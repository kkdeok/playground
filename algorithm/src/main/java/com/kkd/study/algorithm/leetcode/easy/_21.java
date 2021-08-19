package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class _21 {
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 */
//	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//		ListNode head = new ListNode();
//		ListNode node = head;
//
//		while (l1 != null && l2 != null) {
//			if (l1.val > l2.val) {
//				node.next = new ListNode(l2.val, null);
//				l2 = l2.next;
//			} else {
//				node.next = new ListNode(l1.val, null);
//				l1 = l1.next;
//			}
//			node = node.next;
//		}
//
//		while (l1 != null) {
//			node.next = new ListNode(l1.val, null);
//			node = node.next;
//			l1 = l1.next;
//		}
//
//		while (l2 != null) {
//			node.next = new ListNode(l2.val, null);
//			node = node.next;
//			l2 = l2.next;
//		}
//
//		return head.next;
//	}
}
