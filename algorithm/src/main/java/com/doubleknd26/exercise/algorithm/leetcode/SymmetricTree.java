package com.doubleknd26.exercise.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {
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

	static class Solution {
		public boolean isSymmetric(TreeNode root) {
			return check(Collections.singletonList(root));
		}

		public boolean check(List<TreeNode> nodes) {
			int nullCount = 0;
			for (TreeNode node : nodes) {
				if (node == null) {
					nullCount++;
				}
			}
			if (nullCount == nodes.size()) {
				return true;
			}

			List<Integer> list = new ArrayList<>();

			for (TreeNode node : nodes) {
				list.add(node == null ? null : node.val);
			}

			int len = list.size();
			int mid = len / 2;
			for (int i=0 ; i<mid ; i++) {
				Integer a = list.get(i);
				Integer b = list.get(len - i - 1);
				if (a == null && b == null) {
					continue;
				} else if (a == null || b == null) {
					return false;
				} else if (!a.equals(b)) {
					return false;
				}
			}

			List<TreeNode> child = new ArrayList<>();
			for (TreeNode node : nodes) {
				if (node == null) {
					continue;
				}
				child.add(node.left);
				child.add(node.right);
			}
			return check(child);
		}
	}
}