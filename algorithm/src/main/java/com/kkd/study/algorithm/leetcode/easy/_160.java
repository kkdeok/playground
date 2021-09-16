package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.ListNode;

public class _160 {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		traversal(headA, -1);
		ListNode ret = traversal(headB, 1);
		traversal(headA, -1);

		return ret;
	}

	private ListNode traversal(ListNode node, int checker) {
		if (node == null) {
			return node;
		}

		if (checker > 0 && node.val < 0) {
			return node;
		}

		node.val *= checker;
		return traversal(node.next, checker);
	}
}
