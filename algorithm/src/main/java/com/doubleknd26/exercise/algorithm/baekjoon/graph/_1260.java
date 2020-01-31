package com.doubleknd26.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
	private static int[][] graph = new int[SIZE][SIZE];
	private static int[] check = new int[SIZE];


	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int v = Integer.parseInt(input[2]);

		for (int i=0 ; i<m ; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			graph[x][y] = graph[y][x] = 1; // there is an edge.
		}

		Arrays.fill(check, 0);
		dfs(n, v);
		bw.write("\n");

		Arrays.fill(check, 0);
		bfs(n, v);
		bw.write("\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int n, int x) throws Exception {
		check[x] = 1;
		bw.write(x + " ");
		for (int y=1 ; y<=n ; y++) {
			if (graph[x][y] == 1 && check[y] == 0) {
				dfs(n, y);
			}
		}
	}

	private static void bfs(int n, int x) throws Exception {
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		check[x] = 1;
		while (!q.isEmpty()) {
			int tmpX = q.poll();
			bw.write(tmpX + " ");
			for (int y=1 ; y<=n ; y++) {
				if (graph[tmpX][y] == 1 && check[y] == 0) {
					q.add(y);
					check[y] = 1;
				}
			}
		}
	}
}
