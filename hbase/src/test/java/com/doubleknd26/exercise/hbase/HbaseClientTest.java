package com.doubleknd26.exercise.hbase;

import com.google.common.collect.Maps;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
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
    private static final boolean useEmbeddedHbase = true;
    private static HBaseTestingUtility utility;
    private static Map tableConfig;
    private HbaseClient client;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // read configuration
        FileInputStream is = new FileInputStream("config/config-local.yml");
        Yaml yaml = new Yaml();
        Map config = (Map) yaml.load(is);
        tableConfig = (Map) config.get("hbase_table");
        String name = (String) tableConfig.get("name");
        String cf = (String) tableConfig.get("cf");
        if (useEmbeddedHbase) {
            utility = HbaseTestingUtility.create();
            utility.startMiniCluster();
            utility.createTable(TableName.valueOf(name), cf);
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        if (useEmbeddedHbase && utility != null) {
            utility.shutdownMiniCluster();
        }
    }

    @Before
    public void setUp() throws Exception {
        Configuration conf;
        if (useEmbeddedHbase) {
            String name = (String) tableConfig.get("name");
            utility.truncateTable(TableName.valueOf(name));
            conf = utility.getConfiguration();
        } else {
            conf = HBaseConfiguration.create();
        }
        client = new HbaseClient(conf, tableConfig);
    }

    @Test
    public void testPut() {
        try {
            // given
            String key = "fakeKey";
            byte[] value = Bytes.toBytes("fakeValue");
            // when
            Put put = client.put(key, value);
            // then
            String cf = (String) tableConfig.get("cf");
            String cl = (String) tableConfig.get("cl");
            assertTrue(Bytes.equals(put.getRow(), Bytes.toBytes(key)));
            assertTrue(put.getTimestamp() == Long.MAX_VALUE);
            assertTrue(put.has(Bytes.toBytes(cf), Bytes.toBytes(cl), value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPutAndGet() {
        try {
            String key = "fakeKey";
            byte[] value = Bytes.toBytes("fakeValue");
            Put put = client.put(key, value);
            Result result = client.get(key);

            // Result.toString return keyvalues
            System.out.println(result.toString());
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

            Result result = client.get(key);
            String cf = (String) tableConfig.get("cf");
            String cl = (String) tableConfig.get("cl");
            byte[] response = result.getValue(Bytes.toBytes(cf), Bytes.toBytes(cl));
            assertTrue(response == null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}