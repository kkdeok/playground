package com.kkd.study.problem_solving.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {
	public static void main(String[] args) {

	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {val = x;}
	}

	static class DepthTreeNode {
		int depth;
		TreeNode treeNode;

		DepthTreeNode(int depth, TreeNode treeNode) {
			this.depth = depth;
			this.treeNode = treeNode;
		}
	}

	static class Solution {
		public List<List<Integer>> levelOrder(TreeNode root) {
			List<List<Integer>> ret = new ArrayList<>();
			if (root == null) {
				return ret;
			}
			Queue<DepthTreeNode> q = new LinkedList<>();
			q.add(new DepthTreeNode(0, root));
			while (!q.isEmpty()) {
				DepthTreeNode dnode = q.poll();
				int depth = dnode.depth;
				TreeNode node = dnode.treeNode;

				while (ret.size() <= depth) {
					ret.add(new ArrayList<>());
				}
				ret.get(depth).add(node.val);
				if (node.left != null) {
					q.add(new DepthTreeNode(depth + 1, node.left));
				}
				if (node.right != null) {
					q.add(new DepthTreeNode(depth + 1, node.right));
				}

			}
			return ret;
		}
	}
}

