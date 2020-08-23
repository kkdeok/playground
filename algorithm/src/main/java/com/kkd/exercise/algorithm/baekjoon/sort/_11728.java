package com.kkd.exercise.algorithm.baekjoon.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/11728
 */
public class _11728 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int n;
	private static int m;
	private static int[] a;
	private static int[] b;
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		StringBuilder sb = new StringBuilder();
		for (int val : process()) {
			sb.append(val + " ");
		}
		System.out.println(sb.toString());
	}
	
	public static int[] process() {
		int l = 0, r = 0, idx = 0;
		int[] ret = new int[n+m];

		while (l < n && r < m) {
			if (a[l] < b[r]) {
				ret[idx++] = a[l++];
			} else {
				ret[idx++] = b[r++];
			}
		}

		while (l < n) ret[idx++] = a[l++];
		while (r < m) ret[idx++] = b[r++];

		return ret;
	}
}
