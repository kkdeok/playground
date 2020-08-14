package com.kkd.exercise.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/11053
 */
public class _11053 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final int SIZE = 1000;
    private static int[] arr = new int[SIZE];
    private static int[] memo = new int[SIZE];
    
    public static void main(String[] args) throws Exception {
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        
        line = br.readLine().split(" ");
        for (int i=0 ; i<n ; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        
        int ans = 0;
        for (int i=0 ; i<n ; i++) {
            doDP(i);
            ans = Math.max(ans, memo[i]);
        }
        System.out.println(ans);
    }
    
    private static int doDP(int n) {
        if (memo[n] != 0) {
            return memo[n];
        }
        
        int ans = 1;
        for (int i=n-1 ; i>=0 ; i--) {
            if (arr[n] > arr[i]) {
                int temp = doDP(i) + 1;
                ans = Math.max(ans, temp);
            }
        }
        return memo[n] = ans;
    }
}
