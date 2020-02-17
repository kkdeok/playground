package com.doubleknd26.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2178
 */
public class _2178 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static short[] dx = new short[]{0, 0, -1, 1};
	private static short[] dy = new short[]{-1, 1, 0, 0};

	private static final int SIZE = 101;
	private static short[][] adj = new short[SIZE][SIZE];
	private static short[][] visited = new short[SIZE][SIZE];

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		for (int i=0 ; i<n ; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j=0 ; j<m ; j++) {
				adj[i][j] = Short.parseShort(String.valueOf(line[j]));
				Arrays.fill(visited[i], Short.MAX_VALUE);
			}
		}

		search(n, m);
		bw.write(visited[n-1][m-1] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void search(int n, int m) {
		Queue<Short> xq = new LinkedList<>();
		Queue<Short> yq = new LinkedList<>();

		xq.add((short) 0);
		yq.add((short) 0);
		visited[0][0] = (short) 1;

		while (!xq.isEmpty()) {
			short tmpx = xq.poll();
			short tmpy = yq.poll();
			int time = visited[tmpx][tmpy];

			for (int i=0 ; i<4 ; i++) {
				short x = (short) (tmpx + dx[i]);
				short y = (short) (tmpy + dy[i]);
				if (x >= 0 && x < n && y >= 0 && y < m) {
					if (adj[x][y] == 1) {
						if (visited[x][y] > time + 1) {
							xq.add(x);
							yq.add(y);
							visited[x][y] = (short) (time + 1);
						}
					}
				}
			}
		}
	}
}
