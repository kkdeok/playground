package com.kkd.study.problem_solving.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _9095 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] memo = new int[11];
    
    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int ans = func(n);
            System.out.println(ans);
        }
    }
    
    private static int func(int n) {
        if (memo[n] != 0) {
            return memo[n];
        }
        return memo[n] = func(n-1) + func(n-2) + func(n-3); 
    }
}
