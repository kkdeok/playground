package com.kkd.study.algorithm.leetcode.medium;

import com.kkd.study.algorithm.leetcode.common.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class _116 {
	public Node connect(Node root) {
		if (root == null) {
			return null;
		}

		Queue<Node> q = new LinkedList<>();
		q.add(root);
		int count = 1;
		int height = 0;
		Node leftNode = null;
		while (!q.isEmpty()) {
			Node node = q.poll();
			count--;

			if (count == 0) {
				if (leftNode != null) {
					leftNode.next = node;
				}
				count = (int) Math.pow(2, ++height);
				leftNode = null;
			} else {
				if (leftNode == null) {
					leftNode = node;
				} else {
					leftNode.next = node;
					leftNode = node;
				}
			}

			if (node.left != null) {
				q.offer(node.left);
			}
			if (node.right != null) {
				q.offer(node.right);
			}
		}
		return root;
	}
}
