package com.doubleknd26.exercise.hbase;

import com.google.common.base.Preconditions;
import org.apache.hadoop.hbase.DoNotRetryIOException;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * It used to test HbaseDescriptor.
 *
 * Created by Kideok Kim on 22/07/2018.
 */
public class HbaseDescriptorTest {
    private static final boolean useEmbeddedHbase = true;
    private static HBaseTestingUtility utility;
    private static String tableName;
    private static String cf;
    private static String cl;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // read configuration
        FileInputStream is = new FileInputStream("config/config-local.yml");
        Yaml yaml = new Yaml();
        Map config = (Map) yaml.load(is);
        Map tableConfig = (Map) config.get("hbase_table");
        tableName = (String) tableConfig.get("name");
        cf = (String) tableConfig.get("cf");
        cl = (String) tableConfig.get("cl");
        if (useEmbeddedHbase) {
            utility = HbaseTestingUtility.create();
            utility.startMiniCluster();
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        if (useEmbeddedHbase && utility != null) {
            utility.shutdownMiniCluster();
        }
    }

    /**
     * Because table name is used for part of path of stored files,
     * It has to follow the rule of filename. Otherwise, It returns this error,
     * IllegalArgumentException
     * Basically, table name cannot be started by '-', '.', and ' '.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTableWithWrongTableName() {
        TableName tableName = TableName.valueOf("  testTable");
    }

    @Test
    public void testCreateTable() {
        // As of release 2.0.0, HTableDescriptor is deprecated.
        // this will be removed in HBase 3.0.0.
        // Use TableDescriptorBuilder to build HTableDescriptor.
        //
        // TableName tableName = TableName.valueOf(HbaseDescriptorTest.tableName);
        // TableDescriptor descriptor = TableDescriptorBuilder
        // .newBuilder(tableName)
        // .build();
        //
        // Because we use TestingUtility now,
        // we can create TableDescriptor like this.
        TableName tableName = TableName.valueOf(HbaseDescriptorTest.tableName);
        TableDescriptor descriptor = utility.createTableDescriptor(
                tableName,
                Bytes.toBytes(cf));
        try {
            Admin admin = utility.getAdmin();
            // create table using tableDescriptor
            admin.createTable(descriptor);
            // check whether if the table is created or not.
            assertTrue(admin.tableExists(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTableName() {
        TableName tableName = TableName.valueOf(HbaseDescriptorTest.tableName);
        TableDescriptor descriptor = utility.createTableDescriptor(
                tableName,
                Bytes.toBytes(cf));
        // get table name
        assertTrue(tableName.equals(descriptor.getTableName()));
    }

    @Test
    public void testHasColumnFamily() {
        TableName tableName = TableName.valueOf(HbaseDescriptorTest.tableName);
        TableDescriptor descriptor = utility.createTableDescriptor(
                tableName,
                Bytes.toBytes(cf));
        // has column family.
        assertTrue(descriptor.hasColumnFamily(Bytes.toBytes(cf)));
    }

    /**
     * expected exception: org.apache.hadoop.hbase.DoNotRetryIOException:
     * org.apache.hadoop.hbase.DoNotRetryIOException: region is read only
     * TODO: how can we put data before set this table up as read only ?
     *
     * @throws DoNotRetryIOException
     */
    @Test(expected = DoNotRetryIOException.class)
    public void testReadOnlyTable() throws Exception {
        TableName tableName = TableName.valueOf(HbaseDescriptorTest.tableName);
        ColumnFamilyDescriptor familyDescriptor = ColumnFamilyDescriptorBuilder
                .newBuilder(Bytes.toBytes(cf))
                .build();
        TableDescriptor descriptor = TableDescriptorBuilder
                .newBuilder(tableName)
                .setColumnFamily(familyDescriptor)
                .setReadOnly(true)
                .build();

        utility.createTable(descriptor, (byte[][]) null);
        Table table = utility.getConnection().getTable(tableName);
        Preconditions.checkNotNull(table);
        Put put = new Put(Bytes.toBytes("testKey"));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cl), Bytes.toBytes("testValue"));
        table.put(put);
    }

}
