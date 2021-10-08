package com.kkd.study.algorithm.codingtest.baemin.unknown;

import java.util.Arrays;

/**
 * You are given an implementation of a function:
 * class Solution { public int solution(int[] A, int[] B); }
 * that, given a non-empty array A of N non-negative integers and a non-empty array B of M
 * non-negative integers, returns the minimal value that occurs in both arrays. If there is no
 * such value, the function should return −1.
 *
 * For example, given arrays A and B such that:
 *     A[0] = 1    B[0] = 4
 *     A[1] = 3    B[1] = 2
 *     A[2] = 2    B[2] = 5
 *     A[3] = 1    B[3] = 3
 *                 B[4] = 2
 * your function should return 2, since 2 is the minimal value which occurs in both arrays A and B (another value which occurs in both arrays is 3).
 * Given arrays A and B such that:
 *     A[0] = 2    B[0] = 3
 *     A[1] = 1    B[1] = 3
 * your function should return −1, since there is no value that occurs in both arrays.
 *
 * The attached code is still incorrect for some inputs. Despite the error(s), the code may produce a correct answer for the example test cases.
 * The goal of the exercise is to find and fix the bug(s) in the implementation.
 * You can modify at most two lines.
 *
 * Write an efficient algorithm for the following assumptions:
 * N and M are integers within the range [1..100,000];
 * each element of arrays A, B is an integer within the range [0..1,000,000,000].
 */
public class _4 {
	public static void main(String[] args) {
		_4 program = new _4();
		int res = program.solution(new int[]{1,3,2,1}, new int[]{4,2,5,3,2});
		System.out.println(res);
	}

	public int solution(int[] A, int[] B) {
		int n = A.length;
		int m = B.length;
		Arrays.sort(A);
		Arrays.sort(B);
		int i = 0;
		for (int k = 0; k < n; k++) {
			while (i < m && B[i] < A[k]) {
				i += 1;
				System.out.println(i);
			}
			if (i < m && A[k] == B[i])
				return A[k];
		}
		return -1;
	}

//	// attached function
//	public int solution(int[] A, int[] B) {
//		int n = A.length;
//		int m = B.length;
//		Arrays.sort(A);
//		Arrays.sort(B);
//		int i = 0;
//		for (int k = 0; k < n; k++) {
//			if (i < m - 1 && B[i] < A[k]) {
//				i += 1;
//				System.out.println(i);
//			}
//			if (A[k] == B[i])
//				return A[k];
//		}
//		return -1;
//	}
}
