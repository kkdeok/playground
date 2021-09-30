package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/
 */
public class _112 {
	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) return false;

		// leaf node
		if (root.left == null && root.right == null) {
			return targetSum - root.val == 0;
		}

		boolean res = false;
		if (root.left != null) {
			res = (res || hasPathSum(root.left, targetSum - root.val));
		}

		if (root.right != null) {
			res = (res || hasPathSum(root.right, targetSum - root.val));
		}
		return res;
	}
}
