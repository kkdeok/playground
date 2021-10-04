package com.kkd.study.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 */
public class _22 {
	private char[] buffer;

	public List<String> generateParenthesis(int n) {
		int left = n;
		int right = n;

		char[] buffer = new char[n*2];
		List<String> result = new ArrayList<>();

		find(left-1, right, '(', buffer, 0, result);

		return result;
	}

	private void find(int left, int right, char c, char[] buffer, int idx, List<String> result) {
		buffer[idx] = c;

		if (left == 0 && right == 0) {
			result.add(String.valueOf(buffer));
			return;
		}

		if (left == 0) {
			find(left, right - 1, ')', buffer, idx+1, result);
		} else if (left > right-1) {
			find(left - 1, right, '(', buffer, idx+1, result);
		} else {
			find(left - 1, right, '(', buffer, idx+1, result);
			find(left, right - 1, ')', buffer, idx+1, result);
		}
	}
}
