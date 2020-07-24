package com.kkd.exercise.algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://www.acmicpc.net/problem/11656
 */
public class _11656 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        Set<String> s = new TreeSet<>();
        String input = br.readLine();
        int len = input.length();
        for (int i=0 ; i<len ; i++) {
            String sub = input.substring(i, len);
            s.add(sub);
        }
        for (String k : s) {
            bw.write(k + "\n");
        }
    }
}
