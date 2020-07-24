package com.kkd.exercise.algorithm.baekjoon.math;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/2609
 */
public class _2609 {
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
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);

        int gcd = getGcd(a, b);
        int lcm = gcd * (a / gcd) * (b / gcd);
        bw.write(gcd + "\n");
        bw.write(lcm + "\n");
    }

    private static int getGcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return getGcd(y, x%y);
        }
    }
}
