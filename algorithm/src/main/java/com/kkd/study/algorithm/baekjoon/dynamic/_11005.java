package com.kkd.study.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/11055
 */
public class _11005 {
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
        
        memo[0] = arr[0];
        int ans = 0;
        for (int i=0 ; i<n ; i++) {
            if (memo[i] == 0) {
                doDP(i);
            }
            ans = Math.max(ans, memo[i]);
        }
        System.out.println(ans);
    }
    
    private static int doDP(int n) {
        if (memo[n] != 0) {
            return memo[n];
        }
        
        int ans = arr[n]; 
        for (int i=n-1 ; i>=0 ; i--) {
            if (arr[i] < arr[n]) {
                int temp = doDP(i) + arr[n];
                ans = Math.max(ans, temp);
            }
        }
        return memo[n] = ans;
    } 
}
