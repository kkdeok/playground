package com.doubleknd26.exercise.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _1463 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static long[] memo = new long[1000001];

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		memo[2] = 1;
		memo[3] = 1;

		for (int i=4 ; i<=n ; i++) {
			long a = i % 3 == 0 ? memo[i/3] : Long.MAX_VALUE;
			long b = i % 2 == 0 ? memo[i/2] : Long.MAX_VALUE;
			long c = memo[i-1];
			memo[i] = Math.min(a, Math.min(b, c)) + 1;
		}
		bw.write(memo[n] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

//	private static long find(int n) {
//		if (n <= 1) {
//			return 0;
//		}
//		if (memo[n] != 0) {
//			return memo[n];
//		}
//		long a = n % 3 == 0 ? find(n/3) : Long.MAX_VALUE;
//		long b = n % 2 == 0 ? find(n/2) : Long.MAX_VALUE;
//		long c = find(n-1);
//		return	memo[n] = Math.min(a, Math.min(b, c)) + 1;
//	}
}
