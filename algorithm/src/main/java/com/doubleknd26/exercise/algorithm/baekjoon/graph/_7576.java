package com.doubleknd26.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/7576
 */
public class _7576 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};

	private static int[][] visit = new int[1000][1000];
	private static int[][] adj = new int[1000][1000];

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int m = Integer.parseInt(input[0]);
		int n = Integer.parseInt(input[1]);

		for (int i=0 ; i<n ; i++) {
			adj[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt).toArray();
			Arrays.fill(visit[i], -1);
		}

		search(m, n);

		int ret = 0;
		boolean unripe = false;
		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<m ; j++) {
				if (visit[i][j] == -1 && adj[i][j] == 0) {
					unripe = true;
					ret = -1;
					break;
				}
				ret = Math.max(ret, visit[i][j]);
			}
			if (unripe) {
				break;
			}
		}
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void search(int m, int n) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();

		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<m ; j++) {
				if (adj[i][j] == 1) {
					xq.add(i);
					yq.add(j);
					visit[i][j] = 0;
				}
			}
		}

		while (!xq.isEmpty()) {
			int tmpx = xq.poll();
			int tmpy = yq.poll();
			int days = visit[tmpx][tmpy];

			for (int i=0 ; i<4 ; i++) {
				int x = tmpx + dx[i];
				int y = tmpy + dy[i];
				if (x >= 0 && x < n && y >= 0 && y < m) {
					if (adj[x][y] == 0) {
						if (visit[x][y] == -1 || visit[x][y] > days + 1) {
							xq.add(x);
							yq.add(y);
							visit[x][y] = days + 1;
						}
					}
				}
			}
		}
	}
}
