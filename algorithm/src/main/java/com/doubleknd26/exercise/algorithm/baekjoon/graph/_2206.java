package com.doubleknd26.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2206
 */
public class _2206 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};

	private static final int SIZE = 1000;
	private static int[][] adj = new int[SIZE][SIZE];
	private static int[][] visit = new int[SIZE][SIZE];

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		for (int i=0 ; i<n ; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j=0 ; j<m ; j++) {
				adj[i][j] = Integer.parseInt(String.valueOf(line[j]));
			}
			Arrays.fill(visit[i], Integer.MAX_VALUE);
		}

		search(n, m);
		int ret = visit[n-1][m-1] == Integer.MAX_VALUE ? -1 : visit[n-1][m-1];
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void search(int n, int m) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		Queue<Boolean> smashq = new LinkedList<>();

		xq.add(0);
		yq.add(0);
		smashq.add(false);
		visit[0][0] = 1;

		while (!xq.isEmpty()) {
			int tmpx = xq.poll();
			int tmpy = yq.poll();
			boolean smash = smashq.poll();
			int time = visit[tmpx][tmpy];

			for (int i=0 ; i<4 ; i++) {
				int x = tmpx + dx[i];
				int y = tmpy + dy[i];

				if (x >= 0 && x < n && y >= 0 && y < m) {
					if (adj[x][y] == 0 || (adj[x][y] == 1 && !smash)) {
						if (visit[x][y] > time + 1 || (visit[x][y] == time + 1 && !smash)) {
							xq.add(x);
							yq.add(y);
							visit[x][y] = time + 1;
							smashq.add(adj[x][y] == 1 || smash);
						}
					}
				}
			}
		}
	}
}
