package com.kkd.exercise.algorithm.baekjoon.math;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/1850
 */
public class _1850 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        String[] input = br.readLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);
        long gcd = getGcd(a, b);
        for (long i=0 ; i<gcd ; i++) {
            bw.write("1");
        }
    }

    private static long getGcd(long x, long y) {
        if (y == 0) {
            return x;
        } else {
            return getGcd(y, x%y);
        }
    }
}
