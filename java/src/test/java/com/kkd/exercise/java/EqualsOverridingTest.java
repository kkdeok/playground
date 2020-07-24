package com.kkd.exercise.java;

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * This class explains about why we need to override equals method using use case.
 *
 * Created by Kideok Kim on 2019-03-08.
 */
public class EqualsOverridingTest {

    class Fruit {
        private String name;

        public Fruit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Fruit)) return false;
            Fruit fruit = (Fruit) o;
            return Objects.equals(getName(), fruit.getName());
        }

        // Don't override equals method.
//        public boolean equals(Fruit o) {
//            if (this == o) return true;
//            if (!(o instanceof Fruit)) return false;
//            Fruit fruit = (Fruit) o;
//            return Objects.equals(getName(), fruit.getName());
//        }

        @Override
        public int hashCode() {
            return Objects.hash(getName());
        }
    }

    @Test
    public void test3Equals() {
        Fruit apple1 = new Fruit("apple");
        Fruit apple2 = new Fruit("apple");

        Set<Fruit> fruits = new HashSet<>();
        fruits.add(apple1);
        fruits.add(apple2);

        assertEquals(1, fruits.size());
    }
}
