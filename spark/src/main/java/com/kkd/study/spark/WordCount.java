package com.kkd.study.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;


import java.util.Arrays;
import java.util.Map;

/**
 * Created by Kideok Kim on 30/06/2018.
 */
public class WordCount {
    private JavaSparkContext sc;
    private final String filePath = "spark/src/main/resources/wordcount_sample.txt";

    private void prepareSpark() {
        SparkConf conf = new SparkConf().
                setMaster("local").
                setAppName("word-count");
        sc = new JavaSparkContext(conf);
    }

    private void start() {
        prepareSpark();
        JavaRDD<String> input = sc.textFile(filePath);
        JavaPairRDD<String, Integer> output = input.
                flatMap(line -> Arrays.asList(line.split(" ")).iterator()).
                mapToPair(word -> new Tuple2<>(word, 1)).
                reduceByKey((count1, count2) -> count1 + count2);

        Map<String, Integer> result = output.collectAsMap();
        for (Map.Entry<String, Integer> entry: result.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            System.out.println(String.format("[word]: %s [count]: %s", word, count));
        }
    }

    private void shutdown() {
        sc.stop();
    }

    public static void main(String[] args) {
        WordCount wordCount = new WordCount();
        wordCount.start();
        wordCount.shutdown();
    }
}
