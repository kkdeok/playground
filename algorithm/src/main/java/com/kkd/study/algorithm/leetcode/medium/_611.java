package com.kkd.study.algorithm.leetcode.medium;

import java.util.Arrays;

public class _611 {
	public int triangleNumber(int[] nums) {
		Arrays.sort(nums);

		int res = 0;
		int n = nums.length;
		for (int i=n-1 ; i>=0 ; i--) {
			int longest = nums[i];
			int l = 0, r = i-1;
			while (l<r) {
				if (nums[l] + nums[r] > longest) {
					// 이 안에 포함되어있는 애들은 모두 삼각형을 만들 수 있는 조건이 된다.
					res+=r-l;
					r--;
				} else {
					l++;
				}
			}
		}
		return res;
	}
}
