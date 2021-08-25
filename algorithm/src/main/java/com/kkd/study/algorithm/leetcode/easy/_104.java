package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class _104 {
	public int maxDepth(TreeNode root) {
		if (root == null) return 0;
		return dfs(root, 1);
	}

	private int dfs(TreeNode node, int depth) {
		int lDepth = depth;
		int rDepth = depth;
		if (node.left != null) {
			lDepth = dfs(node.left, depth+1);
		}
		if (node.right != null) {
			rDepth = dfs(node.right, depth+1);
		}
		return lDepth > rDepth ? lDepth : rDepth;
	}
}
