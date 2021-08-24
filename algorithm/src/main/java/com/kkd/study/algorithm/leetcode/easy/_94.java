package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class _94 {
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root != null) inorderTraversal(res, root);
		return res;
	}

	private void inorderTraversal(List<Integer> res, TreeNode node) {
		if (node.left != null) inorderTraversal(res, node.left);
		res.add(node.val);
		if (node.right != null) inorderTraversal(res, node.right);
	}
}
