package com.kkd.study.algorithm.leetcode.easy;

public class _171 {
	public int titleToNumber(String columnTitle) {
		char[] arr = columnTitle.toCharArray();
		int res = 0;

		for (int i=0 ; i<arr.length ; i++) {
			int digit = arr.length - i;
			res += (int) Math.pow(26, digit-1) * (arr[i] - 'A' + 1);
		}
		return res;
	}
}
