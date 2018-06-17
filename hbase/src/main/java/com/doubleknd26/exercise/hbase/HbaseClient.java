package com.doubleknd26.exercise.hbase;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Kideok Kim on 15/06/2018.
 */
public class HbaseClient {
    private Table table;
    private String cf;
    private String cl;

    public HbaseClient(Configuration conf, Map tableConfig) {
        try {
            Connection conn = ConnectionFactory.createConnection(conf);
            String tableName = (String) tableConfig.get("name");
            this.table = conn.getTable(TableName.valueOf(tableName));
            this.cf = (String) tableConfig.get("cf");
            this.cl = (String) tableConfig.get("cl");
            Preconditions.checkNotNull(this.table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void put(String key, byte[] value) throws IOException {
        Put put = new Put(Bytes.toBytes(key));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cl), value);
        table.put(put);
    }

    public byte[] get(String key) throws IOException {
        Get get = new Get(Bytes.toBytes(key));
        get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cl));
        Result result = table.get(get);
        return result.getValue(Bytes.toBytes(cf), Bytes.toBytes(cl));
    }

    public Map<String, byte[]> scan() throws IOException {
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cl));
        ResultScanner scanner = table.getScanner(scan);
        Map<String, byte[]> response = Maps.newHashMap();
        for (Result result = scanner.next(); result != null; result = scanner.next()) {
            String key = Bytes.toString(result.getRow());
            byte[] value = result.getValue(Bytes.toBytes(cf), Bytes.toBytes(cl));
            response.put(key, value);
        }
        return response;
    }

    public void delete(String key) throws IOException {
        Delete delete = new Delete(Bytes.toBytes(key));
        delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cl));
        table.delete(delete);
    }
}
