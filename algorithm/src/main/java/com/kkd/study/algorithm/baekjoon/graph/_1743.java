package com.kkd.study.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1743
 */
public class _1743 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int[][] adj = new int[101][101];
	private static boolean[][] visit = new boolean[101][101];
	
	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		int r = Integer.parseInt(line[2]);
		
		for (int i=0 ; i<r ; i++) {
			line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			adj[x][y] = 1;
		}
		
		int ans = 0;
		for (int i=1 ; i<=n ; i++) {
			for (int j=1 ; j<=m ; j++) {
				if (adj[i][j] == 1 && !visit[i][j]) {
					ans = Math.max(ans, doBFS(n, m, i, j));
				}
			}
		}
		System.out.println(ans);
	}
	
	private static int doBFS(int n, int m, int x, int y) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		
		xq.add(x); yq.add(y);
		visit[x][y] = true;
		
		int ret = 0;
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			ret++;
			
			for (int i=0 ; i<4 ; i++) {
				x = tempx + dx[i];
				y = tempy + dy[i];
				if (x>=1 && x <=n && y>=1 && y<=m) {
					if (adj[x][y] == 1 && !visit[x][y]) {
						xq.add(x); yq.add(y);
						visit[x][y] = true;
					}
				}
			}
		}
		return ret;
	}
}
