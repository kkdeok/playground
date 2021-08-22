package com.kkd.study.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

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

	////////////////////////////////////////// KMP 알고리즘

	static int[] getPi(String pattern) {
		char[] pArr = pattern.toCharArray();
		int[] pi = new int[pArr.length];
		int j=0;
		for (int i=1 ; i<pArr.length ; i++) {
			while (j > 0 && pArr[i] != pArr[j]) {
				// pArr[i] != pArr[j] 일때, j 인덱스의 값에서 i 인덱스의 값과 불일치가 발생했다는 의미이기 때문에
				// 그 전 (0 to j-1) 까지의 부분 문자열 중에서 가장 긴 접두사 == 접미사 의 개수인 pi[j-1] 를 j에
				// 대입한다. 그렇게 했을 때, 만약 개수가 2개라면, pArr[2]가 될테니, 같은 것의 개수 2개 이후인 3번째
				// 부터 i와 비교를 다시 시작하게 되는 것이다.
				j = pi[j-1];
			}
			if (pArr[i] == pArr[j]) {
				// ++j 해주는 이유는 j는 인덱스이고, pi는 같은 prefix suffix 부분 문자열의 개수를 저장해야 하기 때문.
				pi[i] = ++j;
			}
		}
		return pi;
	}

	static List<Integer> kmp(String s, String p) {
		List<Integer> ans = new ArrayList<>();
		int[] pi = getPi(p);

		char[] sArr = s.toCharArray();
		char[] pArr = p.toCharArray();
		int n = sArr.length;
		int m = pArr.length;
		int j = 0;

		for (int i=0 ; i<n ; i++) {
			while (j>0 && sArr[i] != pArr[j]) {
				j = pi[j-1];
			}
			if (sArr[i] == pArr[j]) {
				if (j == m-1) { // 만약, pattern 문자열이 마지막인 경우,
					ans.add(i-m+1); // original 문자열의 현 위치 i를 기준으로, pattern 문자열이 시작되는 index를 저장한다.
					j = pi[j]; // j는 다음 비교를 위해 pi[j] 즉, 0 ~ j까지의 문자열에서 가장 긴 prefix == suffix의 길이를 j의 인덱스로 할당해서 다음 비교를 진행한다.
				} else {
					j++;
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		String haystack = "aaaaa";
		String needle = "bba";
		List<Integer> ans = kmp(haystack, needle);
	}
}
