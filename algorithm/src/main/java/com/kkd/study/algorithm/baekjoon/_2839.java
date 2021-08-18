package com.kkd.study.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2839
 */
public class _2839 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());

		int ans = Integer.MAX_VALUE;
		for (int i=0 ; n-(5*i) >= 0 ; i++) {
			int remain = n - (5 * i);
			
			if (remain % 3 == 0) {
				ans = Math.min(ans , i + (remain/3));
			}
		}
		System.out.println( ans == Integer.MAX_VALUE ? -1 : ans );
	}
}
