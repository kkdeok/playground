package com.kkd.study.algorithm.leetcode.medium;

/**
 * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 */
public class _1283 {
	public static void main(String[] args) {
		_1283 program = new _1283();
		System.out.println(program.smallestDivisor(new int[]{1,2,5,9}, 6));
	}

	public int smallestDivisor(int[] nums, int threshold) {
		int l = 1;
		int r = 1000000;
		int res = Integer.MAX_VALUE;

		while (l<=r) {
			int mid = (l + r) / 2;
			int sum = 0;
			for (int num : nums) {
				// sum += Math.ceil((float) num / mid);
				sum += (num + mid - 1) / mid;
			}
			if (sum <= threshold) {
				res = Math.min(res, mid);
				r = mid-1;
			} else {
				l = mid+1;
			}
		}

		return res;
	}
}
