package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.ListNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class _237 {
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
}
