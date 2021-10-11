package com.kkd.study.algorithm.codingtest.yanolja.no3;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

	/**
	 * We consider alphabet with only three letters: "a", "b", "c"
	 *
	 * A string is called diverse if no three consecutive letters are the same.
	 * 세 개의 연속 문자가 동일하지 않으면 문자열을 다양하다고 합니다.
	 *
	 * In other words, a divers string may not contain any of the strings "aaa", "bbb" or "ccc"
	 * 즉, 다이버 문자열에는 "aaa", "bbb" 또는 "ccc" 문자열이 포함될 수 없습니다.
	 *
	 * return any longest possible diverse string containing at most A letters 'a', at most B letters 'b' and at most C letters 'c'.
	 * If there is no possibility of building any string, return empty string.
	 *
	 * @param A
	 * @param B
	 * @param C
	 * @return
	 */

//	private static String result;
//
//	public String solution(int A, int B, int C) {
//		result = "";
//
//		// write your code in Java SE 8
//		if (A > 0) {
//			find(new StringBuilder("a"), A-1, B, C);
//		}
//		if (B > 0) {
//			find(new StringBuilder("b"), A, B-1, C);
//		}
//		if (C > 0) {
//			find(new StringBuilder("c"), A, B, C-1);
//		}
//		return result;
//	}
//
//	private void find(StringBuilder sb, int A, int B, int C) {
//		String str = sb.toString();
//		if (!isDiverse(str)) {
//			return;
//		}
//
//		if (result.length() < str.length()) {
//			result = str;
//		}
//
//		if (A>0) {
//			find(sb.append('a'), A-1, B, C);
//			sb.deleteCharAt(sb.length()-1);
//		}
//
//		if (B>0) {
//			find(sb.append('b'), A, B-1, C);
//			sb.deleteCharAt(sb.length()-1);
//		}
//
//		if (C>0) {
//			find(sb.append('c'), A, B, C-1);
//			sb.deleteCharAt(sb.length()-1);
//		}
//	}
//
//	private boolean isDiverse(String str) {
//		return !str.contains("aaa") && !str.contains("bbb") && !str.contains("ccc");
//	}

	static class Pair {
		private char ch;
		private int count;

		public Pair(Character ch, int count) {
			this.ch = ch;
			this.count = count;
		}
	}

	public String solution(int A, int B, int C) {
		// write your code in Java SE 8

		StringBuilder sb = new StringBuilder();
		Queue<Pair> q = new PriorityQueue<>(
			(count1, count2) -> Integer.compare(count2.count, count1.count));

		if (A > 0)
			q.add(new Pair('a', A));
		if (B > 0)
			q.add(new Pair('b', B));
		if (C > 0)
			q.add(new Pair('c', C));

		while (q.size() > 1) {
			Pair pair1 = q.poll();
			if (pair1.count >= 2) {
				sb.append(pair1.ch);
				sb.append(pair1.ch);
				pair1.count -= 2;
			} else {
				sb.append(pair1.ch);
				pair1.count -= 1;
			}

			Pair pair2 = q.poll();
			if (pair2.count >= 2 && pair1.count < pair2.count) {
				sb.append(pair2.ch);
				sb.append(pair2.ch);
				pair2.count -= 2;
			} else {
				sb.append(pair2.ch);
				pair2.count -= 1;
			}

			if (pair1.count > 0) {
				q.add(pair1);
			}
			if (pair2.count > 0) {
				q.add(pair2);
			}
		}

		if (!q.isEmpty()) {
			if (sb.charAt(sb.length() - 1) != q.peek().ch) {
				if (q.peek().count >= 2) {
					sb.append(q.peek().ch);
					sb.append(q.peek().ch);
				} else {
					sb.append(q.peek().ch);
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String result = solution.solution(6, 1, 1);
		System.out.println(result);
	}
}
