package com.kkd.study.hbase;

import com.google.common.collect.Lists;
import javaslang.Tuple4;
import org.apache.commons.collections.ListUtils;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkException;
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
        load(SAMPLES, KeyValue.Type.Put);

        List<Tuple4<String, String, String, String>> expected = SAMPLES;
        List<Tuple4<String, String, String, String>> response = get(KEY);
        assertTrue(isEquals(expected, response));
    }

    @Test
    public void testKeyValueTypeDeleteColumnFamily() throws IOException {
        load(SAMPLES, KeyValue.Type.Put);
        load(Collections.singletonList(SAMPLES.get(0)), KeyValue.Type.DeleteFamily);

        List<Tuple4<String, String, String, String>> expected = Collections.EMPTY_LIST;
        List<Tuple4<String, String, String, String>> response = get(KEY);
        assertTrue(isEquals(expected, response));
    }

    /**
     * org.apache.spark.SparkException: Task failed while writing rows
     * Caused by: java.lang.IllegalArgumentException: Can not create a Path from an empty string
     * @throws IOException
     */
    @Test(expected = SparkException.class)
    public void testKeyValueTypeDelete() throws IOException {
        load(SAMPLES, KeyValue.Type.Put);
        load(Collections.singletonList(SAMPLES.get(0)), KeyValue.Type.Delete);
    }

    private void load(List<Tuple4<String, String, String, String>> samples, KeyValue.Type type)
            throws IOException {
        List<Tuple2<ImmutableBytesWritable, KeyValue>> data = createHbaseData(samples, type);
        JavaPairRDD<ImmutableBytesWritable, KeyValue> pairRdd = jsc.parallelizePairs(data);
        HbaseBulkLoader.load(pairRdd, utility.getConfiguration(), conn, table);
    }

    private List<Tuple2<ImmutableBytesWritable, KeyValue>> createHbaseData(
            List<Tuple4<String, String, String, String>> samples,
            KeyValue.Type type) {
        List<Tuple2<ImmutableBytesWritable, KeyValue>> data = Lists.newArrayList();
        for (Tuple4<String, String, String, String> tuple : samples) {
            byte[] key = tuple._1().getBytes();
            byte[] cf = tuple._2().getBytes();
            byte[] cl = tuple._3().getBytes();
            byte[] val = tuple._4().getBytes();

            ImmutableBytesWritable ibw = new ImmutableBytesWritable(key);
            KeyValue kv = createKeyValue(key, cf, cl, val, type);
            data.add(new Tuple2<>(ibw, kv));
        }
        return data;
    }

    private KeyValue createKeyValue(byte[] row, byte[] cf, byte[] cl, byte[] value, KeyValue.Type type) {
        final long timestamp = HConstants.LATEST_TIMESTAMP;
        switch (type) {
            case Put:
                return new KeyValue(row, cf, cl, timestamp, type, value);
            case DeleteFamily:
                return new KeyValue(row, cf, null, timestamp, type);
            case Delete:
                return new KeyValue(row, timestamp, type);
            default:
                throw new RuntimeException("Unknown KeyValue.Type exception: " + type);
        }
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
