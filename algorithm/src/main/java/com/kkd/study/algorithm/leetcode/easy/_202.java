package com.kkd.study.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 */
public class _202 {

	public static void main(String[] args) {
		_202 program = new _202();
		System.out.println(program.isHappy(2));
	}

	public boolean isHappy(int n) {
		if (n == 1) return true;

		Set<Long> set = new HashSet<>();
		long tmp = n;
		set.add(tmp);

		while (tmp != 1) {
			long sum = 0L;
			for (char c : String.valueOf(tmp).toCharArray()) {
				sum += Math.pow((c - '0'), 2);
			}
			if (set.contains(sum)) {
				return false;
			}
			set.add(sum);
			tmp = sum;
		}
		return true;
	}
}
