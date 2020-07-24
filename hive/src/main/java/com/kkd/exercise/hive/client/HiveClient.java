package com.kkd.exercise.hive.client;

import java.sql.*;
import java.util.Properties;

public class HiveClient {
    private static final String HIVE_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
    private static final String HIVE_URL = "jdbc:hive2://";

    private static final String PRESTO_DRIVER_NAME = "com.facebook.presto.jdbc.PrestoDriver";
    private static final String PRESTO_URL = "jdbc:presto://";

    private static final String USER = "";

    public static void main(String[] args) throws Exception {
        String driverName = HIVE_DRIVER_NAME;
        String url = HIVE_URL;

        Class.forName(driverName);

        Properties properties = new Properties();
        properties.setProperty("user", USER);
        Connection con = DriverManager.getConnection(url, properties);

        long startTime = System.currentTimeMillis();

        String sql = "";
        System.out.println("Running: " + sql);
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString("id"));
        }

        long endTime = System.currentTimeMillis();
        System.out.println("took ms: " + (endTime - startTime));

        res.close();
        stmt.close();
        con.close();
    }
}
