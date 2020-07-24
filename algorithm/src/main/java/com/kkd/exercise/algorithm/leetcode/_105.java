package com.kkd.exercise.algorithm.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class _105 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] a = {3,9,20,15,7};
		int[] b = {9,3,15,20,7};

		TreeNode node = s.buildTree(a, b);


	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	static class Solution {
		public TreeNode buildTree(int[] preorder, int[] inorder) {
			TreeNode root = new TreeNode(preorder[0]);

			if (inorder.length == 1) {
				return root;
			}

			int pos = -1;
			for (int i=0 ; i<inorder.length ; i++) {
				if (inorder[i] == preorder[0]) {
					pos = i;
					break;
				}
			}

			int[] lpo = Arrays.copyOfRange(preorder, 1, pos + 1);
			int[] rpo = Arrays.copyOfRange(preorder, pos + 1, preorder.length);

			int[] lio = Arrays.copyOfRange(inorder, 0, pos);
			int[] rio = Arrays.copyOfRange(inorder, pos + 1, inorder.length);

			root.left = buildTree(lpo, lio);
			root.right = buildTree(rpo, rio);

			return root;
		}
	}
}