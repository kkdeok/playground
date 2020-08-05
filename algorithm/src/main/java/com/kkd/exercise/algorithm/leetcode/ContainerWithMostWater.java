package com.kkd.exercise.algorithm.leetcode;

public class ContainerWithMostWater {

	public static void main(String[] args) {
		int[] height = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(maxArea(height));
	}

	// brute force O(n2)
//	public static int maxArea(int[] height) {
//		int ret = -1;
//		for (int i = 0; i < height.length; i++) {
//			for (int j = i + 1; j < height.length; j++) {
//				int h = j - i;
//				int v = Math.min(height[i], height[j]);
//				ret = Math.max(ret, h * v);
//			}
//		}
//		return ret;
//	}

	// Two pointer approach O(n)
	public static int maxArea(int[] height) {
		int ret = -1;
		int l = 0, r = height.length - 1;
		while (l < r) {
			ret = Math.max(ret, Math.min(height[l], height[r]) * (r-l));
			if (height[l] < height[r]) {
				l++;
			} else {
				r--;
			}
		}
		return ret;
	}
}
