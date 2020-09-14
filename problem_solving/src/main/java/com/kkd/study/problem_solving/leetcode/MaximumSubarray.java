package com.kkd.study.problem_solving.leetcode;

/**
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {
	public static void main(String[] args) {
		int ret = new Solution().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
		System.out.println(ret);
	}

	static class Solution {
		public int maxSubArray(int[] nums) {
			int max = Integer.MIN_VALUE;
			for (int i=0 ; i<nums.length ; i++) {
				int sum = 0;
				for (int j=i ; j<nums.length ; j++) {
					sum += nums[j];
					max = Math.max(max, sum);
				}
			}
			return max;
		}
	}
}
