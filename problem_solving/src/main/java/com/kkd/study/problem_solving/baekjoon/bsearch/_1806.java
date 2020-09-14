package com.kkd.study.problem_solving.baekjoon.bsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/1806
 */
public class _1806 {
	private static int[] arr;
	private static int n, s;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		s = Integer.parseInt(line[1]);
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println(process());
	}
	
	public static int process() {
		int l = 1;
		int r = arr.length;
		boolean hasPossibleWay = false;
		
		while (l<=r) {
			int mid = (l + r) / 2;
			boolean isPossible = false;
			long sum = 0;
			for (int i=0 ; i<=arr.length - mid ; i++) {
				if (i == 0) {
					for (int j=0 ; j<mid ; j++) {
						sum += arr[i+j];
					}
				} else {
					sum -= arr[i-1];
					sum += arr[i+mid-1];
				}
				if (sum >= s) {
					isPossible = true;
					hasPossibleWay = true;
					break;
				}
			}

			if (isPossible) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return hasPossibleWay ? l : 0;
	}

}
