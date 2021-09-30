package com.kkd.study.algorithm.leetcode.easy;

/**
 * https://leetcode.com/problems/flood-fill/
 */
public class _733 {
	private static final int[] dx = new int[]{-1, 1, 0, 0};
	private static final int[] dy = new int[]{0, 0, -1, 1};
	private static int[][] checked;
	private static int xLen = 0;
	private static int yLen = 0;

	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		xLen = image.length;
		yLen = image[0].length;
		checked = new int[xLen][yLen];

		fill(image, sr, sc, newColor);
		return image;
	}

	private void fill(int[][] image, int sr, int sc, int newColor) {
		checked[sr][sc] = 1;
		int prev = image[sr][sc];
		image[sr][sc] = newColor;

		for (int i=0 ; i<4 ; i++) {
			int x = sr + dx[i];
			int y = sc + dy[i];
			if (x>=0 && x<xLen && y>=0 && y<yLen) {
				if (image[x][y] == prev && checked[x][y] != 1) {
					fill(image, x, y, newColor);
				}
			}
		}
	}
}
