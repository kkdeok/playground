package com.kkd.study.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-anagram/
 */
public class _242 {
	// s의  철철자  순순서 바꿔서 t 만만들  수  있있으으면  true
	public boolean isAnagram(String s, String t) {
		int[] alphabet = new int[30];
		Arrays.fill(alphabet, 0);

		if (s.length() != t.length()) {
			return false;
		}

		for (char c : s.toCharArray()) {
			alphabet[c - 'a']++;
		}

		for (char c: t.toCharArray()) {
			if (--alphabet[c - 'a'] < 0) {
				return false;
			}
		}
		return true;
	}
}
