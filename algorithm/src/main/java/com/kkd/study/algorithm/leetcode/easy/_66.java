package com.kkd.study.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/plus-one/
 */
public class _66 {
	// try 1
//	public int[] plusOne(int[] digits) {
//		List<Integer> list = new ArrayList<>();
//		int len = digits.length;
//		boolean plusOne = false;
//		for (int i = len-1 ; i>=0 ; i--) {
//			int digit = digits[i];
//			if (i == len - 1) {
//				if (++digit == 10) {
//					list.add(0);
//					plusOne = true;
//				} else {
//					list.add(digit);
//				}
//			} else {
//				if (plusOne) {
//					if (++digit == 10) {
//						list.add(0);
//					} else {
//						list.add(digit);
//						plusOne = false;
//					}
//				} else {
//					list.add(digit);
//				}
//			}
//		}
//		if (plusOne) {
//			list.add(1);
//		}
//		Collections.reverse(list);
//		return list.stream().mapToInt(Integer::intValue).toArray();
//	}

	// try 2
	public static int[] plusOne(int[] digits) {
		int len = digits.length;
		for (int i=len-1 ; i>=0 ; i--) {
			if (++digits[i] > 9) {
				digits[i] = 0;
			} else {
				break;
			}
		}

		if (digits[0] == 0) {
			int[] res = new int[len + 1];
			res[0] = 1;
			return res;
		}
		return digits;
	}

	public static void main(String[] args) {
		int[] digits = new int[]{9, 9, 9, 9};
		plusOne(digits);
	}
}
