package com.doubleknd26.exercise.algorithm.baekjoon.divideandconquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/10830
 */
public class _10830 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		long b = Long.parseLong(input[1]);

		long[][] m = new long[n][n];
		for (int i=0 ; i<n ; i++) {
			m[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		}
		long[][] ans = find(n, m, b);
		for (int i=0 ; i<n ; i++) {
			for(int j=0 ; j<n ; j++) {
				bw.write((ans[i][j] % 1000) + " ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static long[][] find (int n, long[][] m , long b) {
		if (b == 1) {
			return m;
		}
		long[][] sub = find(n, m, b/2);
		long[][] res = multiply(n, sub, sub);
		return b % 2 == 1 ? multiply(n, m, res) : res;
	}

	private static long[][] multiply(int n, long[][] a, long[][] b) {
		long[][] ret = new long[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ret[i][j] = 0;
				for (int k = 0; k < n; k++) {
					ret[i][j] = ((ret[i][j] % 1000) + ((a[i][k] * b[k][j]) % 1000)) % 1000;
				}
			}
		}
		return ret;
	}
}
