package com.doubleknd26.exercise.hbase;

import com.google.common.collect.Lists;
import javaslang.Tuple4;
import org.apache.commons.collections.ListUtils;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.serializer.KryoSerializer;
import org.apache.spark.sql.SparkSession;
import org.junit.Before;
import org.junit.Test;
import scala.Tuple2;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Kideok Kim on 2019-02-21.
 */
public class HbaseBulkLoaderTest extends HbaseTestingUtility implements Serializable {
    private static final String KEY = "key";
    private static final List<Tuple4<String, String, String, String>> SAMPLES = Lists.newArrayList(
            new Tuple4<>(KEY, cf, "cl1", "val1"),
            new Tuple4<>(KEY, cf, "cl2", "val2"));

    private SparkSession ss;
    private JavaSparkContext jsc;
    private Connection conn;
    private Table table;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setUpSpark();
        setUpHbase();
    }

    private void setUpSpark() {
        ss = SparkSession.builder()
                .appName("test")
                .master("local[*]")
                .config("spark.serializer", KryoSerializer.class.getCanonicalName())
                .config("spark.kryo.registrator", SparkKryoRegistrator.class.getCanonicalName())
                .getOrCreate();
        jsc = new JavaSparkContext(ss.sparkContext());
    }

    private void setUpHbase() throws IOException {
        utility.createTable(TableName.valueOf(tableName), cf.getBytes());
        conn = utility.getConnection();
        table = conn.getTable(TableName.valueOf(tableName));
    }

    @Test
    public void testKeyValueTypePut() throws IOException {
        put(SAMPLES);

        List<Tuple4<String, String, String, String>> expected = SAMPLES;
        List<Tuple4<String, String, String, String>> response = get(KEY);
        assertTrue(isEquals(expected, response));
    }

    @Test
    public void testKeyValueTypeDeleteColumnFamily() throws IOException {
        put(SAMPLES);
        deleteColumnFamily(Collections.singletonList(SAMPLES.get(0)));

        List<Tuple4<String, String, String, String>> expected = Collections.EMPTY_LIST;
        List<Tuple4<String, String, String, String>> response = get(KEY);
        assertTrue(isEquals(expected, response));
    }

    private void put(List<Tuple4<String, String, String, String>> samples) throws IOException {
        List<Tuple2<ImmutableBytesWritable, KeyValue>> data = createHbaseData(samples, KeyValue.Type.Put);
        JavaPairRDD<ImmutableBytesWritable, KeyValue> pairRdd = jsc.parallelizePairs(data);
        HbaseBulkLoader.load(pairRdd, utility.getConfiguration(), conn, table);
    }

    private void deleteColumnFamily(List<Tuple4<String, String, String, String>> samples) throws IOException {
        List<Tuple2<ImmutableBytesWritable, KeyValue>> data = createHbaseData(samples, KeyValue.Type.DeleteFamily, true);
        JavaPairRDD<ImmutableBytesWritable, KeyValue> pairRdd = jsc.parallelizePairs(data);
        HbaseBulkLoader.load(pairRdd, utility.getConfiguration(), conn, table);
    }

    private List<Tuple2<ImmutableBytesWritable, KeyValue>> createHbaseData(
            List<Tuple4<String, String ,String, String>> samples,
            KeyValue.Type type) {
        return createHbaseData(samples, type, false);
    }

    private List<Tuple2<ImmutableBytesWritable, KeyValue>> createHbaseData(
            List<Tuple4<String, String ,String, String>> samples,
            KeyValue.Type type,
            boolean withoutColumn) {
        List<Tuple2<ImmutableBytesWritable, KeyValue>> data = Lists.newArrayList();
        for (Tuple4<String, String, String, String> tuple: samples) {
            byte[] key = tuple._1().getBytes();
            byte[] cf = tuple._2().getBytes();
            byte[] cl = tuple._3().getBytes();
            byte[] val = tuple._4().getBytes();

            ImmutableBytesWritable ibw = new ImmutableBytesWritable(key);
            KeyValue kv;
            if (withoutColumn) {
                kv = createKeyValue(key, cf, val, type);
            } else {
                kv = createKeyValue(key, cf, cl, val, type);
            }
            data.add(new Tuple2<>(ibw, kv));
        }
        return data;
    }

    private KeyValue createKeyValue(byte[] row, byte[] cf, byte[] value, KeyValue.Type type) {
        return createKeyValue(row, cf, null, value, type);
    }

    private KeyValue createKeyValue(byte[] row, byte[] cf, byte[] cl, byte[] value, KeyValue.Type type) {
        return new KeyValue(row, cf, cl, HConstants.LATEST_TIMESTAMP, type, value);
    }

    private List<Tuple4<String, String, String, String>> get(String key) throws IOException {
        List<Tuple4<String, String, String, String>> response = Lists.newArrayList();
        Get get = new Get(key.getBytes());
        Result result = table.get(get);
        CellScanner scanner = result.cellScanner();
        while (scanner.advance()) {
            Cell cell = scanner.current();
            String cf = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
            String cl = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            String val = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
            response.add(new Tuple4<>(key, cf, cl, val));
        }
        return response;
    }

    private boolean isEquals(
            List<Tuple4<String, String, String, String>> expected,
            List<Tuple4<String, String, String, String>> response) {
        Collections.sort(expected);
        Collections.sort(response);
        return ListUtils.isEqualList(expected, response);
    }
}
