package com.kkd.study.algorithm.baekjoon.bsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1654
 */
public class _1654 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int k = Integer.parseInt(line[0]);
		int n = Integer.parseInt(line[1]);

		int[] arr = new int[k];
		int max = 0;
		for (int i=0 ; i<k ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}

		long l = 1, r = max;
		long ans = 0;
		while (l<=r) {
			long mid = (l + r) / 2;
			int count = 0;
			for (int a : arr) {
				count += a / mid;
			}
			
			if (count >= n) {
				l = mid + 1;
				ans = Math.max(ans, mid);
			} else {
				r = mid - 1;
			}
		}
		System.out.println(ans);
	}
}
