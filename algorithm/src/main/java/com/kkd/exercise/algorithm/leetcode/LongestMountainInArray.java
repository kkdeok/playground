package com.kkd.exercise.algorithm.leetcode;

/**
 * https://leetcode.com/problems/longest-mountain-in-array/
 */
public class LongestMountainInArray {

	public static void main(String[] args) {
		int[] A = new int[]{2,2,2};
		System.out.println(longestMountain(A));
	} 
	
	public static int longestMountain(int[] A) {
		int ans = 0, count = 0;
		boolean findUp = true;
		int n = A.length;
		for (int i=0 ; i<n-1 ; i++) {
			if (findUp) {
				if (A[i] < A[i+1]) {
					count++;
				} else if (count > 0 && A[i] > A[i+1]) {
					count++;
					findUp = false;
				} else {
					count = 0;
				}
			} else {
				if (A[i] > A[i+1]) {
					count++;
				} else {
					count++;
					ans = Math.max(ans, count);
					count=0;
					findUp = true;
				}
			}
		}
		if (count > 0) {
			ans = Math.max(ans, count+1);
		}
		return ans;
	}
}
