package com.doubleknd26.exercise.hbase;

import com.google.common.collect.Maps;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.*;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Kideok Kim on 15/06/2018.
 */
public class HbaseClientTest {
    private static HBaseTestingUtility utility;
    private static Map tableConfig;
    private HbaseClient client;

    @BeforeClass
    public static void setUpClass() throws Exception {
        utility = new HBaseTestingUtility();
        // For whatever reason, we have to set port number to use HbaseTestingUtility in ver 2.0.0.
        utility.getConfiguration().set("hbase.master.port", "60000");
        utility.getConfiguration().set("hbase.master.info.port", "60010");
        utility.getConfiguration().set("hbase.regionserver.port", "60020");
        utility.getConfiguration().set("hbase.regionserver.info.port", "60030");
        utility.startMiniCluster();
        // read configuration
        FileInputStream is = new FileInputStream("config/config-local.yml");
        Yaml yaml = new Yaml();
        Map config = (Map) yaml.load(is);
        tableConfig = (Map) config.get("hbase_table");
        String name = (String) tableConfig.get("name");
        String cf = (String) tableConfig.get("cf");
        utility.createTable(TableName.valueOf(name), cf);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        utility.cleanupDataTestDirOnTestFS();
        utility.shutdownMiniCluster();
    }

    @Before
    public void setUp() throws Exception {
        String tableName = (String) tableConfig.get("name");
        utility.truncateTable(TableName.valueOf(tableName));
        client = new HbaseClient(utility.getConfiguration(), tableConfig);
    }

    @Test
    public void testPut() {
        try {
            String key = "fakeKey";
            byte[] value = Bytes.toBytes("fakeValue");
            Put put = client.put(key, value);
            String cf = (String) tableConfig.get("cf");
            String cl = (String) tableConfig.get("cl");
            assertTrue(Bytes.equals(put.getRow(), Bytes.toBytes(key)));
            assertTrue(put.has(Bytes.toBytes(cf), Bytes.toBytes(cl), value));
            byte[] response = client.get(key);
            assertTrue(Arrays.equals(value, response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testScan() {
        try {
            Map<String, byte[]> fakeData = Maps.newHashMap();
            fakeData.put("fakeKey1", Bytes.toBytes("fakeValue1"));
            fakeData.put("fakeKey2", Bytes.toBytes("fakeValue2"));
            for (Map.Entry<String, byte[]> entry: fakeData.entrySet()) {
                client.put(entry.getKey(), entry.getValue());
            }
            Map<String, byte[]> response = client.scan();
            for (Map.Entry<String, byte[]> entry: response.entrySet()) {
                String responseKey = entry.getKey();
                byte[] responseValue = entry.getValue();
                assertTrue(fakeData.containsKey(responseKey));
                assertTrue(Arrays.equals(responseValue, fakeData.get(responseKey)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            String key = "fakeKey";
            byte[] value = Bytes.toBytes("fakeValue");
            client.put(key, value);
            client.delete(key);
            byte[] response = client.get(key);
            assertTrue(response == null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}