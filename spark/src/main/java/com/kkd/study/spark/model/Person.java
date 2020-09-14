package com.kkd.study.spark.model;

import java.util.List;

/**
 * Created by Kideok Kim on 2019-02-04.
 */
public class Person {
    private String name;
    private int age;
    private Sex sex;
    private List<String> favoriteSingers;

    public Person(String name, int age, Sex sex, List<String> favoriteSingers) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.favoriteSingers = favoriteSingers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<String> getFavoriteSingers() {
        return favoriteSingers;
    }

    public void setFavoriteSingers(List<String> favoriteSingers) {
        this.favoriteSingers = favoriteSingers;
    }
}
