package com.kkd.study.algorithm.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/max-area-of-island/
 */
public class _695 {
	private static final int[] dx = new int[]{-1, 1, 0, 0};
	private static final int[] dy = new int[]{0, 0, -1, 1};
	private static Queue<Integer> xq = new LinkedList<>();
	private static Queue<Integer> yq = new LinkedList<>();


	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] visit = new int[m][n];
		int res = 0;
		for (int i=0 ; i<m ; i++) {
			for (int j=0 ; j<n ; j++) {
				if (grid[i][j] == 1 && visit[i][j] == 0) {
					res = Math.max(res, getArea(grid, visit, i, j, m, n));
				}
			}
		}
		return res;
	}

	private int getArea(int[][] grid, int[][] visit, int x, int y, int m, int n) {
		xq.offer(x);
		yq.offer(y);
		visit[x][y] = 1;

		int res = 0;
		while (!xq.isEmpty()) {
			x = xq.poll();
			y = yq.poll();

			res++; // count area

			for (int i=0 ; i<4 ; i++) {
				int tempX = x + dx[i];
				int tempY = y + dy[i];
				if (tempX>=0 && tempX<m && tempY>=0 && tempY<n) {
					if (grid[tempX][tempY] == 1 && visit[tempX][tempY] == 0) {
						xq.offer(tempX);
						yq.offer(tempY);
						visit[tempX][tempY] = 1;
					}
				}
			}
		}
		return res;
	}
}
