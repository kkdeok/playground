package com.kkd.study.algorithm.baekjoon.backtracking;

	import java.io.*;

/**
 * https://www.acmicpc.net/problem/9663
 */
public class _9663 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int count = 0;

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());

		int[][] board = new int[n][n];
		process(board, n, 0);
		System.out.println(count);
	}

	private static void process(int[][] board, int n, int x) {
		if (n == x) {
			count++;
			return;
		}
		for (int y=0 ; y<n ; y++) {
			if (isPromise(board, n, x, y)) {
				board[x][y] = 1;
				process(board, n, x+1);
				board[x][y] = 0;
			}
		}
	}

	private static boolean isPromise(int[][] board, int n, int x, int y) {
		for (int i=0 ; i<n ; i++) {
			if (board[i][y] == 1) {
				return false;
			}
			if (x-i>=0 && x-i<n && y-i>=0 && y-i<n) {
				if (board[x-i][y-i] == 1) return false;
			}
			if (x+i>=0 && x+i<n && y+i>=0 && y+i<n) {
				if (board[x+i][y+i] == 1) return false;
			}
			if (x+i>=0 && x+i<n && y-i>=0 && y-i<n) {
				if (board[x+i][y-i] == 1) return false;
			}
			if (x-i>=0 && x-i<n && y+i>=0 && y+i<n) {
				if (board[x-i][y+i] == 1) return false;
			}
		}
		return true;
	}

}
