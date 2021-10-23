package com.kkd.study.algorithm.codingtest.naver._211023.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	public int solution(int[] A) {
		Arrays.sort(A);

		int res = 1;
		for (int num : A) {
			if (num > 0) {
				if (num == res) {
					res++;
				} else if (num == res - 1) {
					// do nothing
				} else {
					break;
				}
			}
		}
		return res;
	}
}
