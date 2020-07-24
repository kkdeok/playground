package com.kkd.exercise.algorithm.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/11047
 */
public class _11047 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);

		int[] a = new int[n];
		for (int i=0 ; i<n ; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		int ret = 0;
		for (int i=n-1 ; i>=0 ; i--) {
			while (k - a[i] >= 0) {
				k-= a[i];
				ret++;
			}
		}
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
