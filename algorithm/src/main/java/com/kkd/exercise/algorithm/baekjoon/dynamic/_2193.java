package com.kkd.exercise.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2193
 */
public class _2193 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final int size = 91;
    private static long[][] memo = new long[size][2];
    
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        
        // init cache
        for (int i=0 ; i<n ; i++) {
            Arrays.fill(memo[i], -1);
        }
        memo[0][0] = memo[1][1] = 0;
        memo[0][1] = memo[1][0] = 1;
        
        for (int i=2 ; i<n ; i++) {
            memo[i][0] = memo[i-1][0] + memo[i-1][1];
            memo[i][1] = memo[i-1][0];
        }
        
        long ans = memo[n-1][0] + memo[n-1][1];
        System.out.println(ans);
    }
}
