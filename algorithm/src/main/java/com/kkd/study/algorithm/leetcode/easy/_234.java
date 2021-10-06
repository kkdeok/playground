package com.kkd.study.algorithm.leetcode.easy;

import com.kkd.study.algorithm.leetcode.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class _234 {
	public boolean isPalindrome(ListNode head) {
		List<Integer> list = new ArrayList<>();

		while (head != null) {
			list.add(head.val);
			head = head.next;
		}

		int l = 0;
		int r = list.size()-1;

		while (l<=r) {
			int left = list.get(l++);
			int right = list.get(r--);
			if (left != right) {
				return false;
			}
		}
		return true;
	}
}
