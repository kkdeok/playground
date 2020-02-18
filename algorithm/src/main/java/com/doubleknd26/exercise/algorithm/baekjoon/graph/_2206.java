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
	// 특정 위치에 벽을 깨서 온 케이스 (A) 와 벽을 깨지 않고 온 케이스 (B) 를 구분해줘야 한다.
	// 왜냐하면, 그 위치에 도착하기까지 B 더 오래 걸렸을지라도, 그 위치에서 목적지까지 가는데에
	// 벽을 한번 더 깨야할 수도 있기 때문이다. 그렇다면, A는 그 위치까지 더 빨리 왔다고 해도 목적지
	// 까지 가는게 불가능하다.
	private static int[][][] visit = new int[SIZE][SIZE][2];

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		for (int i=0 ; i<n ; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j=0 ; j<m ; j++) {
				adj[i][j] = Integer.parseInt(String.valueOf(line[j]));
				Arrays.fill(visit[i][j], Integer.MAX_VALUE);
			}
		}

		search(n, m);
		int ret = Math.min(visit[n-1][m-1][0], visit[n-1][m-1][1]);
		ret = ret == Integer.MAX_VALUE ? -1 : ret;
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
		visit[0][0][0] = 1;
		visit[0][0][1] = 1;

		while (!xq.isEmpty()) {
			int tmpx = xq.poll();
			int tmpy = yq.poll();
			boolean smash = smashq.poll();
			int smashIdx = smash ? 1 : 0;
			int time = visit[tmpx][tmpy][smashIdx];

			for (int i=0 ; i<4 ; i++) {
				int x = tmpx + dx[i];
				int y = tmpy + dy[i];

				if (x >= 0 && x < n && y >= 0 && y < m) {
					if (adj[x][y] == 0) {
						if (visit[x][y][smashIdx] > time + 1) {
							xq.add(x);
							yq.add(y);
							smashq.add(smash);
							visit[x][y][smashIdx] = time + 1;
						}
					} else {
						if (!smash && visit[x][y][smashIdx] > time + 1) {
							xq.add(x);
							yq.add(y);
							smashq.add(true);
							visit[x][y][1] = time + 1;
						}
					}
				}
			}
		}
	}
}
