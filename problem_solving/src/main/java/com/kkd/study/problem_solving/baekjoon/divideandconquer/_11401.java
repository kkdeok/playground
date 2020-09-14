package com.kkd.study.problem_solving.baekjoon.divideandconquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/11401
 */
public class _11401 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static final int SIZE = 4000001;
	private static final int MOD = 1000000007;
	private static int[] memo = new int[SIZE];

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);

		if (k > n) {
			bw.write("0\n");
		} else {
			memo[0] = 1;
			for (int i=1 ; i<=n ; i++) {
				memo[i] = ((memo[i-1] % MOD) * (i % MOD)) % MOD;
			}

			int a = memo[n] / memo[n-k];
			int b = memo[k];
			bw.write(a / b + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
