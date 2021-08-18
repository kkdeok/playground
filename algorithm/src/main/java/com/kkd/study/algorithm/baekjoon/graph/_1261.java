package com.kkd.study.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/1261
 */
public class _1261 {
	private static int n, m;
	private static int[][] memo;
	private static int[][] visit;
	private static int[][] board;

	private static int[] dx = new int[] { 0, 0, -1, 1 };
	private static int[] dy = new int[] { -1, 1, 0, 0 };

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		m = Integer.parseInt(line[0]);
		n = Integer.parseInt(line[1]);
		memo = new int[n][m];
		visit = new int[n][m];
		board = new int[n][m];
		
		for (int i=0 ; i<n ; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j=0 ; j<chars.length ; j++) {
				board[i][j] = chars[j] - '0';
			}
		}
		
		_1261 app = new _1261();
		System.out.println(app.process());
	}
	
	public int process() {
		// init
		for (int i=0 ; i<n ; i++) {
			Arrays.fill(memo[i], -1);
			Arrays.fill(visit[i], 0);
		}
		memo[n-1][m-1] = Integer.MAX_VALUE;

		doDFS(0, 0, 0);
		return memo[n-1][m-1];
	}

	public void doDFS(int x, int y, int c) {
		memo[x][y] = c;
		if (x == n-1 && y == m-1) {
			return;
		}
		visit[x][y] = 1;
		for (int i=0 ; i<4 ; i++) {
			int tx = x + dx[i]; // tempX
			int ty = y + dy[i]; // tempY
			if (tx >= 0 && tx < n && ty >= 0 && ty < m) {
				if (visit[tx][ty] == 1) continue;
				if (memo[tx][ty] == -1
					|| memo[tx][ty] > c + board[tx][ty]) {
					doDFS(tx, ty, c + board[tx][ty]);
				}
			}
		}
		visit[x][y] = 0;
	}
}
