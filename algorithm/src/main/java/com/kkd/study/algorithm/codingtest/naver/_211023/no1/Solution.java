package com.kkd.study.algorithm.codingtest.naver._211023.no1;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 */
public class Solution {
	static class Pair {
		private char alphabet;
		private int count;

		public Pair(char alphabet, int count) {
			this.alphabet = alphabet;
			this.count = count;
		}
	}

	public String solution(int A, int B, int C) {
		// write your code in Java SE 8
		Queue<Pair> queue = new PriorityQueue<>(
			Comparator.<Pair>comparingInt(x -> x.count).reversed());

		if (A > 0) queue.offer(new Pair('a', A));
		if (B > 0) queue.offer(new Pair('b', B));
		if (C > 0) queue.offer(new Pair('c', C));

		StringBuilder sbuilder = new StringBuilder();
		while (!queue.isEmpty()) {
			Pair pair1 = queue.poll();
			if (pair1.count > 1) {
				appendAlphabet(sbuilder, pair1);
				appendAlphabet(sbuilder, pair1);
			} else {
				appendAlphabet(sbuilder, pair1);
			}

			if (queue.isEmpty()) {
				break;
			}

			Pair pair2 = queue.poll();
			if (pair2.count > 1 && pair1.count < pair2.count) {
				appendAlphabet(sbuilder, pair2);
				appendAlphabet(sbuilder, pair2);
			} else {
				appendAlphabet(sbuilder, pair2);
			}

			if (pair1.count > 0) {
				queue.offer(pair1);
			}
			if (pair2.count > 0) {
				queue.offer(pair2);
			}
		}

		return sbuilder.toString();
	}

	private void appendAlphabet(StringBuilder sbuilder, Pair pair) {
		sbuilder.append(pair.alphabet);
		pair.count--;
	}
}
