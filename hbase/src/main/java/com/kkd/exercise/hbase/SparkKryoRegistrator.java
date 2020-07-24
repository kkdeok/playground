package com.kkd.exercise.hbase;

import com.esotericsoftware.kryo.Kryo;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.spark.serializer.KryoRegistrator;

/**
 * Created by Kideok Kim on 2019-02-22.
 */
public class SparkKryoRegistrator implements KryoRegistrator {
    @Override
    public void registerClasses(Kryo kryo) {
        kryo.register(ImmutableBytesWritable.class);
    }
}
