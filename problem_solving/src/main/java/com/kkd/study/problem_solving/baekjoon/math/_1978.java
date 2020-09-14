package com.kkd.study.problem_solving.baekjoon.math;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/1978
 */
public class _1978 {
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
        String[] input = br.readLine().split(" ");
        int ans = 0;
        for (int i=0 ; i<n ; i++) {
            int d = Integer.parseInt(input[i]);
            if (isPrime(d)) {
                ans++;
            }
        }
        bw.write(ans + "\n");
    }

    private static boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i=2 ; i<=x/2 ; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
