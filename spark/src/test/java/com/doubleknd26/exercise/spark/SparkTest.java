package com.doubleknd26.exercise.spark;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.apache.spark.sql.types.StructType;
import org.junit.Test;

import java.util.Arrays;

import static org.apache.spark.sql.types.DataTypes.*;

public class SparkTest {
    @Test
    public void testIntegerTypeNull() {
        StructType schema = createStructType(Arrays.asList(createStructField("test", IntegerType, true)));
        Object[] values = new Object[1];
        values[0] = null;
        Row row = new GenericRowWithSchema(values, schema);
        Integer result = row.<Integer>getAs("test");
        System.out.println(result); // result = null
    }
}
