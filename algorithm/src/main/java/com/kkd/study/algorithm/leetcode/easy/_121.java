package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class _121 {
	// Try 1) NOT WORK!
//	public int maxProfit(int[] prices) {
//		int l = 0;
//		int r = prices.length-1;
//		int res = 0;
//		while (l<r) {
//
//			if (res < prices[r] - prices[l])
//				res = prices[r] - prices[l];
//
//			int i = l+1;
//			while (i<r) {
//				if (prices[l] > prices[i]) {
//					break;
//				} else {
//					i++;
//				}
//			}
//			l = i;
//
//			i = r-1;
//			while (l<i) {
//				if (prices[r] < prices[i]) {
//					break;
//				} else {
//					i--;
//				}
//			}
//			r = i;
//		}
//		return res;
//	}

	// Try 2) brute force -> time out
//	public int maxProfit(int[] prices) {
//		int max = 0;
//		for (int i=0 ; i<prices.length ; i++) {
//			int sell = prices[i];
//			for (int j=i+1 ; j<prices.length ; j++) {
//				if (prices[j] - sell > max) {
//					max = prices[j] - sell;
//				}
//			}
//		}
//		return max;
//	}

	// Try 3)
	public int maxProfit(int prices[]) {
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i=0 ; i<prices.length ; i++) {
			if (prices[i] < minPrice)
				minPrice = prices[i];
			else if (prices[i] - minPrice > maxProfit)
				maxProfit = prices[i] - minPrice;
		}
		return maxProfit;
	}
}
