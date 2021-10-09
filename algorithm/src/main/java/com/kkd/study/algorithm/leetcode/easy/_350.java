package com.kkd.study.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class _350 {
	public int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int l1 = 0;
		int r1 = nums1.length;

		int l2 = 0;
		int r2 = nums2.length;

		List<Integer> list = new ArrayList<>();
		while (l1<r1 && l2<r2) {
			if (nums1[l1] == nums2[l2]) {
				list.add(nums1[l1]);
				l1++;
				l2++;
			} else if (nums1[l1] < nums2[l2]) {
				l1++;
			} else {
				l2++;
			}
		}
		return list.stream().mapToInt(i -> i).toArray();
	}
}
