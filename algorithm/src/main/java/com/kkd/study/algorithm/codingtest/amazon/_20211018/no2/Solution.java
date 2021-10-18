package com.kkd.study.algorithm.codingtest.amazon._20211018.no2;

import java.util.Arrays;
import java.util.List;

/**
 * AS part of your Day 1 Orientation at Amazon, you've been invited to participate in a programming challenge.
 * Please represent your team by completing the following challenge.
 *
 * Given an array of binary digits, 0 and 1, sort the array so that
 * all zeros are at one end and all ones are at the other.
 * Which end does not matter.
 * To sort the array, swap any two adjacent elements.
 * Determine the minimum number of swaps to sort the array.
 *
 * 2진수 배열인 0과 1이 주어지면 다음과 같이 배열을 정렬합니다.
 * 모든 0은 한쪽 끝에 있고 모든 1은 다른 쪽 끝에 있습니다.
 * 어느 쪽 끝이 중요하지 않습니다.
 * 배열을 정렬하려면 인접한 두 요소를 바꿉니다.
 * 배열을 정렬하기 위한 최소 스왑 수를 결정합니다.
 */
public class Solution {
	// https://stackoverflow.com/questions/56878798/minimum-number-of-adjacent-swaps-of-binary-array
	public static int minMoves(List<Integer> arr) {
		int n0 = 0, i0 = 0, n1 = 0, i1 = 0;
		for (int p = 0; p < arr.size(); ++p) {
			if (arr.get(p) == 0)
				n0 += p - i0++; // No. of steps to move the 0 to the left
			else
				n1 += p - i1++; // No. of steps to move the 1 to the left
		}
		return Math.min(n0, n1); // Choose lowest no. of steps
	}
//
//	public static int minMoves(List<Integer> arr) {
//		int n = arr.size();
//		int count = 0;
//		int num_unplaced_zeros = 0;
//
//		for (int index = n - 1; index >= 0; index--) {
//			if (arr.get(index) == 0)
//				num_unplaced_zeros += 1;
//			else
//				count += num_unplaced_zeros;
//		}
//		return count;
//	}

	public static void main(String[] args) {
		System.out.println(Solution.minMoves(
			Arrays.asList(1, 1, 1, 1, 0, 0, 0, 0)));
	}
}
