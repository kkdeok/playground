package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.TreeNode;


/**
 * https://leetcode.com/problems/symmetric-tree/
 */
public class _101 {
	public boolean isSymmetric(TreeNode root) {
		return compare(root, root);
	}

	private boolean compare(TreeNode lNode, TreeNode rNode) {
		if (lNode == null && rNode == null)
			return true;
		if (lNode == null || rNode == null)
			return false;
		if (lNode.val != rNode.val)
			return false;

		return compare(lNode.left, rNode.right) && compare(lNode.right, rNode.left);
	}
}
