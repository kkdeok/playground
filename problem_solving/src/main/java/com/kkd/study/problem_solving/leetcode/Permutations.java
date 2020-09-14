package com.kkd.study.problem_solving.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/permutations/
 */
public class Permutations {
	public static void main(String[] args) {
		List<List<Integer>> ret = new Solution().permute(new int[]{});
	}

	static class Solution {
		private List<List<Integer>> ret = new ArrayList<>();
		private int[] check;
		private int[] tmp;

		public List<List<Integer>> permute(int[] nums) {
			int len = nums.length;
			check = new int[len];
			tmp = new int[len];

			for (int i=0 ; i<len ; i++) {
				dfs(0, i, nums);
			}
			return ret;
		}

		private void dfs (int tmpIdx, int idx, int[] nums) {
			check[idx] = 1;
			tmp[tmpIdx] = nums[idx];

			if (isFinished()) {
				ret.add(Arrays.stream(tmp).boxed().collect(Collectors.toList()));
			} else {
				int len = nums.length;
				for (int i=0 ; i<len ; i++) {
					if (check[i] == 0) {
						dfs(tmpIdx + 1, i, nums);
					}
				}
			}
			tmp[tmpIdx] = 0;
			check[idx] = 0;
		}

		private boolean isFinished() {
			int len = check.length;
			int i;
			for (i=0 ; i<len ; i++) {
				if (check[i] == 0) {
					break;
				}
			}
			return i == len;
		}
	}
}