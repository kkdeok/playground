package com.kkd.exercise.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1904
 */
public class _1904 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static long[] memo = new long[1000001];

	private static void start() throws Exception {
		int n = Integer.parseInt(br.readLine());
		memo[0] = 0;
		memo[1] = 1;
		memo[2] = 2;
		for (int i=3 ; i<=n ; i++) {
			memo[i] = (memo[i-1] + memo[i-2]) % 15746;
		}
		bw.write(memo[n] + "\n");
	}

//	private static long find(int n) throws Exception {
//		if (memo[n] != -1) {
//			return memo[n];
//		}
//		memo[n] = find(n-2) + find(n-1);
//		memo[n] %= 15746;
//		bw.write(n + ": " + memo[n] + "\n");
//		return memo[n];
//	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
