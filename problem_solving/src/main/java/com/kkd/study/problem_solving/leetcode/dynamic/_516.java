package com.kkd.study.problem_solving.leetcode.dynamic;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class _516 {
	// 팰린드롬
	// d[i] = i 까지의 문자열중에서 가장 긴 팰린드롬.
	// d[0] = 1
	// d[1] = 2
	// d[2] = 3
	// d[3] = 3
	// d[4] = 4
	// d[i] = max(d[i], )

	// d[i][j] = i~j 문자열중에서 가장 긴 팰린드롬.
	// d[i][j] = d[i+1][j-1] + 2 if s[i] == s[j]
	// d[i][j] = max( d[i+1][j], d[i][j-1] )

	private static final int MAX_SIZE = 1001;
	private static int[][] dp = new int[MAX_SIZE][MAX_SIZE];
	private static char[] chars = null;

	public int longestPalindromeSubseq(String s) {
		for (int i = 0; i < MAX_SIZE; i++) {
			Arrays.fill(dp[i], -1);
		}

		chars = s.toCharArray();
		return findLongestPalindromeSubseq(0, s.length() - 1);
	}

	private int findLongestPalindromeSubseq(int i, int j) {
		if (i == j) {
			return 1;
		}
		if (i > j) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		if (chars[i] == chars[j]) {
			return dp[i][j] = findLongestPalindromeSubseq(i + 1, j - 1) + 2;
		} else {
			return dp[i][j] = Math.max(
				findLongestPalindromeSubseq(i, j - 1),
				findLongestPalindromeSubseq(i + 1, j));
		}
	}
}
