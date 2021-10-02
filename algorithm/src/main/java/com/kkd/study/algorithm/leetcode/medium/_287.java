package com.kkd.study.algorithm.leetcode.medium;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 */
public class _287 {
	public int findDuplicate(int[] nums) {
		int[] temp = new int[nums.length+1];

		for (int num : nums) {
			temp[num]++;
		}

		for (int i=0 ; i<temp.length ; i++) {
			if (temp[i] > 1) {
				return i;
			}
		}
		return 0;
	}
}
