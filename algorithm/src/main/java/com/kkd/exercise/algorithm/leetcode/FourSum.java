package com.kkd.exercise.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/4sum/
 */
public class FourSum {
	public static void main(String[] args) {
		List<List<Integer>> ret = new Solution().fourSum(new int[]{2,-4,-5,-2,-3,-5,0,4,-2}, -14);

		for (int i=0 ; i<ret.size() ; i++) {
			int len = ret.get(i).size();
			for (int j=0 ; j<len ; j++) {
				System.out.print(ret.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	static class Solution {
		public List<List<Integer>> fourSum(int[] nums, int target) {
			int len = nums.length;
			List<List<Integer>> list = new ArrayList<>();

			for (int i=0 ; i<len ; i++) {
				for (int j=i+1 ; j<len ; j++) {
					for (int k=j+1 ; k<len ; k++) {
						for (int z=k+1 ; z<len ; z++) {
							int sum = nums[i] + nums[j] + nums[k] + nums[z];
							if (target == sum) {
								List<Integer> tmp = new ArrayList<>();
								tmp.add(nums[i]);
								tmp.add(nums[j]);
								tmp.add(nums[k]);
								tmp.add(nums[z]);

								boolean isExists = false;
								int listLen = list.size();
								Set<Integer> tmpSet = new HashSet<>(tmp);
								for (int t=0 ; t<listLen ; t++) {
									if (new HashSet<>(list.get(t)).equals(tmpSet)) {
										isExists = true;
										break;
									}
								}
								if (!isExists) {
									list.add(tmp);
								}
							}
						}
					}
				}
			}
			return list;
		}
	}
}