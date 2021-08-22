package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/sqrtx/
 */
public class _69 {

	public static int mySqrt(int x) {
		// base rule
		if (x == 0) return 0;
		if (x == 1) return 1;

		long l = 1;
		long r = x;

		while (l < r) {
			long mid = (l + r) / 2;
			long tmp = mid * mid;

			if (tmp < x) l = mid + 1;
			else if (tmp == x) return (int) mid;
			else r = mid - 1;
		}
		return (int) l-1;
	}

	public static void main(String[] args) {
		System.out.println(mySqrt(3));
	}
}
