package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

public class _110 {
	public boolean isBalanced(TreeNode root) {
		return getHeight(root) != -1;
	}

	private int getHeight(TreeNode node) {
		if (node == null) return 0;

		int left = getHeight(node.left);
		int right = getHeight(node.right);

		if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

		return Math.max(left, right) + 1;
	}
}
