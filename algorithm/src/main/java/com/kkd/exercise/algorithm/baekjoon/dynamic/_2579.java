package com.kkd.exercise.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2579
 */
public class _2579 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 301;
	private static int[] stairs = new int[SIZE];
	private static int[] memo = new int[SIZE];

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		for (int i=1 ; i<=n ; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		Arrays.fill(memo, -1);
		bw.write(find(n) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int find(int n) {
		if (n <= 0) {
			return 0;
		}
		if (memo[n] != -1) {
			return memo[n];
		}
		return memo[n] = stairs[n] + Math.max(find(n-2), stairs[n-1] + find(n-3));
	}
}
