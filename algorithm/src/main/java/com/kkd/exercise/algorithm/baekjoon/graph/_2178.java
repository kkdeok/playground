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
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int size = 101;
	private static int[][] board = new int[size][size];
	private static int[][] visit = new int[size][size];
	
	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};
	
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		for (int i=0 ; i<n ; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j=0 ; j<m ; j++) {
				board[i][j] = chars[j] - '0';
			}
		}
		doBFS(n, m);
		System.out.println(visit[n-1][m-1]);
	}
	
	private static void doBFS(int n, int m) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		
		xq.add(0); yq.add(0);
		visit[0][0] = 1;
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			for (int i=0 ; i<4 ; i++) {
				int x = tempx + dx[i];
				int y = tempy + dy[i];
				if (x>=0 && x<n && y>=0 && y<m) {
					if (board[x][y] == 1 && visit[x][y] == 0) {
						xq.add(x); yq.add(y);
						visit[x][y] = visit[tempx][tempy] + 1;
					}
				}
			}
		}
	}
}
