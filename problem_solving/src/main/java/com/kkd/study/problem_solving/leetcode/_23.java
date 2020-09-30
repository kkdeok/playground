package com.kkd.study.problem_solving.leetcode;


/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class _23 {

	 public class ListNode {
	     int val;
	     ListNode next;
	     ListNode() {}
	     ListNode(int val) { this.val = val; }
	     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 }

	// DESCRIBE
	// given array of k linked-lists lists, each linked-list is sorted in ascending order.
	// Merge all the linked-lists into one sorted linked-list and return it.

	// CHECK
	// Q. lists size = k ? -> 0 <= k <= 10^4
	// Q. value integer -> 0 <= lists[i].length <= 500

	// IDEA 1.
	// 1. divide lists: [[A], [B], [C], [D]] -> ([A], [B]) , ([C], [D])
	// 2. conquer: ([A], [B]) , ([C], [D]) -> ([A,B], [C,D]) -> [A,B,C,D]
	// T.C: O(500 * logK)

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}

		int s = 0;
		int e = lists.length - 1;
		return divideAndConquer(s, e, lists);
	}

	private ListNode divideAndConquer(int s, int e, ListNode[] lists) {
		if (s < e) {
			int mid = (s + e) / 2;
			ListNode lList = divideAndConquer(s, mid, lists);
			ListNode rList = divideAndConquer(mid + 1, e, lists);
			return conquer(lList, rList);
		}
		return lists[s];
	}

	private ListNode conquer(ListNode lList, ListNode rList) {
		ListNode root = new ListNode();
		ListNode p = root;

		while (lList != null && rList != null) {
			ListNode node = new ListNode();
			node.next = null;
			if (lList.val < rList.val) {
				node.val = lList.val;
				lList = lList.next;
			} else {
				node.val = rList.val;
				rList = rList.next;
			}

			if (p.next == null) {
				p.next = node;
				p = p.next;
			}
		}

		while (lList != null) {
			ListNode node = new ListNode();
			node.val = lList.val;
			node.next = null;

			if (p.next == null) {
				p.next = node;
				p = p.next;
			}
			lList = lList.next;
		}

		while (rList != null) {
			ListNode node = new ListNode();
			node.val = rList.val;
			node.next = null;

			if (p.next == null) {
				p.next = node;
				p = p.next;
			}
			rList = rList.next;
		}

		return root.next;
	}
	 
	private void start() {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(4);
		ListNode c = new ListNode(5);
		a.next = b;
		b.next = c;
		
		ListNode d = new ListNode(1);
		ListNode e = new ListNode(3);
		ListNode f = new ListNode(4);
		d.next = e;
		e.next = f;
		
		ListNode g = new ListNode(2);
		ListNode h = new ListNode(6);
		g.next = h;
		
		ListNode[] lists = new ListNode[]{};
		ListNode ans = mergeKLists(lists);
		
		while (ans != null) {
			System.out.println(ans.val);
			ans = ans.next;
		}
	}
	
	public static void main(String[] args) {
		_23 program = new _23();
		program.start();
	}
}
