package com.kkd.exercise.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/permutations-ii/
 */
public class PermutationUnique {
	public static void main(String[] args) {
		List<List<Integer>> ret = new Solution().permuteUnique(new int[]{1, 1, 2});

		for (List<Integer> list : ret) {
			System.out.println(list);
		}
	}


	static class Solution {
		private Set<List<Integer>> set = new HashSet<>();
		private boolean[] used;

		public List<List<Integer>> permuteUnique(int[] nums) {
			used = new boolean[nums.length];
			List<Integer> tmpList = new ArrayList<>();
			backTrack(nums, tmpList);

			List<List<Integer>> response = new ArrayList<>();
			for (List<Integer> list : set) {
				response.add(list);
			}
			return response;
		}

		private void backTrack(int[] nums, List<Integer> tmpList) {
			if (tmpList.size() == nums.length) {
				set.add(new ArrayList<>(tmpList));
				return;
			}

			for (int i=0 ; i<nums.length ; i++) {
				if (used[i] == false) {
					tmpList.add(nums[i]);
					used[i] = true;
					backTrack(nums, tmpList);
					used[i] = false;
					tmpList.remove(tmpList.size() - 1);
				}
			}
		}
	}
}
