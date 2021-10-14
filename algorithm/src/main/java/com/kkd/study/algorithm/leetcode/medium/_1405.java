package com.kkd.study.algorithm.leetcode.medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/longest-happy-string/
 */
public class _1405 {

	static class CharNode {
		private char c;
		private int count;

		public CharNode(char c, int count) {
			this.c = c;
			this.count = count;
		}
	}

	public String longestDiverseString(int a, int b, int c) {
		Queue<CharNode> q = new PriorityQueue<>(
			Comparator.<CharNode>comparingInt(x -> x.count).reversed());

		if (a>0) q.offer(new CharNode('a', a));
		if (b>0) q.offer(new CharNode('b', b));
		if (c>0) q.offer(new CharNode('c', c));

		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			CharNode node1 = q.poll();
			if (node1.count > 1) {
				append(sb, node1);
				append(sb, node1);
			} else {
				append(sb, node1);
			}

			if (q.isEmpty()) {
				break;
			}

			CharNode node2 = q.poll();
			if (node2.count > 1 && node1.count < node2.count) {
				append(sb, node2);
				append(sb, node2);
			} else {
				append(sb, node2);
			}

			if (node1.count > 0) {
				q.offer(node1);
			}
			if (node2.count > 0) {
				q.offer(node2);
			}
		}
		return sb.toString();
	}

	private void append(StringBuilder sb, CharNode node) {
		sb.append(node.c);
		node.count--;
	}

	public static void main(String[] args) {
		_1405 program = new _1405();
		String result = program.longestDiverseString(6, 1, 1);
		System.out.println(result);
	}
}
