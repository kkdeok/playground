package com.kkd.study.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/10844
 */
public class _10884 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static long[][] memo = new long[101][10];
    private static final int mod = 1000000000; 
    
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        
        long ans = 0;
        for (int i=0 ; i<=9 ; i++) {
            ans = (ans + doDP(n, i)) % mod;
        }
        System.out.println(ans);
    }
    
    private static long doDP(int d, int i) {
        if (i>9 || i<0) {
            return 0;
        }
        if (d == 1) {
            return i == 0 ? 0 : 1;
        }
        
        if (memo[d][i] != 0) {
            return memo[d][i];
        }
        return memo[d][i] = ((doDP(d-1, i+1) % mod) + (doDP(d-1, i-1) % mod)) % mod;
    } 
}
