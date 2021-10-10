package com.kkd.study.algorithm.leetcode.medium;

import com.kkd.study.algorithm.leetcode.common.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 */
public class _117 {
	public Node connect(Node root) {
		if (root == null) {
			return null;
		}

		Queue<Node> nodeQueue = new LinkedList<>();
		Queue<Integer> heightQueue = new LinkedList<>();

		nodeQueue.offer(root);
		heightQueue.offer(1);

		Node prevNode = null;
		int prevHeight = 0;
		while (!nodeQueue.isEmpty()) {
			Node node = nodeQueue.poll();
			int height = heightQueue.poll();

			if (prevNode == null || prevHeight != height) {
				prevNode = node;
				prevHeight = height;
			} else {
				prevNode.next = node;
				prevNode = node;
			}

			if (node.left != null) {
				nodeQueue.offer(node.left);
				heightQueue.offer(height + 1);
			}
			if (node.right != null) {
				nodeQueue.offer(node.right);
				heightQueue.offer(height + 1);
			}
		}
		return root;
	}
}
