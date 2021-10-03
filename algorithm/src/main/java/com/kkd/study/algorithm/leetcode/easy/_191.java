package com.kkd.study.algorithm.leetcode.easy;

public class _191 {
	public static void main(String[] args) {
		System.out.println(hammingWeight(11));
	}

	// you need to treat n as an unsigned value
	public static int hammingWeight(int n) {
		int cnt = 0;
		for (int i=0 ; i<32 ; i++) {
			cnt += (n << i & 1) == 1 ? 1 : 0;
		}
		return cnt;
	}
}
