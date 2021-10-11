package com.kkd.study.algorithm.codingtest.yanolja.no1;


public class Solution {
	private static final char DEFAULT_CHAR = 'a';

	public String solution(String S) {
		// write your code in Java SE 8
		char[] charArr = S.toCharArray();
		int len = charArr.length;

		int lIdx = len % 2 == 0 ? len / 2 - 1 : len / 2;
		int rIdx = len / 2;

		while (lIdx>=0 && rIdx<len) {
			processQuestionMark(charArr, lIdx, rIdx);
			if (charArr[lIdx] == charArr[rIdx]) {
				lIdx--;
				rIdx++;
			} else {
				return "NO";
			}
		}
		return String.valueOf(charArr);
	}

	private void processQuestionMark(char[] charArr, int lIdx, int rIdx) {
		if (charArr[lIdx] == '?' && charArr[rIdx] == '?') {
			charArr[lIdx] = charArr[rIdx] = DEFAULT_CHAR;
		} else if (charArr[lIdx] == '?') {
			charArr[lIdx] = charArr[rIdx];
		} else if (charArr[rIdx] == '?') {
			charArr[rIdx] = charArr[lIdx];
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();

		StringBuilder sb = new StringBuilder();
		for (int i=1 ; i<=100 ; i++) {
			sb.append("askqw");
		}

		String result = solution.solution("q");
		System.out.println(result);
	}
}
