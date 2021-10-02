package com.kkd.study.algorithm.leetcode.medium;

import java.util.Arrays;

public class _1395 {
	private static int[][] memo;
	private static boolean isIncMode;

	public int numTeams(int[] rating) {
		int n = rating.length;
		int res = 0;
		initMemo(n);
		isIncMode = true;
		for (int i = n - 1; i >= 2; i--) {
			res += find(rating, i, 2);
		}

		initMemo(n);
		isIncMode = false;
		for (int i = n - 1; i >= 2; i--) {
			res += find(rating, i, 2);
		}

		return res;
	}

	private void initMemo(int n) {
		memo = new int[n][4];
		for (int i = 0; i < n; i++) {
			Arrays.fill(memo[i], -1);
		}
	}

	private int find(int[] rating, int n, int k) {
		if (k == 0)
			return 1;
		if (n < 0 || k < 0)
			return 0;

		if (memo[n][k] != -1)
			return memo[n][k];

		int res = 0;
		if (isIncMode) {
			for (int i = n - 1; i >= 0; i--) {
				if (rating[n] > rating[i]) {
					res += find(rating, i, k - 1);
				}
			}
		} else {
			for (int i = n - 1; i >= 0; i--) {
				if (rating[n] < rating[i]) {
					res += find(rating, i, k - 1);
				}
			}
		}
		return memo[n][k] = res;
	}
}
