package com.kkd.exercise.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/01-matrix/
 */
public class Matrix {
	
	public static void main(String[] args) {
		int[][] input = { { 0, 1 } };
		int[][] output = updateMatrix(input);
		
		for (int i=0 ; i<output.length; i++) {
			for (int j=0 ; j<output[0].length ; j++) {
				System.out.print(output[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};

	public static int[][] updateMatrix(int[][] matrix) {
		int h = matrix.length;
		int w = matrix[0].length;
		int[][] ret = new int[h][w];
		for (int i=0 ; i<h ; i++) {
			Arrays.fill(ret[i], 0);
		}
		
		int[][] visit = new int[h][w];
		for (int x=0 ; x<h ; x++) {
			for (int y=0 ; y<w ; y++) {
				if (matrix[x][y] == 1) {
					ret[x][y] = doBFS(matrix, visit, h, w, x, y);
				}
			}
		}
		return ret;
	}
	
	private static int doBFS(int[][] matrix, int[][] visit, int h, int w, int x, int y) {
		for (int i=0 ; i<h ; i++) {
			Arrays.fill(visit[i], -1);
		}
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		xq.add(x); yq.add(y);
		visit[x][y] = 0;
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			for (int i=0 ; i<4 ; i++) {
				x = tempx + dx[i];
				y = tempy + dy[i];
				if (x>=0 && x<h && y>=0 && y<w) {
					if (matrix[x][y] == 0) {
						return visit[tempx][tempy] + 1;
					} else { 
						xq.add(x); yq.add(y);
						visit[x][y] = visit[tempx][tempy] + 1;
					}
				}
			}
		}
		return 0;
	}
}
