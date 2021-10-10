package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/counting-bits/
 */
public class _338 {
//	public int[] countBits(int n) {
//		int[] result = new int[n+1];
//		for (int i=1 ; i<=n ; i++) {
//			if (i==1) {
//				result[i] = 1;
//			} else {
//				int temp = i;
//				int count = 0;
//				while (temp != 0) {
//					if ((temp & 1) == 1) {
//						count++;
//					}
//					temp >>= 1;
//				}
//				result[i] = count;
//			}
//		}
//		return result;
//	}

	public int[] countBits(int n) {
		int[] result = new int[n+1];
		for (int i=1 ; i<=n ; i++) {
			result[i] = result[i >> 1] + (i & 1);
		}
		return result;
	}

	public static void main(String[] args) {
		_338 program = new _338();
		program.countBits(5);
	}
}
