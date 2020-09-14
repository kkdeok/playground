package com.kkd.study.problem_solving.baekjoon.datastructure;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/10808
 */
public class _10808 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int ARRAY_SIZE = 26;

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        char[] input = br.readLine().toCharArray();
        int[] ans = new int[ARRAY_SIZE];
        int len = input.length;
        for (int i=0 ; i<len ; i++) {
            ans[input[i] - 'a']++;
        }
        for (int i=0 ; i<ARRAY_SIZE ; i++) {
            bw.write(ans[i] + "\n");
        }
    }
}