package com.kkd.study.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2293
 */
public class _2293 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int[] arr = new int[101];
	private static int[] d = new int[10001];

	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int k = Integer.parseInt(line[1]);
		
		for (int i=0 ; i<n ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		d[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = arr[i]; j <= k; j++) {
				d[j] = d[j - arr[i]] + d[j];
			}
		}
		System.out.println(d[k]);
	}
}
