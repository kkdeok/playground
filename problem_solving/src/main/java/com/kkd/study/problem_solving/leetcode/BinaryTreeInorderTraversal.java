package com.kkd.study.problem_solving.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	class Solution {
		public List<Integer> inorderTraversal(TreeNode root) {
			Stack<TreeNode> s = new Stack<>();

			while (root != null) {
				s.push(root);
				root = root.left;
			}

			List<Integer> ret = new ArrayList<>();
			while (!s.empty()) {
				TreeNode node = s.pop();
				ret.add(node.val);

				if (node.right != null) {
					node = node.right;
					s.push(node);
					while (node.left != null) {
						s.push(node.left);
						node = node.left;
					}
				}
			}
			return ret;
		}
	}
}