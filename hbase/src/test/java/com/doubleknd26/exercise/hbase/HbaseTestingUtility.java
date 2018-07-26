package com.doubleknd26.exercise.hbase;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.Map;

/**
 * Created by Kideok Kim on 22/07/2018.
 */
public class HbaseTestingUtility {
    static HBaseTestingUtility utility;
    static Map tableConfig;
    static String tableName;
    static String cf;
    static String cl;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // read configuration
        FileInputStream is = new FileInputStream("config/config-local.yml");
        Yaml yaml = new Yaml();
        Map config = (Map) yaml.load(is);
        tableConfig = (Map) config.get("hbase_table");
        tableName = (String) tableConfig.get("name");
        cf = (String) tableConfig.get("cf");
        cl = (String) tableConfig.get("cl");
        utility = HbaseTestingUtility.create();
        utility.startMiniCluster();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        if (utility != null) {
            utility.shutdownMiniCluster();
        }
    }

    @Before
    public void setUp() throws Exception {
        TableName tableName = TableName.valueOf(HbaseTestingUtility.tableName);
        if (utility.getAdmin().tableExists(tableName)) {
            utility.deleteTable(tableName);
        }
    }

    private static HBaseTestingUtility create() {
        HBaseTestingUtility utility = new HBaseTestingUtility();
        // For whatever reason, we have to set belows port
        // to use HbaseTestingUtility in ver 2.0.0
        utility.getConfiguration().set("hbase.master.port", "60000");
        utility.getConfiguration().set("hbase.master.info.port", "60010");
        utility.getConfiguration().set("hbase.regionserver.port", "60020");
        utility.getConfiguration().set("hbase.regionserver.info.port", "60030");
        return utility;
    }
}
