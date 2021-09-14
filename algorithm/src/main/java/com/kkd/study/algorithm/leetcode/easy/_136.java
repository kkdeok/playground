package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/single-number/
 */
public class _136 {
	public int singleNumber(int[] nums) {
		int size = 30001;
		int[] positives = new int[size];
		int[] negatives = new int[size];
		for (int num : nums) {
			if (num < 0) {
				negatives[-num]++;
			} else {
				positives[num]++;
			}
		}

		for (int i=0 ; i<size ; i++) {
			if (negatives[i] == 1) return -i;
			if (positives[i] == 1) return i;
		}
		return 0;
	}
}
