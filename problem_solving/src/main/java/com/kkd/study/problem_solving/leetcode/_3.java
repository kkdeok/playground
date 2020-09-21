package com.kkd.study.problem_solving.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class _3 {
	
	class Solution {
		// CHECK
		// 반복 문자열이 없는 가장 긴 substring을 찾아라.
		// Q. 0 <= s.length <= 5 * 10^4
		// Q. s consists of English letters, digits, symbols and spaces.

		// IDEA 1.
		// 1) interate from the start.
		// 2) put char into the set. -> list
		// 3) if char is already in the set, get set size, restart from duplicated idx + 1.
		// T.C: O(n) n = s.length();

		// IDEA 2.
		// 1) iterate from the start with two pointers. s
		// 2) if s == e || !set.contains(arr[e+1]) e++;

		// IMPLEMENTATION
		public int lengthOfLongestSubstring(String s) {
			Set<Character> set = new HashSet<>();
			int ret = 0;
			char[] chars = s.toCharArray();
			int sp = 0, ep = 0;
			while (true) {
				if (ep == s.length()) break;

				if (sp == ep || !set.contains(chars[ep])) {
					set.add(chars[ep++]);
				} else {
					ret = Math.max(ret, ep - sp);

					for (int i=sp ; i<ep ; i++) {
						if (chars[i] == chars[ep]) {
							sp=i+1;
							break;
						}
						set.remove(chars[i]);
					}
					set.add(chars[ep++]);
				}
			}
			return Math.max(ret, ep - sp);
		}
	}
}
