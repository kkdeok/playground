package com.kkd.exercise.spark;

import com.kkd.exercise.spark.model.Person;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;

/**
 * Created by Kideok Kim on 2019-02-04.
 */
public class PersonDataFrameGenerator {

    public static Dataset<Row> generate(List<Person> list, SparkSession ss) {
        // We have to set getter and setter in the target class.
        return ss.createDataFrame(list, Person.class);
    }
}
