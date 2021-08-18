package com.kkd.study.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2579
 */
public class _2579 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static int[] arr = new int[300];
    // memo[n][0] = 1 step
    // memo[n][1] = 2 step
    private static int[][] memo = new int[300][2];
    
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        for (int i=0 ; i<n ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int ans = Math.max(doDP(n-1, 0), doDP(n-1, 1));
        System.out.println(ans);
    }
    
    private static int doDP(int n, int i) {
        if (n < 0) {
            return 0;
        }
        if (memo[n][i] != 0) {
            return memo[n][i];
        }
        
        if (i == 0) {
            return memo[n][i] = arr[n] + doDP(n - 2, 1);
        } else {
            return memo[n][i] = arr[n] + Math.max(doDP(n-1, 0), doDP(n-2, 1));
        }
    }
}
