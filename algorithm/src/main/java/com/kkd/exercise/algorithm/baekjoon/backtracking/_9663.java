package com.kkd.exercise.algorithm.baekjoon.backtracking;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/9663
 */
public class _9663 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 15;
	private static int check[][] = new int[SIZE][SIZE];
	private static int ans = 0;

	private static void start() throws IOException {
		int n = Integer.parseInt(br.readLine());
		for (int y=1 ; y<=n ; y++) {
			dfs(1, 1, y, n);
		}
		bw.write(ans + "\n");
	}

	private static void dfs(int cnt, int x, int y, int n) {
		if (cnt == n) {
			ans++;
			return;
		}

		markAccessPossibility(x, y, n, 1);
		int nextX = x + 1;
		for (int nextY=1 ; nextY<=n ; nextY++) {
			if (check[nextX][nextY] == 0) {
				dfs(cnt + 1, nextX, nextY, n);
			}
		}
		markAccessPossibility(x, y, n, -1);
	}

	private static void markAccessPossibility(int x, int y, int n, int ap) {
		// width: --
		for (int i=1 ; i<=n ; i++) {
			check[i][y] += ap;
		}
		// height: |
		for (int i=1 ; i<=n ; i++) {
			check[x][i] += ap;
		}
		// diagonal: x incremental
		for (int i=1 ; i<=n - x ; i++) {
			if (y+i <= n) {
				check[x+i][y+i] += ap;
			}
			if (y-i >= 1) {
				check[x+i][y-i] += ap;
			}
		}
		// diagonal: x decremental
		for (int i=1 ; i<= x - 1 ; i++) {
			if (y+i <= n) {
				check[x-i][y+i] += ap;
			}
			if (y-i >= 1) {
				check[x-i][y-i] += ap;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
