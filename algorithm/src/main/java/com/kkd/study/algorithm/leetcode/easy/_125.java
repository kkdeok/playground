package com.kkd.study.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/valid-palindrome/submissions/
 */
public class _125 {
	public boolean isPalindrome(String s) {
		s = s.toLowerCase();
		char[] arr = s.toCharArray();
		List<Character> list = new ArrayList<>();
		for (char c : arr) {
			if ( (c >= 'a' && c<= 'z') || (c >= '0' && c <= '9')) {
				list.add(c);
			}
		}

		int l = 0;
		int r = list.size() - 1;
		while (l < r) {
			if (list.get(l) != list.get(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
}
