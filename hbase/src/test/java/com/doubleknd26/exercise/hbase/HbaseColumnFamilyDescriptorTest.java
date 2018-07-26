package com.doubleknd26.exercise.hbase;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Kideok Kim on 26/07/2018.
 */
public class HbaseColumnFamilyDescriptorTest {
    private static HBaseTestingUtility utility;
    private static Map tableConfig;
    private static String tableName;
    private static String cf;
    private static String cl;

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
        TableName tableName = TableName.valueOf(HbaseColumnFamilyDescriptorTest.tableName);
        if (utility.getAdmin().tableExists(tableName)) {
            utility.deleteTable(tableName);
        }
    }

    @Test
    public void testColumnFamilyCreation() {
        byte[] cfName = Bytes.toBytes(cf);
        ColumnFamilyDescriptor descriptor = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .build();
        assertTrue(Bytes.equals(cfName, descriptor.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColumnFamilyCreationWithWrongName1() {
        byte[] cfName = Bytes.toBytes(".testColumnFamilyName");
        ColumnFamilyDescriptor descriptor = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColumnFamilyCreationWithWrongName2() {
        byte[] cfName = Bytes.toBytes(".testColumnFamilyName");
        ColumnFamilyDescriptorBuilder.isLegalColumnFamilyName(cfName);
    }


    @Test
    public void testMaxVersions() {
        final int defaultMaxVersions = 1;
        final int maxVersions = 3;
        byte[] cfName = Bytes.toBytes(cf);
        ColumnFamilyDescriptor descriptor1 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .build();
        ColumnFamilyDescriptor descriptor2 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .setMaxVersions(maxVersions)
                .build();
        assertTrue(descriptor1.getMaxVersions() == defaultMaxVersions);
        assertTrue(descriptor2.getMaxVersions() == maxVersions);
    }

    @Test
    public void testCompression() {
        byte[] cfName = Bytes.toBytes(cf);
        ColumnFamilyDescriptor descriptor1 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .build();
        ColumnFamilyDescriptor descriptor2 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .setCompressionType(Compression.Algorithm.SNAPPY)
                .build();
        assertTrue(Compression.Algorithm.NONE.equals(descriptor1.getCompressionType()));
        assertTrue(Compression.Algorithm.SNAPPY.equals(descriptor2.getCompressionType()));
    }

    @Test
    public void testBlockSize() {
        byte[] cfName = Bytes.toBytes(cf);
        final int defaultBlockSize = 65536; // 64KB
        final int blockSize = 10485760; // 10MB
        ColumnFamilyDescriptor descriptor1 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .build();
        ColumnFamilyDescriptor descriptor2 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .setBlocksize(blockSize)
                .build();
        assertTrue(defaultBlockSize == descriptor1.getBlocksize());
        assertTrue(blockSize == descriptor2.getBlocksize());
    }

    @Test
    public void testBlockCache() {
        byte[] cfName = Bytes.toBytes(cf);
        ColumnFamilyDescriptor descriptor1 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .build();
        ColumnFamilyDescriptor descriptor2 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .setBlockCacheEnabled(false)
                .build();
        assertTrue(descriptor1.isBlockCacheEnabled());
        assertTrue(!descriptor2.isBlockCacheEnabled());
    }

    @Test
    public void testTimeToLive() {
        byte[] cfName = Bytes.toBytes(cf);
        int defaultTTL = Integer.MAX_VALUE;
        int ttl = 10;
        ColumnFamilyDescriptor descriptor1 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .build();
        ColumnFamilyDescriptor descriptor2 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .setTimeToLive(ttl)
                .build();
        assertTrue(defaultTTL == descriptor1.getTimeToLive());
        assertTrue(ttl == descriptor2.getTimeToLive());
    }

    /**
     * inMemory True if we are to favor keeping all values for this
     * column family in the HRegionServer cache.
     */
    @Test
    public void testInMemory() {
        byte[] cfName = Bytes.toBytes(cf);
        ColumnFamilyDescriptor descriptor1 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .build();
        ColumnFamilyDescriptor descriptor2 = ColumnFamilyDescriptorBuilder
                .newBuilder(cfName)
                .setInMemory(true)
                .build();
        assertTrue(!descriptor1.isInMemory());
        assertTrue(descriptor2.isInMemory());
    }
}
