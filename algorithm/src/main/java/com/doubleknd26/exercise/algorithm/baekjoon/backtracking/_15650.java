package com.doubleknd26.exercise.algorithm.baekjoon.backtracking;

import java.io.*;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/15650
 */
public class _15650 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 9;
	private static Stack<Integer> s = new Stack<>();
	private static int check[] = new int[SIZE];

	private static void start() throws IOException {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		for (int i=1 ; i<=n ; i++) {
			dfs(i, n, m);
		}
	}

	private static void dfs(int k, int n, int m) throws IOException {
		s.push(k);
		check[k] = 1;

		if (s.size() == m) {
			for (int i=0 ; i<m ; i++) {
				bw.write(s.get(i) + " ");
			}
			bw.write("\n");
		} else {
			for (int i=k + 1 ; i<=n ; i++) {
				if (check[i] == 0) {
					dfs(i, n, m);
				}
			}
		}

		check[k] = 0;
		s.pop();
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
