package com.doubleknd26.exercise.algorithm.leetcode;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		int ret = new Solution().lengthOfLongestSubstring("");
		System.out.println(ret);
	}

	static class Solution {
		public int lengthOfLongestSubstring(String s) {
			char[] c = s.toCharArray();
			int len = s.length();
			int maxLen = 0;
			for (int i = 0; i < len - maxLen; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j=i ; j<len ; j++) {
					boolean isExists = false;
					for (int k=i ; k<j ; k++) {
						if (c[k] == c[j]) {
							isExists = true;
							break;
						}
					}
					if (isExists) {
						break;
					} else {
						sb.append(c[i]);
					}
				}
				maxLen = Math.max(maxLen, sb.toString().length());
			}
			return maxLen;
		}
	}
}