package com.kkd.study.algorithm.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/island-perimeter/
 */
public class _463 {
	private static final int[] dx = new int[] { -1, 1, 0, 0 };
	private static final int[] dy = new int[] { 0, 0, -1, 1 };

	public int islandPerimeter(int[][] grid) {
		int xLen = grid.length;
		int yLen = grid[0].length;
		int[][] visit = new int[xLen][yLen];

		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();

		// select land to start
		for (int i = 0; i < xLen; i++) {
			for (int j = 0; j < yLen; j++) {
				if (grid[i][j] == 1) {
					xq.offer(i);
					yq.offer(j);
					visit[i][j] = 1;
					break;
				}
			}
			if (!xq.isEmpty())
				break;
		}

		int res = 0;
		while (!xq.isEmpty()) {
			int x = xq.poll();
			int y = yq.poll();

			for (int i = 0; i < 4; i++) {
				int tempX = x + dx[i];
				int tempY = y + dy[i];

				if (tempX >= 0 && tempX < xLen && tempY >= 0 && tempY < yLen) {
					if (grid[tempX][tempY] == 1) {
						if (visit[tempX][tempY] == 0) {
							xq.offer(tempX);
							yq.offer(tempY);
							visit[tempX][tempY] = 1;
						}
					} else {
						res++; // water
					}
				} else {
					res++; // water
				}
			}
		}
		return res;
	}
}
