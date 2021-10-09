package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/power-of-three/
 */
public class _326 {
	public boolean isPowerOfThree(int n) {
		while (n > 1 && n % 3 == 0) {
			n/=3;
		}
		return n == 1;
	}
}
