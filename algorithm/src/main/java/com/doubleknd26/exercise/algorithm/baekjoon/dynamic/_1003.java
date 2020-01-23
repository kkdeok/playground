package com.doubleknd26.exercise.algorithm.baekjoon.dynamic;

import java.io.*;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/1003
 */
public class _1003 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 41;
	private static int[] memo = new int[SIZE];
	private static int[] zero = new int[SIZE];
	private static int[] one = new int[SIZE];

	private static void start() throws IOException {
		int k = Integer.parseInt(br.readLine());

		for (int i=0 ; i<k ; i++) {
			int n = Integer.parseInt(br.readLine());
			Arrays.fill(memo, -1);
			Arrays.fill(zero, -1);
			Arrays.fill(one, -1);

			one[0] = zero[1] = memo[0] = 0;
			one[1] = zero[0] = memo[1] = 1;

			fibo(n);
			bw.write(zero[n] + " " + one[n] + "\n");
		}
	}

	private static int fibo(int n) {
		if (memo[n] != -1) {
			return memo[n];
		}
		memo[n] = fibo(n - 1) + fibo(n - 2);
		zero[n] = zero[n-1] + zero[n-2];
		one[n] = one[n-1] + one[n-2];
		return memo[n];
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
