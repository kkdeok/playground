package com.kkd.study.problem_solving.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 */
public class _15 {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();

		// sort list
		Arrays.sort(nums);

		for (int i = 0; i + 2 < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue; // skip same result
			}

			int j = i + 1;
			int k = nums.length - 1;
			int target = -nums[i];
			while (j < k) {
				if (nums[j] + nums[k] == target) {
					res.add(Arrays.asList(nums[i], nums[j], nums[k]));
					j++;
					k--;

					// if j++ is same with previous j,
					while (k > j && nums[j] == nums[j - 1]) {
						j++;
					}

					// if k-- is same with previous k,
					while (k > j && nums[k] == nums[k + 1]) {
						k--;
					}
				} else if (target < nums[j] + nums[k]) {
					k--;
				} else {
					j++;
				}
			}
		}
		return res;
	}
}
