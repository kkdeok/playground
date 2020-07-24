package com.kkd.exercise.algorithm.baekjoon.datastructure;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/10799
 */
public class _10799 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        char[] line = br.readLine().toCharArray();
        int stack = 1, ans = 0;
        for (int i=1 ; i<line.length ; i++) {
            char prev = line[i-1];
            char curr = line[i];
            if (curr == ')') {
                stack--;
                if (prev == '(') {
                    ans += stack;
                } else {
                    ans += 1;
                }
            } else {
                stack++;
            }
        }
        bw.write(ans + "\n");
    }
}