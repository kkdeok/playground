package com.kkd.study.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/missing-number/
 */
public class _268 {
	public int missingNumber(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length;
		for (int i=0 ; i<n ; i++) {
			if (nums[i] != i) return i;
		}
		return n;
	}
}
