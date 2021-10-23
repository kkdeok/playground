package com.kkd.study.algorithm.codingtest.naver._211023.no3;

import java.util.*;

/**
 * You are given an array A of N positive integers and an integer K.
 *
 * Find the largest possible even sum of K elements at different positions in A.
 *
 * For example, given A = [4,9,8,2,6], K = 3, the largest even sum of three elements is 18.
 *
 * given an array A of N positive integers and positive integer K, returns the largest even sum of K elements.
 * If there are no such K elements, return -1;
 *
 * https://www.geeksforgeeks.org/maximum-even-sum-subsequence-of-length-k/
 */
public class Solution {
	public int solution(int[] A, int K) {
		// write your code in Java SE 8
		int n = A.length;
		if (K > n) {
			return -1;
		}

		int maxSum = 0;
		List<Integer> evenList = new ArrayList<>();
		List<Integer> oddList = new ArrayList<>();
		for (int i=0 ; i<n ; i++) {
			if (A[i] % 2 == 0) {
				evenList.add(A[i]);
			} else {
				oddList.add(A[i]);
			}
		}

		Collections.sort(evenList);
		Collections.sort(oddList);

		int i = evenList.size() - 1;
		int j = oddList.size() - 1;

		while (K > 0) {
			if (K % 2 == 1) {
				if (i >= 0) {
					maxSum += evenList.get(i--);
				} else {
					// if K is odd and remaining numbers are all odd,
					// result cannot be even, so return -1.
					return -1;
				}
				K--;
			} else if (i >=1 && j >= 1) {
				int evenSum = evenList.get(i) + evenList.get(i-1);
				int oddSum = oddList.get(j) + oddList.get(j-1);
				if (evenSum <= oddSum) {
					maxSum += oddSum;
					j-=2;
				} else {
					maxSum += evenSum;
					i-=2;
				}
				K-=2;
			} else if (i >= 1) {
				maxSum += evenList.get(i) + evenList.get(i-1);
				i-=2;
				K-=2;
			} else if (j >= 1) {
				maxSum += oddList.get(j) + oddList.get(j-1);
				j-=2;
				K-=2;
			}
		}
		return maxSum;
	}
}
