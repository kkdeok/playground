package com.kkd.exercise.hbase;

import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Kideok Kim on 26/07/2018.
 */
public class HbaseColumnFamilyDescriptorTest extends HbaseTestingUtility {

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
