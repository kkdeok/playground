package com.doubleknd26.exercise.hbase;

import org.apache.hadoop.hbase.HBaseTestingUtility;

/**
 * Created by Kideok Kim on 22/07/2018.
 */
public class HbaseTestingUtility {

    public static HBaseTestingUtility create() {
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
