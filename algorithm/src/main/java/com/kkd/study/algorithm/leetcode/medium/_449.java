package com.kkd.study.algorithm.leetcode.medium;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-bst/
 */
public class _449 {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return null;
		}

		String result = String.valueOf(root.val);
		if (root.left != null) {
			result += "L" + serialize(root.left);
		} else {
			result += "LX";
		}
		if (root.right != null) {
			result += "R" + serialize(root.right);
		} else {
			result += "RX";
		}
		return result;
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || data.isEmpty()) {
			return null;
		}
		StringBuilder sb = new StringBuilder(data);
		return deserialize(sb);
	}

	private TreeNode deserialize(StringBuilder data) {
		if (data == null || data.toString().isEmpty()) {
			return null;
		}

		String value = getValue(data);
		if (isNull(value)) {
			return null;
		}
		TreeNode node = new TreeNode(Integer.parseInt(value));
		data.replace(0, data.indexOf("L")+1, "");
		node.left = deserialize(data);

		data.replace(0, data.indexOf("R")+1, "");
		node.right = deserialize(data);

		return node;
	}

	private boolean isNull(String value) {
		return "X".equals(value);
	}

	private String getValue(StringBuilder data) {
		int lidx = data.indexOf("L");
		int ridx = data.indexOf("R");

		if (lidx == -1 && ridx == -1) {
			return data.toString();
		}
		if (lidx == -1) {
			return data.substring(0, ridx);
		}
		if (ridx == -1) {
			return data.substring(0, lidx);
		}
		return lidx > ridx ? data.substring(0, ridx) : data.substring(0, lidx);
	}

	public static void main(String[] args) {
		_449 program = new _449();

		TreeNode node = new TreeNode(2);
		node.left = new TreeNode(1);
		node.right = new TreeNode(3);

		String str = program.serialize(node);
		TreeNode result = program.deserialize(str);
	}
}
