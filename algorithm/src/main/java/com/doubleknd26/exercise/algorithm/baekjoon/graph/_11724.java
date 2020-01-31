package com.doubleknd26.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/11724
 */
public class _11724 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static final int SIZE = 1001;
	private static int[][] graph = new int[SIZE][SIZE];
	private static int[] check = new int[SIZE];

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		for (int i=0 ; i<m ; i++) {
			String[] line = br.readLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			graph[u][v] = graph[v][u] = 1;
		}

		int res = 0;
		for (int i=1 ; i<=n ; i++) {
			if (check[i] == 0) {
				// dfs(n, i);
				bfs(n, i);
				res++;
			}
		}
		bw.write(res + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int n, int x) {
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		check[x] = 1;

		while (!q.isEmpty()) {
			int tmpX = q.poll();
			for (int y=1 ; y<=n ; y++) {
				if (graph[tmpX][y] == 1 && check[y] == 0) {
					q.add(y);
					check[y] = 1;
				}
			}
		}
	}

	private static void dfs(int n, int x) {
		check[x] = 1;
		for (int i=1 ; i<=n ; i++) {
			if (graph[x][i] == 1 && check[i] == 0) {
				dfs(n, i);
			}
		}
	}
}
