package com.doubleknd26.exercise.algorithm.baekjoon.math;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/11653
 */
public class _11653 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        int n = Integer.parseInt(br.readLine());
        // 소인수분해
        for (int i=2 ; i*i<=n ; i++) {
            while (n % i == 0) {
                bw.write(i + "\n");
                n /= i;
            }
        }
        if (n > 1) {
            bw.write(n + "\n");
        }
    }
}
