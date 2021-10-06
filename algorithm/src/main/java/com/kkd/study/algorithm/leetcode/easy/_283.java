package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/move-zeroes/
 */
public class _283 {
//	public void moveZeroes(int[] nums) {
//		int n = nums.length;
//
//		for (int i=n-1 ; i>=0 ; i--) {
//			if (nums[i] == 0) {
//				for (int j=i+1 ; j<n ; j++) {
//					nums[j-1] = nums[j];
//				}
//				nums[n-1] = 0;
//			}
//		}
//	}

	public void moveZeroes(int[] nums) {
		int n = nums.length;
		int idx = 0;

		for (int i=0 ; i<n ; i++) {
			if (nums[i] != 0) {
				nums[idx++] = nums[i];
			}
		}

		while (idx< n) {
			nums[idx++] = 0;
		}
	}
}
