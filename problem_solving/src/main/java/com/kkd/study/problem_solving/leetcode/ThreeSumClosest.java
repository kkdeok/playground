package com.kkd.study.problem_solving.leetcode;

/**
 * https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumClosest {
	public static void main(String[] args) {
		int ret = new Solution().threeSumClosest(new int[]{-1,2,1,-4}, 1);
		System.out.println(ret);
	}

	static class Solution {
		public int threeSumClosest(int[] nums, int target) {
			int closet = Integer.MAX_VALUE;
			int diff = Integer.MAX_VALUE;
			for (int i=0 ; i<nums.length ; i++) {
				for (int j=i+1 ; j<nums.length ; j++) {
					for (int k=j+1 ; k<nums.length ; k++) {
						int sum = nums[i] + nums[j] + nums[k];
						if (diff > Math.abs(target - sum)) {
							diff = Math.abs(target - sum);
							closet = sum;
						}
					}
				}
			}
			return closet;
		}
	}
}
