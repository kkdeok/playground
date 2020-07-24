package com.kkd.exercise.java.enumeration._interface;


import java.util.Arrays;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        double x = 4.0d;
        double y = 2.0d;
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    private static void test(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op: opSet) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
