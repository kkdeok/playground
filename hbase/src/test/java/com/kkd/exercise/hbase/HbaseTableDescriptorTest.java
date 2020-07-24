package com.kkd.exercise.hbase;

import org.apache.hadoop.hbase.DoNotRetryIOException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * TableDescriptor test.
 *
 * Created by Kideok Kim on 22/07/2018.
 */
public class HbaseTableDescriptorTest extends HbaseTestingUtility {

    /**
     * As of release 2.0.0, HTableDescriptor is deprecated.
     * Use TableDescriptorBuilder to build HTableDescriptor.
     */
    @Test
    public void testTableCreation() throws IOException {
        TableName tableName = TableName.valueOf(HbaseTableDescriptorTest.tableName);
        ColumnFamilyDescriptor familyDescriptor = ColumnFamilyDescriptorBuilder
                .newBuilder(Bytes.toBytes(cf))
                .build();
        TableDescriptor tableDescriptor = TableDescriptorBuilder
                .newBuilder(tableName)
                .setColumnFamily(familyDescriptor)
                .build();
        utility.createTable(tableDescriptor, null);
        assertTrue(utility.getAdmin().tableExists(tableName));
    }

    /**
     * Because table name is used for part of stored files path, It has to follow
     * the rule of filename. Otherwise, It returns IllegalArgumentException.
     * Basically, table name cannot be started by '-', '.', and ' '.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTableCreationWithWrongTableName() {
        TableName.valueOf("  testTable");
    }

    @Test
    public void testGetTableName() {
        TableName tableName = TableName.valueOf(HbaseTableDescriptorTest.tableName);
        TableDescriptor descriptor = utility.createTableDescriptor(
                tableName,
                Bytes.toBytes(cf));
        assertTrue(descriptor.getTableName().equals(tableName));
    }

    @Test
    public void testGetColumnFamily() {
        TableName tableName = TableName.valueOf(HbaseTableDescriptorTest.tableName);
        TableDescriptor descriptor = utility.createTableDescriptor(
                tableName,
                Bytes.toBytes(cf));
        ColumnFamilyDescriptor familyDescriptor = descriptor.getColumnFamily(Bytes.toBytes(cf));
        assertTrue(familyDescriptor.getNameAsString().equals(cf));
    }

    @Test
    public void testHasColumnFamily() {
        TableName tableName = TableName.valueOf(HbaseTableDescriptorTest.tableName);
        TableDescriptor descriptor = utility.createTableDescriptor(
                tableName,
                Bytes.toBytes(cf));
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
        TableName tableName = TableName.valueOf(HbaseTableDescriptorTest.tableName);
        ColumnFamilyDescriptor familyDescriptor = ColumnFamilyDescriptorBuilder
                .newBuilder(Bytes.toBytes(cf))
                .build();
        TableDescriptor descriptor = TableDescriptorBuilder
                .newBuilder(tableName)
                .setColumnFamily(familyDescriptor)
                .setReadOnly(true)
                .build();
        utility.createTable(descriptor, null);
        Table table = utility.getConnection().getTable(tableName);
        Put put = new Put(Bytes.toBytes("testKey"));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cl), Bytes.toBytes("testValue"));
        table.put(put);
    }

    @Test
    public void testMaxFileSize() {
        final long size = 10485760L; // 10MB
        TableName tableName = TableName.valueOf(HbaseTableDescriptorTest.tableName);
        ColumnFamilyDescriptor familyDescriptor = ColumnFamilyDescriptorBuilder
                .newBuilder(Bytes.toBytes(cf))
                .build();
        TableDescriptor descriptor = TableDescriptorBuilder
                .newBuilder(tableName)
                .setColumnFamily(familyDescriptor)
                .setMaxFileSize(size)
                .setMemStoreFlushSize(size)
                .build();
        try {
            utility.createTable(descriptor, null);
            Table table = utility.getConnection().getTable(tableName);
            List<RegionInfo> before = utility.getAdmin().getRegions(tableName);
            assertTrue(before.size() == 1);

            byte[] value = Bytes.createMaxByteArray(1048576); // 1MB
            for (int i = 1; i <= 40; i++) {
                Put put = new Put(Bytes.toBytes("rowkey" + i));
                put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cl), value);
                table.put(put);
            }
            utility.getAdmin().flush(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // To see region split using master server UI, waiting here.
        // while (true);
    }
}
