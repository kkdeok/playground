package com.kkd.exercise.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class Solution {
		public boolean isSameTree(TreeNode p, TreeNode q) {
			List<Integer> tp = traversal(p);
			List<Integer> tq = traversal(q);
			return tp.equals(tq);
		}

		private List<Integer> traversal(TreeNode node) {
			List<Integer> list = new ArrayList<>();
			Stack<TreeNode> s = new Stack<>();
			s.push(node);
			while (!s.isEmpty()) {
				TreeNode tmp = s.pop();
				list.add(tmp.val);
				if (tmp.right != null) {
					s.push(tmp.right);
				}
				if (tmp.left != null) {
					s.push(tmp.left);
				}
			}
			return list;
		}
	}
}
