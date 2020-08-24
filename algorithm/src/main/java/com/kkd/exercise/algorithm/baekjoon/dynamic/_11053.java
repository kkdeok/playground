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
            func(i);
            ans = Math.max(ans, memo[i]);
        }
        System.out.println(ans);
    }

    private static int func(int x) {
        if (memo[x] != 0) return memo[x];

        int ans = 1;
        for (int i=0 ; i<x ; i++) {
            if (arr[i] < arr[x]) {
                ans = Math.max(ans, func(i) + 1);
            }
        }
        return memo[x] = ans;
    }

}
