package com.doubleknd26.exercise.algorithm.baekjoon.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1260
 */
public class _1260 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 1001;
	private static final int[][] board = new int[SIZE][SIZE];
	private static boolean[] check = new boolean[SIZE];

	private static void start() throws IOException {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int v = Integer.parseInt(input[2]);
		for (int i=0; i<m ; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			board[x][y] = board[y][x] = 1;
		}
		doDFS(v, n);
		Arrays.fill(check, false);
		System.out.println();
		doBFS(v, n);
	}

	private static void doDFS(int v, int n) {
		check[v] = true;
		System.out.print(v + " ");
		for (int i=1 ; i<=n ; i++) {
			if (board[v][i] == 1 && !check[i]) {
				doDFS(i, n);
			}
		}
	}

	private static void doBFS(int v, int n) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		check[v] = true;

		while (!q.isEmpty()) {
			int tmpv = q.poll();
			System.out.print(tmpv + " ");
			for (int i=1 ; i<=n ; i++) {
				if (board[tmpv][i] == 1 && !check[i]) {
					q.add(i);
					check[i] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		start();
		br.close();
	}
}
