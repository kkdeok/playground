package com.kkd.study.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 */
public class _39 {
	private List<List<Integer>> result;

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		result = new ArrayList<>();
		List<Integer> buffer = new ArrayList<>();

		find(0, candidates, buffer, target);
		return result;
	}


	private void find(int idx, int[] candidates, List<Integer> buffer, int target) {
		if (target == 0) {
			result.add(new ArrayList<>(buffer));
			return;
		}
		if (idx == candidates.length) {
			return;
		}

		int num = candidates[idx];
		for (int i=0 ; num*i <= target ; i++) {
			for (int j=0 ; j<i ; j++) {
				buffer.add(num);
			}

			int newTarget = target - (num*i);
			if (newTarget >= 0) {
				find(idx+1, candidates, buffer, newTarget);
			}

			for (int j=0 ; j<i ; j++) {
				buffer.remove(buffer.size()-1);
			}
		}
	}
}
