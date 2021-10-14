package com.kkd.study.algorithm.codingtest.yanolja.no4;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/number-of-ways-to-split-a-string/discuss/863226/JAVA-0(n)-Simple-code-with-comment
 */
public class Solution {

	/**
	 * string S consisting of letters 'a' and 'b'.
	 *
	 * You want to split it into three separate non-empty parts.
	 * 비어 있지 않은 세 부분으로 분할하려고 합니다.
	 *
	 * The length of the parts can differ from one another.
	 * 부분의 길이는 서로 다를 수 있습니다.
	 *
	 * In how many ways can you split S into three parts, such that each part contains the same number of letters 'a'?
	 * 각 부분에 동일한 수의 문자 'a'가 포함되도록 S를 세 부분으로 나누는 방법은 몇 가지입니까?
	 *
	 * S의 길이 = N [1.. 40,000]
	 * S는 오직 'a' 'b' 만을 포함한다.
	 *
	 * @param S
	 * @return
	 */

	public int solution(String S) {
		// write your code in Java SE 8
		int len = S.length();
		int cntA = getNumberOfA(S);

		if (!isPossibleToSplitThreeGroup(len, cntA)) {
			return 0;
		}

		if (cntA == 0) {
			return ((len-1) * (len-2)) / 2;
		}

		int currCnt = 0;
		int delta = 0;
		for (int i=0; i<len ; i++) {
			if (S.charAt(i) == 'a') {
				currCnt++;
			}
			if (currCnt == cntA / 3) {
				delta++;
			} else if (currCnt > cntA / 3) {
				break;
			}
		}

		currCnt = 0;
		int num = 0;
		for (int i = len-1 ; i>=0 ; i--) {
			if (S.charAt(i) == 'a') {
				currCnt++;
			}
			if (currCnt == cntA / 3) {
				num++;
			} else if (currCnt > cntA / 3) {
				break;
			}
		}

		return delta * num;
	}

	private int getNumberOfA(String S) {
		int cnt = 0;
		for (char c : S.toCharArray()) {
			if (c == 'a') {
				cnt++;
			}
		}
		return cnt;
	}

	private boolean isPossibleToSplitThreeGroup(int len, int cntA) {
		return len >= 3 && cntA % 3 == 0;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int result = solution.solution("abbabbbababbaab");
		System.out.println(result);
	}
}
