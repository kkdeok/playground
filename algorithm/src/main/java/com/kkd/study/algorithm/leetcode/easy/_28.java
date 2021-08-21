package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/implement-strstr/
 */
public class _28 {

	// O(n^2) -> It has actually same time complexity with String.indexOf();
	public int strStr(String haystack, String needle) {
		char[] haystackArr = haystack.toCharArray();
		char[] needleArr = needle.toCharArray();

		int hLen = haystack.length();
		int nLen = needle.length();

		if (nLen == 0) {
			return 0;
		}

		for (int i=0 ; i<= hLen - nLen ; i++) {
			int j, k=0;
			for (j=0 ; j<nLen ; j++) {
				if (haystackArr[i+k] != needleArr[j]) {
					break;
				}
				k++;
			}
			if (j == nLen) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String str = "aaab";
		str.indexOf("aab");
	}
}
