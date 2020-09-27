package com.kkd.study.problem_solving.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class _222 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}
	
	// PROBLEM
	// Given a complete binary tree, count the number of nodes.

	// CHECK
	// Q. max height? -> Don't know.

	// Idea 1. - DFS or BFS to visit every nodes.
	// 1. visit every node and count it.
	// T.C: O(N + E / N*2)
	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int ret = 0;
		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			ret++;
			if (current.left != null) {
				queue.offer(current.left);
			}
			if (current.right != null) {
				queue.offer(current.right);
			}
		}
		return ret;
	}

	// Idea 2. 
	// complete binary tree is filled every nodes except leaf.
	// if we can figure out how many nodes in the leaf, we can get answer using this way
	// # of nodes (h-1) = 2^h - 1 + # of leaf nodes
	// But, I think this is impossible, because there is no way to get $ of leaf nodes without traversal. 

	// class Solution {
	//     public int countNodes(TreeNode root) {

	//     }
	// }
}
