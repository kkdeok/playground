package com.kkd.study.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2178
 */
public class _2178 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int size = 100;
	private static int[][] board = new int[size][size];
	private static boolean[][] visit = new boolean[size][size];
	
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
		System.out.println(doBFS(n, m));
	}
	
	private static int doBFS(int n, int m) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		Queue<Integer> cq = new LinkedList<>();

		xq.add(0); yq.add(0); cq.add(1);
		visit[0][0] = true;

		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			int tempc = cq.poll();

			if (tempx==n-1 && tempy==m-1) {
				return tempc;
			}

			for (int i=0 ; i<4 ; i++) {
				int x = tempx + dx[i];
				int y = tempy + dy[i];

				if (x>=0 && x<n && y>=0 && y<m) {
					if (board[x][y] == 1 && !visit[x][y]) {
						xq.add(x); yq.add(y); cq.add(tempc+1);
						visit[x][y] = true;
					}
				}
			}
		}
		return 0;
	}
}
