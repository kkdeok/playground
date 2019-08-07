package com.doubleknd26.exercise.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1000
 */
public class _1000 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main (String[] args) throws Exception {
        int a = br.read();
        int b = br.read();

        bw.write(a + " " + b);
        bw.flush();
        bw.close();
        br.close();
    }
}
