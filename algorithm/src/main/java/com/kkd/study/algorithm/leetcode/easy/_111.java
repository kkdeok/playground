package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

public class _111 {
	public int minDepth(TreeNode root) {
		if (root == null) return 0;
		return findMinDepth(root, 1);
	}

	private int findMinDepth(TreeNode node, int depth) {
		if (node.left == null && node.right == null) {
			return depth;
		}

		int min = Integer.MAX_VALUE;
		if (node.left != null) {
			min = Math.min(min, findMinDepth(node.left, depth+1));
		}

		if (node.right != null) {
			min = Math.min(min, findMinDepth(node.right, depth+1));
		}

		return min;
	}
}
