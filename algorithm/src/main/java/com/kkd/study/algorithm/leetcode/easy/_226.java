package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 */
public class _226 {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) return null;

		// invert
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		invertTree(root.left);
		invertTree(root.right);

		return root;
	}
}
