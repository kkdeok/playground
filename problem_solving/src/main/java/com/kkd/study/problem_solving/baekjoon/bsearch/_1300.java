package com.kkd.study.problem_solving.baekjoon.bsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1300
 */
public class _1300 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		System.out.println(process(n, k));
	}

	public static long process(int n, int k) {
		long left = 1, right = k;
		while (left <= right) {
			long mid = (left + right) / 2;
			int cnt = 0;
			for (int i = 1; i <= n; i++) {
				cnt += Math.min(mid/i, n);
			}
			
			if (cnt >= k) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}
