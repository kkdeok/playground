package com.doubleknd26.exercise.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.hbase.tool.LoadIncrementalHFiles;
import org.apache.hadoop.mapreduce.Job;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;

import java.io.IOException;

/**
 * Created by Kideok Kim on 2019-02-22.
 */
public class HbaseBulkLoader {
    private static final Logger logger = LogManager.getLogger(HbaseBulkLoader.class);

    public static void load(
            JavaPairRDD<ImmutableBytesWritable, KeyValue> pairRdd,
            Configuration conf,
            Connection conn,
            Table table) throws IOException {
        final String hFilesOutputPath = "/tmp/hfile/" + System.currentTimeMillis();
        RegionLocator regionLocator = conn.getRegionLocator(table.getName());

        Job job = Job.getInstance(conn.getConfiguration());
        HFileOutputFormat2.configureIncrementalLoad(job, table, regionLocator);
        pairRdd.saveAsNewAPIHadoopFile(
                hFilesOutputPath,
                ImmutableBytesWritable.class,
                KeyValue.class,
                HFileOutputFormat2.class,
                job.getConfiguration());
        logger.info("Saved HFiles to " + hFilesOutputPath);

        Path path = new org.apache.hadoop.fs.Path(hFilesOutputPath);
        FileSystem fileSystem = FileSystem.get(conf);
        fileSystem.deleteOnExit(path);

        LoadIncrementalHFiles loader = new LoadIncrementalHFiles(conf);
        loader.doBulkLoad(new Path(hFilesOutputPath), conn.getAdmin(), table, regionLocator);
        logger.info("HFiles are loaded into HBase tables.");
    }
}
