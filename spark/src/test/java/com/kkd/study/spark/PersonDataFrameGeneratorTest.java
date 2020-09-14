package com.kkd.study.spark;

import com.kkd.study.spark.model.Person;
import com.kkd.study.spark.model.Sex;
import org.apache.spark.sql.SparkSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kideok Kim on 2019-02-04.
 */
public class PersonDataFrameGeneratorTest {
    private List<Person> source;

    @Before
    public void setUp() throws Exception {
        this.source = Arrays.asList(
                new Person("김기덕", 30, Sex.MAN, Arrays.asList("Psy", "이소라")),
                new Person("김현우", 30, Sex.MAN, Arrays.asList("야다", "플라워")));
    }


    /**
     * java.lang.IllegalArgumentException: The value (MAN) of the type
     * (com.doubleknd26.study.spark.model.Sex) cannot be converted
     * to the string type
     */
    @Test(expected = IllegalArgumentException.class)
    public void generate() {
        SparkSession ss = prepareSpark(false);
        PersonDataFrameGenerator.generate(source, ss);
    }

    private SparkSession prepareSpark(boolean withKryo) {
        SparkSession.Builder builder = SparkSession.builder();
        builder.master("local[*]");
        builder.appName("test");
        if (withKryo) {
        }
        return builder.getOrCreate();
    }
}