package com.kkd.study.problem_solving.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _11052 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static int size = 1001;
    private static int len;
    private static int[] p = new int[size];
    private static int[] memo = new int[size];
    
    public static void main(String[] args) throws Exception {
        String[] line = br.readLine().split(" ");
        len = Integer.parseInt(line[0]);
        line = br.readLine().split(" ");
        
        for (int i=1 ; i<=len ; i++) {
            p[i] = Integer.parseInt(line[i-1]);
        }
        int ans = doDP(len);
        System.out.println(ans);
    }
    
    private static int doDP(int n) {
        if (n==0) return 0;
        if (memo[n] != 0) return memo[n];
        
        int count = 0;
        for (int i=len ; i>=1 ; i--) {
            if (n-i >= 0) {
                count = Math.max(count, doDP(n - i) + p[i]);
            }
        }
        return memo[n] = count;
    }
}
