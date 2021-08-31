package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class _121 {
	// NOT WORK!
	public int maxProfit(int[] prices) {
		int l = 0;
		int r = prices.length-1;
		int res = 0;
		while (l<r) {

			if (res < prices[r] - prices[l])
				res = prices[r] - prices[l];

			int i = l+1;
			while (i<r) {
				if (prices[l] > prices[i]) {
					break;
				} else {
					i++;
				}
			}
			l = i;

			i = r-1;
			while (l<i) {
				if (prices[r] < prices[i]) {
					break;
				} else {
					i--;
				}
			}
			r = i;
		}
		return res;
	}
}
