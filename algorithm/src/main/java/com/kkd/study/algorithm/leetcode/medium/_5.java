package com.kkd.study.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class _5 {
	private int[][] memo;

	public String longestPalindrome(String s) {
		int len = s.length();
		memo = new int[len][len];

		for (int i=0 ; i<len ; i++) {
			Arrays.fill(memo[i], -1);
		}

		char[] sArr = s.toCharArray();

		int lidx = 0;
		int ridx = 0;
		for (int i=0 ; i<len ; i++) {
			for (int j=i ; j<len ; j++) {
				if (isPalindrome(sArr, i, j)) {
					if (ridx - lidx <= j - i) {
						lidx = i;
						ridx = j;
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i=lidx ; i<=ridx ; i++) {
			sb.append(sArr[i]);
		}
		return sb.toString();
	}

	private boolean isPalindrome(char[] sArr, int lidx, int ridx) {
		if (lidx == ridx || lidx > ridx) return true;

		if (memo[lidx][ridx] != -1) {
			return memo[lidx][ridx] == 1;
		}

		memo[lidx][ridx] = sArr[lidx] != sArr[ridx] ? 0
			: (isPalindrome(sArr, lidx+1, ridx-1) ? 1 : 0);

		return memo[lidx][ridx] == 1;
	}

	public static void main(String[] args) {
		_5 program = new _5();
		String result = program.longestPalindrome("cbbd");
		System.out.println(result);
	}
}
