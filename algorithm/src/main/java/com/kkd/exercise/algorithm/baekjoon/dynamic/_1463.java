package com.kkd.exercise.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1463 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] memo = new int[1000001];
    
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int ans = func(n);
        System.out.println(ans);
    }
    
    private static int func(int n) {
        if (n == 1) {
            return 0;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = Integer.MAX_VALUE;
        if (n % 3 == 0) {
            memo[n] = Math.min(memo[n], func(n/3) + 1);
        }
        if (n % 2 == 0) {
            memo[n] = Math.min(memo[n], func(n/2) + 1);
        }
        return memo[n] = Math.min(memo[n], func(n-1) + 1);
    }
}
