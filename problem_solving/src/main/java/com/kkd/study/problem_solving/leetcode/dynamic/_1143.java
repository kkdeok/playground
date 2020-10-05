package com.kkd.study.problem_solving.leetcode.dynamic;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 */
public class _1143 {
	// DESCRIBE:
	// return the length of their longest common subsequece.

	// CHECK:
	// 
	// 1 <= text1.length <= 1000
	// 1 <= text2.length <= 1000
	// lowercase English characters only.

	// IDEA 1: Two Pointers
	// abcde ace
	// i     j
	//   i    j
	//     i   j

	// IDEA 2: Dynamic Programming
	// d[x][y] = text1[x] , text2[y]
	// d[x][y] = d[x-1][y-1] + 1 if text1[x] == text2[y]
	// d[x][y] = max(d[x-1][y], d[x][y-1]) otherwise.

	private static final int MAX_SIZE = 1001;
	private static int[][] memo = new int[MAX_SIZE][MAX_SIZE];
	private static char[] a = null;
	private static char[] b = null;

	public int longestCommonSubsequence(String text1, String text2) {
		for (int i = 0; i < MAX_SIZE; i++) {
			Arrays.fill(memo[i], -1);
		}

		a = text1.toCharArray();
		b = text2.toCharArray();

		return find(text1.length() - 1, text2.length() - 1);
	}

	private int find(int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}

		if (memo[i][j] != -1) {
			return memo[i][j];
		}

		if (a[i] == b[j]) {
			return memo[i][j] = find(i - 1, j - 1) + 1;
		} else {
			return memo[i][j] = Math.max(find(i, j - 1), find(i - 1, j));
		}
	}
}
