package com.kkd.study.problem_solving.leetcode.tree;

/**
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 */
public class MaximumLevelSumOfABinaryTree {
	
	public static void main(String[] args) {
		
	}

	private static final int size = 10001;
	private static long[] sums;
	
	public static int maxLevelSum(TreeNode root) {
		sums = new long[size];
		traversal(root, 1);
		
		long ret = 0;
		int level = 0;
		for(int i=0 ; i<size ; i++) {
			if (ret < sums[i]) {
				ret = sums[i];
				level = i + 1;
			}
		}
		return level;
	}
	
	private static void traversal(TreeNode node, int level) {
		sums[level] += node.val;
		if (node.left != null) {
			traversal(node.left , level + 1);
		}
		if (node.right != null) {
			traversal(node.right, level + 1);
		}
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
