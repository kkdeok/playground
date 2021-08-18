package com.kkd.study.problem_solving.leetcode.easy;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class _14 {

	public String longestCommonPrefix(String[] strs) {
		int minLen = Integer.MAX_VALUE;

		// get string min length.
		for (int i=0 ; i<strs.length ; i++) {
			if (strs[i].length() < minLen) {
				minLen = strs[i].length();
			}
		}

		int commonPrefixIndex = -1;
		for (int i=0 ; i<minLen ; i++) {
			char c = strs[0].charAt(i);

			int j;
			for (j=1 ; j<strs.length ; j++) {
				if (c != strs[j].charAt(i)) {
					break;
				}
			}

			if (j != strs.length) {
				break;
			} else {
				commonPrefixIndex++;
			}
		}

		return commonPrefixIndex == -1 ? "" : strs[0].substring(0, commonPrefixIndex+1);
	}
}
