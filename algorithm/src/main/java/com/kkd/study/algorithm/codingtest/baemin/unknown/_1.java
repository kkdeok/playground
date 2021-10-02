package com.kkd.study.algorithm.codingtest.baemin.unknown;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a board with 2 rows and N columns, represented by a matrix M.
 * Rows are numbered from 0 to 1 from top to bottom and columns are numbered from 0 to N−1 from
 * left to right.
 * Each cell contains either a 0 or a 1. You know that:
 * - the sum of integers in the 0-th (upper) row is equal to U,
 * - the sum of integers in the 1-st (lower) row is equal to L,
 * - the sum of integers in the K-th column is equal to C[K].
 *
 * Your job is to recover M based on this information.
 * Write a function:
 * class Solution {
 *   public String solution(int U, int L, int[] C);
 * }
 * that, given two integers U, L and an array C of N integers, as described above, returns a
 * string describing the matrix M in the following format.
 * The first part of the string should be the description of the upper row (N characters: 0 or 1)
 * , then there should be comma (,), and finally there should be the description of the lower row
 * (N characters: 0 or 1.)
 * The output string should not contain any whitespace.
 * If there exist multiple valid Ms, your function may return any one of them.
 * If no valid M exists, your function should return the word IMPOSSIBLE.
 *
 * Examples:
 * Given U = 3, L = 2, C = [2, 1, 1, 0, 1], your function may, for example, return 11001,10100
 * which describes the following board:
 * 그림1
 * Given U = 2, L = 3, C = [0, 0, 1, 1, 2], your function should return the word IMPOSSIBLE,
 * because no matrix M satisfies such conditions.
 * Given U = 2, L = 2, C = [2, 0, 2, 0], your function should return 1010,1010, which describes
 * the following board:
 *
 * 그림2
 * Write an efficient algorithm for the following assumptions:
 * U and L are integers within the range [0..100,000];
 * N is an integer within the range [1..100,000];
 * each element of array C is an integer within the range [0..2].
 */
public class _1 {
	private Map<Integer, List<Pair<Integer, Integer>>> visit;
	private int[][] res;

	public static void main(String[] args) {
		_1 program = new _1();
		String result = program.solution(2, 2, new int[]{2, 0, 2, 0});
		System.out.println(result);
	}

	public String solution(int U, int L, int[] C) {
		visit = new HashMap<>();
		res = new int[2][C.length];

		if (find(U, L, C, C.length-1)) {
			StringBuilder sb = new StringBuilder();
			for (int i=0 ; i<2 ; i++) {
				int len = res[i].length;
				for (int j=0 ; j<len ; j++) {
					sb.append(res[i][j]);
				}
				if (i == 0) {
					sb.append(",");
				}
			}
			return sb.toString();
		}
		return "IMPOSSIBLE";
	}

	private boolean find(int U, int L, int[] C, int K) {
		markVisit(U, L, K);

		if (K == -1) {
			if (U == 0 && L == 0) {
				return true;
			}
			return false;
		}

		if (C[K] == 0) {
			if (!isSkippable(U, L, K-1)) {
				res[0][K] = res[1][K] = 0;
				return find(U, L, C, K-1);
			}
		} else if (C[K] == 2) {
			if (!isSkippable(U-1, L-1, K-1)) {
				res[0][K] = res[1][K] = 1;
				return find(U-1, L-1, C, K-1);
			}
		} else {
			boolean result = false;
			if (!isSkippable(U-1, L, K-1)) {
				res[0][K] = 1;
				res[1][K] = 0;
				result = result || find(U-1, L, C, K-1);
			}
			if (!result && !isSkippable(U, L-1, K-1)) {
				res[0][K] = 0;
				res[1][K] = 1;
				result = find(U, L-1, C, K-1);
			}
			return result;
		}
		return false;
	}

	private void markVisit(int U, int L, int K) {
		List<Pair<Integer, Integer>> list = visit.getOrDefault(K, new ArrayList<>());
		list.add(new Pair<>(U, L));
		visit.put(K, list);
	}

	private boolean isSkippable(int U, int L, int K) {
		if (K == -1) return false;

		if (visit.containsKey(K)) {
			List<Pair<Integer, Integer>> list = visit.get(K);
			for (Pair<Integer, Integer> pair : list) {
				if (pair.getKey() == U && pair.getValue() == L) {
					return true;
				}
			}
		}
		return false;
	}
}
