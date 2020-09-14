package com.kkd.study.problem_solving.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class BinarySearchTreeIterator {
	public static void main(String[] args) {

	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	static class BSTIterator {
		private Queue<Integer> q;

		public BSTIterator(TreeNode root) {
			q = new LinkedList<>();
			Stack<TreeNode> s = new Stack<>();
			while (root != null) {
				s.push(root);
				root = root.left;
			}

			while (!s.empty()) {
				TreeNode node = s.pop();
				q.add(node.val);
				if (node.right != null) {
					node = node.right;
					s.push(node);
					while (node.left != null) {
						s.push(node.left);
						node = node.left;
					}
				}
			}
		}

		/** @return the next smallest number */
		public int next() {
			return q.poll();
		}

		/** @return whether we have a next smallest number */
		public boolean hasNext() {
			return !q.isEmpty();
		}
	}
}