package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class _141 {
	/**
	 * Definition for singly-linked list.
	 * class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) {
	 *         val = x;
	 *         next = null;
	 *     }
	 * }
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}
		if (head.val == Integer.MIN_VALUE) {
			return true;
		}
		head.val = Integer.MIN_VALUE;
		return hasCycle(head.next);
	}
}
