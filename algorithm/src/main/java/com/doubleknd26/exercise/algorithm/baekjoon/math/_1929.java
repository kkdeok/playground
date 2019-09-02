package com.doubleknd26.exercise.algorithm.baekjoon.math;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/1929
 */
public class _1929 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        String input[] = br.readLine().split(" ");
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        boolean isNonPrime[] = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i=2 ; i<=n ; i++) {
            if (!isNonPrime[i]) {
                primes.add(i);
                for (int j=i*2 ; j<=n ; j+=i) {
                    isNonPrime[j] = true;
                }
            }
        }

        for (Integer p : primes) {
            if (p >= m) {
                bw.write(p + "\n");
            }
        }
    }
}