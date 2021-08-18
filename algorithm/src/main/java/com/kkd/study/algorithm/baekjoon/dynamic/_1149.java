package com.kkd.study.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/1149
 */
public class _1149 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 1000;
	private static int[][] rgb = new int[SIZE][3];
	private static int[][] memo = new int[SIZE][3];
	private static int[][] next = {{1,2}, {0, 2}, {0, 1}};


	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		for (int i=0 ; i<n ; i++) {
			String[] input = br.readLine().split(" ");
			rgb[i] = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
		}
		memo[0] = rgb[0];

		int res = Math.min(paint(n-1, 0), Math.min(paint(n-1, 1), paint(n-1, 2)));
		bw.write(res + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int paint(int x, int y) {
		if (memo[x][y] != 0) {
			return memo[x][y];
		}
		memo[x][y] = rgb[x][y];
		memo[x][y] += Math.min(paint(x-1, next[y][0]), paint(x-1, next[y][1]));
		return memo[x][y];
	}
}
