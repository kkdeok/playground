package com.kkd.study.problem_solving.leetcode;

/**
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {

	public static void main(String[] args) {
		int[] ret = new Solution().twoSum(new int[]{2,7,11,15}, 9);
		for (int i=0 ; i<ret.length ; i++) {
			System.out.println(ret[i]);
		}
	}


	static class Solution {
		public int[] twoSum(int[] nums, int target) {
			int len = nums.length;
			for (int i=0 ; i<len ; i++) {
				for (int j=i+1 ; j<len ; j++) {
					if (nums[i] + nums[j] == target) {
						return new int[]{i, j};
					}
				}
			}
			return new int[]{};
		}
	}
}
