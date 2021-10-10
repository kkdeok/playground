package com.kkd.study.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 */
public class _322 {
	private int[] memo;

	public int coinChange(int[] coins, int amount) {
		if (amount < 1) return 0;

		memo = new int[10001];
		Arrays.fill(memo, -2);
		return find(coins, amount);
	}

	private int find(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		if (amount < 0) {
			return -1;
		}

		if (memo[amount] != -2) {
			return memo[amount];
		}

		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = find(coins, amount - coin);
			if (res >= 0 && res < min) {
				min = 1 + res;
			}
		}
		return memo[amount] = (min == Integer.MAX_VALUE) ? -1 : min;
	}
}
