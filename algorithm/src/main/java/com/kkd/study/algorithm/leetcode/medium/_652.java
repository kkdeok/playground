package com.kkd.study.algorithm.leetcode.medium;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/
 */
public class _652 {
	private static Map<String, Integer> map;
	private static List<TreeNode> res;

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		map = new HashMap<>();
		res = new ArrayList<>();

		checkSubTree(root);
		return res;
	}

	public String checkSubTree(TreeNode node) {
		if (node == null) return "";

		String lkey = checkSubTree(node.left);
		String rkey = checkSubTree(node.right);
		String key = "(" + lkey + node.val + rkey + ")";

		int cnt = map.getOrDefault(key, 0);
		map.put(key, cnt + 1);

		if (map.get(key) == 2) {
			res.add(node);
		}
		return key;
	}
}
