package com.kkd.study.algorithm.leetcode.medium;

import com.kkd.study.algorithm.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 */
public class _662 {
	public int widthOfBinaryTree(TreeNode root) {
		Queue<TreeNode> nq = new LinkedList<>(); // node queue
		Queue<Integer> pq = new LinkedList<>(); // position queue
		Queue<Integer> hq = new LinkedList<>(); // height queue

		nq.offer(root);
		pq.offer(1);
		hq.offer(1);

		List<Integer> posList = new ArrayList<>(); // position list of same height nodes
		int res = 1;
		int prevHeight = 1;
		while (!nq.isEmpty()) {
			TreeNode node = nq.poll();
			int pos = pq.poll();
			int height = hq.poll();

			if (prevHeight != height) {
				int width = getWidth(posList);
				res = Math.max(res, width);
				posList.clear();
			}
			posList.add(pos);
			prevHeight = height;

			if (node.left != null) {
				nq.offer(node.left);
				pq.offer(pos * 2);
				hq.offer(height + 1);
			}
			if (node.right != null) {
				nq.offer(node.right);
				pq.offer(pos * 2 + 1);
				hq.offer(height + 1);
			}
		}
		int width = getWidth(posList); // for last height
		return Math.max(res, width);
	}

	private int getWidth(List<Integer> posList) {
		return posList.get(posList.size()-1) - posList.get(0) + 1;
	}
}
