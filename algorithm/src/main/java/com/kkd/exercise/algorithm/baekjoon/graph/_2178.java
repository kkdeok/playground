package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2178
 */
public class _2178 {
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 101;
	private static int[][] board = new int[SIZE][SIZE];
	private static int[][] visit = new int[SIZE][SIZE];

	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		for (int i=1 ; i<=n ; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j=1 ; j<=m ; j++) {
				board[i][j] = tmp[j-1] - '0';
			}
		}

		int ret = calMinimumMovement(n, m);
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		bw.close();
	}

	private static int calMinimumMovement(int n, int m) {
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		qx.add(1);
		qy.add(1);
		visit[1][1] = 1;

		while (!qx.isEmpty()) {
			int tmpx = qx.poll();
			int tmpy = qy.poll();
			// base condition
			if (tmpx == n && tmpy == m) {
				break;
			}
			for (int i=0 ; i<4 ; i++) {
				int x = tmpx + dx[i];
				int y = tmpy + dy[i];
				if (1 <= x && x <= n && 1 <= y && y <= m) {
					if (board[x][y] == 1 && visit[x][y] == 0) {
						qx.add(x);
						qy.add(y);
						visit[x][y] = visit[tmpx][tmpy] + 1;
					}
				}
			}
		}
		return visit[n][m];
	}
}
