package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class _206 {
	public ListNode reverseList(ListNode head) {
		if (head == null)
			return null;
		return reverse(null, head);
	}

	private ListNode reverse(ListNode prev, ListNode head) {
		if (head.next == null) {
			head.next = prev;
			return head;
		} else {
			ListNode next = head.next;
			ListNode res = reverse(head, next);
			head.next = prev;
			return res;
		}
	}
}
