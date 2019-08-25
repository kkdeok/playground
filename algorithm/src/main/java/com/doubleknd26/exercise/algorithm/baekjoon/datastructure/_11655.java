package com.doubleknd26.exercise.algorithm.baekjoon.datastructure;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/11655
 */
public class _11655 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        char[] input = br.readLine().toCharArray();
        int len = input.length;
        for (int i=0 ; i<len ; i++) {
            char c = input[i];
            if (c >= 'a' && c <= 'z') {
                char r = (char) ('a' + (c - 'a' + 13) % 26);
                bw.write(r);
            } else if (c >= 'A' && c <= 'Z') {
                char r = (char) ('A' + (c - 'A' + 13) % 26);
                bw.write(r);
            } else {
                bw.write(c);
            }

        }
        bw.write("\n");
    }
}
