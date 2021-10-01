package com.kkd.study.algorithm.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-closed-islands/
 */
public class _1254 {
	private static final int[] dx = new int[] { -1, 1, 0, 0 };
	private static final int[] dy = new int[] { 0, 0, -1, 1 };

	private static int m;
	private static int n;
	private static int[][] visit;

	public int closedIsland(int[][] grid) {
		m = grid.length;
		n = grid[0].length;
		visit = new int[m][n];

		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0 && visit[i][j] == 0) {
					res += isClosedIsland(grid, i, j);
				}
			}
		}
		return res;
	}

	private int isClosedIsland(int[][] grid, int x, int y) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();

		xq.offer(x);
		yq.offer(y);
		visit[x][y] = 1;
		boolean isClosed = true;

		while (!xq.isEmpty()) {
			x = xq.poll();
			y = yq.poll();

			for (int i = 0; i < 4; i++) {
				int tempX = x + dx[i];
				int tempY = y + dy[i];

				if (tempX >= 0 && tempX < m && tempY >= 0 && tempY < n) {
					if (grid[tempX][tempY] == 0 && visit[tempX][tempY] == 0) {
						xq.offer(tempX);
						yq.offer(tempY);
						visit[tempX][tempY] = 1;
					}
				} else {
					isClosed = false;
				}
			}
		}

		return isClosed ? 1 : 0;
	}
}
