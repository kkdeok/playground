package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1260
 */
public class _1260 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static final int SIZE = 1001;
	private static boolean[][] BOARD = new boolean[SIZE][SIZE];
	private static boolean[] VISITED = new boolean[SIZE];
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		int v = Integer.parseInt(line[2]);

		for (int i=0 ; i<m ; i++) {
			line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			BOARD[x][y] = BOARD[y][x] = true;
		}

		doDFS(n, v);
		Arrays.fill(VISITED, false);
		bw.write("\n");
		doBFS(n, v);
		bw.write("\n");
		bw.flush();
	}
	
	private static void doDFS(int n, int v) throws IOException {
		VISITED[v] = true;
		bw.write(v + " ");
		for (int i=1 ; i<=n ; i++) {
			if (BOARD[v][i] && !VISITED[i]) {
				doDFS(n, i);
			}
		}
	}
	
	private static void doBFS(int n , int v) throws IOException {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		VISITED[v] = true;
		
		while (!q.isEmpty()) {
			int tempv = q.poll();
			bw.write(tempv + " ");
			for (int i=1 ; i<=n ; i++) {
				if (BOARD[tempv][i] && !VISITED[i]) {
					q.add(i);
					VISITED[i] = true;
				}
			}
		}
	}
}
