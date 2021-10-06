package com.kkd.study.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/
 */
public class _217 {
//	public boolean containsDuplicate(int[] nums) {
//		Set<Integer> set = new HashSet<>();
//		for (int num : nums) {
//			if (set.add(num)) {
//				return true;
//			}
//			set.add(num);
//		}
//		return false;
//	}

	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (!set.add(num)) {
				return true;
			}
		}
		return false;
	}
}
