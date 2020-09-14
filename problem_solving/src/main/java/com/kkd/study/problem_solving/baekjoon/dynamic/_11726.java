package com.kkd.study.problem_solving.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _11726 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int size = 1001;
    private static int[] memo = new int[size];
    
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        memo[1] = 1;
        memo[2] = 2;
        for (int i=3 ; i<=n ; i++) {
            memo[i] = (memo[i-1] + memo[i-2]) % 10007;
        }
        System.out.println(memo[n]);
    }
}
