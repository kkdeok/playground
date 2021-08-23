package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/climbing-stairs/
 */
public class _70 {
	private int[] memo = new int[46];

	public int climbStairs(int n) {
		if (n == 0) return 1;
		if (n < 0) return 0;
		if (memo[n] != 0) return memo[n];

		return memo[n] = climbStairs(n-1) + climbStairs(n-2);
	}
}
