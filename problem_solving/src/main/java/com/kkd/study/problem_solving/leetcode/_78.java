package com.kkd.study.problem_solving.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/subsets/
 */
public class _78 {
	// PROBLEM
	// Given a set of distinct integers, nums, return all possible subsets (the power set).

	// CHECK
	// Q. What is power set meaning? -> maybe includes empty or all subsets?
	// Q. duplicate subsets?         -> The solution set must not contain duplicate subsets.
	// Q. order of return list?      -> seems it doesn't matter.
	// Q. input size?                -> unlimited.

	// Idea 1.
	// 1. visit every number and treats it as a start of array.
	// 2. from the start, check all possible subsets.
	// 2.1 check all possible subsets -> check subset size -> 1, 2, ... n - idx
	// e.g) 1,2,3 idx=0 -> [1], [1,2], [1,2,3], **[1,3]**
	// e.g) 1,2,3 idx=1 -> [2], [2,3]
	// e.g) 1,2,3 idx=2 -> [3]
	// e.g)             -> []

	// Idea 2.
	// 3C0 + 3C1 + 3C2 + 3C3
	// 1   + 3   + 3   + 1
	// This is impossible. We need to return subsets, not the number of subsets.

	// Idea 3.
	// remove duplicate computation.
	// 1,2,3
	// 1   -> [1] 2, [1] 3
	// 1 2 -> [1 2] 3
	// 1 3 -> x
	// 1 2 3 -> x
	// T.C: O(n * 2^n( This is how we get the number of subsets) )
	public List<List<Integer>> subsets(int[] nums) {
		Queue<List<Integer>> queue = new LinkedList<>();
		Queue<Integer> iqueue = new LinkedList<>();

		queue.offer(new ArrayList<>());
		iqueue.offer(-1);

		List<List<Integer>> ret = new ArrayList<>();
		while (!queue.isEmpty()) {
			List<Integer> list = queue.poll();
			int idx = iqueue.poll();

			ret.add(list);

			// n * 2^2
			for (int i = idx + 1; i < nums.length; i++) {
				List<Integer> temp = new ArrayList<>(list);
				temp.add(nums[i]);
				queue.offer(temp);
				iqueue.offer(i);
			}
		}
		return ret;
	}
}
