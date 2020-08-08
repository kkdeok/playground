package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/11724
 */
public class _11724 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int SIZE = 1001;
	private static int[][] BOARD = new int[SIZE][SIZE];
	private static int[] VISITED = new int[SIZE];
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		
		for (int i=0 ; i<m ; i++) {
			line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			BOARD[x][y] = BOARD[y][x] = 1;
		}
		
		int count = 0;
		for (int i=1 ; i<=n ; i++) {
			if (VISITED[i] == 0) {
				count++;
				doDFS(n, i);
			}
		}
		System.out.println(count);
		br.close();
	}
	
	private static void doDFS(int n, int v) {
		VISITED[v] = 1;
		for (int i=1; i <=n ; i++) {
			if (BOARD[v][i] == 1 && VISITED[i] == 0) {
				doDFS(n, i);
			}
		}
	}
}
