package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2146
 */
public class _2146 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static final int size = 100;
	private static int[][] adj = new int[size][size];
	private static int[][] visit = new int[size][size]; 
	
	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		
		for (int i=0 ; i<n ; i++) {
			adj[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		// divide island
		int islandNum = 1;
		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<n ; j++) {
				if (adj[i][j] != 0 && visit[i][j] == 0) {
					divideIsland(i, j, n, islandNum++);
				}
			}
		}
		initVisit(n);
		
		int ret = Integer.MAX_VALUE;
		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<n ; j++) {
				if (adj[i][j] != 0) {
					for (int k=0 ; k<4 ; k++) {
						int tempi = i + dx[k];
						int tempj = j + dy[k];
						if (tempi>=0 && tempi<n && tempj>=0 && tempj<n) {
							if (adj[tempi][tempj] == 0) {
								ret = Math.min(ret, findBridge(i, j, n, adj[i][j]));
								initVisit(n);
								break;
							}
						}
					}
				}
			}
		}
		ret = ret == Integer.MAX_VALUE ? 0 : ret;
		System.out.println(ret);
	}
	
	private static void divideIsland(int x, int y, int n, int num) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		
		xq.add(x); yq.add(y);
		visit[x][y] = 1;
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			adj[tempx][tempy] = num;
			for (int i=0 ; i<4 ; i++) {
				x = tempx + dx[i];
				y = tempy + dy[i];
				if (x>=0 && x<n && y>=0 && y<n) {
					if (adj[x][y] != 0 && visit[x][y] == 0) {
						xq.add(x); yq.add(y);
						visit[x][y] = 1;
					}
				}
			}
		}
	}
	
	private static void initVisit(int n) {
		for (int i=0 ; i<n ; i++) Arrays.fill(visit[i], 0);
	}
	
	private static int findBridge(int x, int y, int n, int num) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		Queue<Integer> nq = new LinkedList<>();
		
		xq.add(x); yq.add(y); nq.add(1);
		visit[x][y] = 1;
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			int tempn = nq.poll();

			for (int i=0 ; i<4 ; i++) {
				x = tempx + dx[i];
				y = tempy + dy[i];
				if (x>=0 && x<n && y>=0 && y<n) {
					if (adj[x][y] == 0 && visit[x][y] == 0) {
						xq.add(x); yq.add(y); nq.add(tempn + 1);
						visit[x][y] = 1;
					} else if (adj[x][y] != 0 && adj[x][y] != num) {
						return tempn - 1;
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}
}
