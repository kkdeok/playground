package com.doubleknd26.exercise.algorithm.baekjoon.datastructure;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/9012
 */
public class _9012 {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        int n = Integer.parseInt(br.readLine());
        while (n > 0) {
            char[] line = br.readLine().toCharArray();
            int i, stack = 0;
            for (i = 0 ; i < line.length ; i++) {
                char c = line[i];
                if (c == '(') {
                    stack++;
                } else {
                    if (stack > 0) {
                        stack--;
                    } else {
                        break;
                    }
                }
            }
            if (i != line.length || stack != 0) {
                bw.write("NO\n");
            } else {
                bw.write("YES\n");
            }
            n--;
        }
    }
}