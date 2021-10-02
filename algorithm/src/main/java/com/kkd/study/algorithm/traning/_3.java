package com.kkd.study.algorithm.traning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Write a function solution that, given an array A of N integers,
 * returns the largest integer K > 0 such that both values K and −K (the opposite number) exist in array A.
 * If there is no such integer, the function should return 0.
 *
 * Examples:
 * Given A = [3, 2, −2, 5, −3], the function should return 3 (both 3 and −3 exist in array A).
 * Given A = [1, 1, 2, −1, 2, −1], the function should return 1 (both 1 and −1 exist in array A).
 * Given A = [1, 2, 3, −4], the function should return 0 (there is no such K for which both
 * values K and −K exist in array A)
 *
 * Write an efficient algorithm for the following assumptions:
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 */
public class _3 {

	public static void main(String[] args) {
		_3 program = new _3();
		System.out.println(program.solution(new int[]{3, 2, -2, 5, -3}));
	}

	public int solution(int[] A) {
		int n = A.length;
		List<Integer> pList = new ArrayList<>();
		List<Integer> nList = new ArrayList<>();

		for (int num : A) {
			if (num > 0) pList.add(num);
			else nList.add(num);
		}

		Collections.sort(pList);
		Collections.sort(nList, Collections.reverseOrder());

		int pIdx = pList.size() - 1;
		int nIdx = nList.size() - 1;

		while (pIdx >= 0 && nIdx >= 0) {
			int pnum = pList.get(pIdx);
			int nnum = nList.get(nIdx);

			if (pnum + nnum == 0) {
				return pnum;
			} else if (pnum > -nnum){
				pIdx--;
			} else {
				nIdx--;
			}
		}
		return 0;
	}
}
