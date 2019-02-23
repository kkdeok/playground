package com.doubleknd26.exercise.spark.model;

/**
 * Created by Kideok Kim on 2019-02-04.
 */
public enum Sex {
    WOMAN("WOMAN"),
    MAN("MAN");

    private String str;

    Sex(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
