package com.kkd.study.problem_solving.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/reverse-integer/
 */
public class ReverseInteger {

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.reverse(Integer.MIN_VALUE));
	}


	private static class Solution {
		public int reverse(int x) {
			if (x == 0) {
				return x;
			}
			boolean isNegative = x < 0;
			x = Math.abs(x);
			List<String> list = new ArrayList<>();
			while (x != 0) {
				int tmp = x % 10;
				list.add(String.valueOf(tmp));
				x /= 10;
			}
			StringBuilder sb = new StringBuilder();
			for (String s : list) {
				sb.append(s);
			}
			try {
				int ret = Integer.parseInt(sb.toString());
				return isNegative ? ret * -1 : ret;
			} catch (NumberFormatException e) {
				return 0;
			}
		}
	}
}
