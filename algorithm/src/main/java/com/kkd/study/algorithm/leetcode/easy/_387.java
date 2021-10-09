package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 */
public class _387 {
	public int firstUniqChar(String s) {
		char[] charArr = s.toCharArray();
		int[] cntArr = new int[27];

		for (char c : charArr) {
			cntArr[c - 'a']++;
		}

		for (int i=0 ; i<charArr.length ; i++) {
			if (cntArr[charArr[i] - 'a'] == 1) {
				return i;
			}
		}

		return -1;
	}
}
