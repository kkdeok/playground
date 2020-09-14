package com.kkd.study.problem_solving.baekjoon.math;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/9613
 */
public class _9613 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            long ans = 0;
            for (int i=1 ; i<=n ; i++) {
                for (int j=i+1 ; j<=n ; j++) {
                    ans += getGcd(Integer.parseInt(input[i]), Integer.parseInt(input[j]));
                }
            }
            bw.write(ans + "\n");
        }
    }

    private static int getGcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return getGcd(y, x%y);
        }
    }
}