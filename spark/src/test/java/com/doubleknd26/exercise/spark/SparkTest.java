package com.doubleknd26.exercise.spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
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

	@Test
	public void testGroupBy() {
		SparkSession ss = SparkSession.builder()
				.master("local[*]").getOrCreate();

		JavaSparkContext sc = new JavaSparkContext(ss.sparkContext());

		// Parallelized with 2 partitions
		JavaRDD<String> rddX = sc.parallelize(
				Arrays.asList("Joseph", "Jimmy", "Tina", "Thomas", "James", "Cory", "Christine", "Jackeline", "Juan", "Aaron"), 2);
		JavaPairRDD<Character, Iterable<String>> rddY = rddX.groupBy(word -> word.charAt(0), 3);
		System.out.println(rddY.collect());
	}
}
