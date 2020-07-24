package com.kkd.exercise.algorithm.baekjoon.dynamic;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/2748
 */
public class _2748 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 91;
	private static long[] memo = new long[SIZE];

	private static void start() throws IOException {
		int n = Integer.parseInt(br.readLine());
		bw.write(fibo(n) + "\n");
	}

	private static long fibo(int n) {
		if (n <= 1) {
			return n;
		}
		if (memo[n] != 0) {
			return memo[n];
		}
		return memo[n] = fibo(n-1) + fibo(n-2);
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
