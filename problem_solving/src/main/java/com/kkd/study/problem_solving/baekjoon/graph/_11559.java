package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/11559
 */
public class _11559 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static final int[] dx = new int[] { 0, 0, -1, 1 };
	private static final int[] dy = new int[] { -1, 1, 0, 0 };

	private static final int w = 6;
	private static final int h = 12;
	private static char[][] board = new char[h][w];
	private static boolean[][] visit = new boolean[h][w];

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		int ans = 0;
		boolean isFinish = false;
		
		for (int i=0 ; i<h ; i++) {
			board[i] = br.readLine().toCharArray();
		}

		while (!isFinish) {
			for (int i = 0; i < h; i++)
				Arrays.fill(visit[i], false);
			List<List<Pair>> group = new ArrayList<>();

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (board[i][j] != '.' && !visit[i][j]) {
						char color = board[i][j];
						Pair pair = new Pair(i, j);

						List<Pair> list = new ArrayList<>();
						Queue<Pair> q = new LinkedList<>();
						q.add(pair);
						visit[i][j] = true;

						while (!q.isEmpty()) {
							Pair p = q.poll();

							list.add(p);

							for (int k = 0; k < 4; k++) {
								int x = p.x + dx[k];
								int y = p.y + dy[k];
								if (x >= 0 && x < h && y >= 0 && y < w) {
									if (color == board[x][y] && !visit[x][y]) {
										q.add(new Pair(x, y));
										visit[x][y] = true;
									}
								}
							}
						}

						if (list.size() >= 4) {
							group.add(list);
						}
					}
				}
			}

			if (group.isEmpty()) {
				isFinish = true;
			} else {
				pop(group);
				rebalance();
				ans++;
			}
		}
		System.out.println(ans);
	}

	private static void pop(List<List<Pair>> group) {
		for (List<Pair> list : group) {
			for (Pair pair : list) {
				board[pair.x][pair.y] = '.';
			}
		}
	}
	
	private static void rebalance() {
		for (int y=0 ; y<w ; y++) {
			for (int x=h-1 ; x>=0 ; x--) {
				if (board[x][y] != '.') {
					for (int k=h-1 ; k>=x ; k--) {
						if (board[k][y] == '.') {
							board[k][y] = board[x][y];
							board[x][y] = '.';
							break;
						}
					}
				}
			}
		}
	}
}
