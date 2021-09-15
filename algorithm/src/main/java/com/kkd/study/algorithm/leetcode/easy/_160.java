package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _160 {
	private Set<ListNode> visit = new HashSet<>();

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null) return null;
		if (headB == null) return null;

		if (visit.contains(headA)) return headA;
		if (visit.contains(headB)) return headB;

		visit.add(headA);
		visit.add(headB);

		ListNode ret1 = getIntersectionNode(headA.next, headB);
		if (ret1 != null) return ret1;

		ListNode ret2 = getIntersectionNode(headA, headB.next);
		if (ret2 != null) return ret2;

		return null;
	}

	public static void main(String[] args) {
		ListNode headA = new ListNode(1);
		headA.next = new ListNode(4);

		ListNode headB = new ListNode(2);


		_160 program = new _160();
		program.getIntersectionNode(headA, headB);
	}
}
