package com.kkd.study.problem_solving.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class _128 {
	// describe:
	// Given an unsorted array of integers, 
	// find the length of the longest consecutive elements sequence.
	// Your algorithm should run in O(n) complexity.

	// check:
	// Q. array.length?   unknown
	// Q. same number exists? yes

	// idea 1: Priority Queue O(n*logn), But logn should be small enough to be ignored.
	public int longestConsecutive(int[] nums) {
		// base condition
		if (nums == null || nums.length == 0)
			return 0;

		// Priority Queue O(n*logn), But logn should be small enough to be ignored.
		Queue<Integer> q = new PriorityQueue<>();
		for (int num : nums) {
			q.offer(num);
		}

		int ret = 0;
		int cnt = 1;
		int prev = q.element();
		while (!q.isEmpty()) {
			int curr = q.poll();
			if (prev == curr) {
				continue;
			} else if (prev + 1 == curr) {
				cnt++;
			} else {
				ret = Math.max(ret, cnt);
				cnt = 1;
			}
			prev = curr;
		}
		return Math.max(ret, cnt);
	}
}
