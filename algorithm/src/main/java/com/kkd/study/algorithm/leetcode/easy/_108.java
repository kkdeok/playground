package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class _108 {
	public TreeNode sortedArrayToBST(int[] nums) {
		TreeNode root = new TreeNode();
		int l = 0;
		int r = nums.length-1;
		int m = (l + r) / 2;
		makeBst(root, nums, l, m, r);
		return root;
	}

	private void makeBst(TreeNode node, int[] nums, int l, int m, int r) {
		node.val = nums[m];

		if (l <= m-1) {
			node.left = new TreeNode();
			makeBst(node.left, nums, l, (l+m-1)/2, m-1);
		}
		if (r >= m+1) {
			node.right = new TreeNode();
			makeBst(node.right, nums, m+1, (m+1+r)/2, r);
		}
	}
}
