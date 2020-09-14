package com.kkd.study.problem_solving.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {
	
	public static void main(String[] args) {
		int[][] input = new int[][]{{0}};
		System.out.println(orangesRotting(input));
	}

	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};
	
	public static int orangesRotting(int[][] grid) {
		int h = grid.length;
		int w = grid[0].length;

		int[][] visit = new int[h][w];
		for (int i=0 ; i<h ; i++) {
			Arrays.fill(visit[i], -1);
		}
		
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		for (int x=0 ; x<h ; x++) {
			for (int y=0 ; y<w ; y++) {
				if (grid[x][y] == 2) {
					xq.add(x); yq.add(y);
					visit[x][y] = 0;
				}
			}
		}
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			
			for (int i=0 ; i<4 ; i++) {
				int x = tempx + dx[i];
				int y = tempy + dy[i];
				
				if (x>=0 && x<h && y>=0 && y<w) {
					if (grid[x][y] == 1 && visit[x][y] == -1) {
						xq.add(x); yq.add(y);
						grid[x][y] = 2;
						visit[x][y] = visit[tempx][tempy] + 1;
					}
				}
			}
		}
		
		int ans = 0;
		for (int i=0 ; i<h ; i++) {
			for (int j=0 ; j<w ; j++) {
				if (grid[i][j] == 1) {
					return -1;
				}
				ans = Math.max(ans, visit[i][j]);
			}
		}
		return ans;
	}
}
