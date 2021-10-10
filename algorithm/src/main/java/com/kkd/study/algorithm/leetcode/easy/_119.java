package com.kkd.study.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 */
public class _119 {
	public List<Integer> getRow(int rowIndex) {
		int[] memo = new int[rowIndex+1];
		memo[0] = 1;
		for (int i=0 ; i<=rowIndex ; i++) {
			for (int j=i ; j>=1 ; j--) {
				memo[j] = memo[j] + memo[j-1];
			}
		}
		return Arrays.stream(memo).boxed().collect(Collectors.toList());
	}

	public static void main(String[] args) {
		_119 program = new _119();
		List<Integer> result = program.getRow(3);

		for (int a : result) {
			System.out.println(a);
		}
	}
}
