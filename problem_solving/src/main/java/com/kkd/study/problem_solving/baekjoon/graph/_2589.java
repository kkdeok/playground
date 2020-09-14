package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2589
 */
public class _2589 {

	private static int[][] visit;
	private static char[][] board;
	private static int n; // 세로
	private static int m; // 가로

	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		board = new char[n][m];
		visit = new int[n][m];
		
		for (int i=0 ; i<n ; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j=0 ; j<chars.length ; j++) {
				board[i][j] = chars[j];
			}
		}
		System.out.println(process());
	}
	
	public static int process() {
		int ret = 0;
		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<m ; j++) {
				if (board[i][j] == 'L') {
					int temp = doBFS(i, j);
					ret = Math.max(ret, temp);
				}
			}
		}
		return ret;
	}

	private static int doBFS(int x, int y) {
		// init visit
		for (int i=0 ; i<n ; i++) {
			Arrays.fill(visit[i], -1);
		}

		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();

		xq.add(x); yq.add(y);
		visit[x][y] = 0;

		int ret = 0;
		while(!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			ret = Math.max(ret, visit[tempx][tempy]);
			for (int i=0 ; i<4 ; i++) {
				x = tempx + dx[i];
				y = tempy + dy[i];
				if (x>=0 && x<n && y>=0 && y<m) {
					if (board[x][y] == 'L' && visit[x][y] == -1) {
						xq.add(x); yq.add(y);
						visit[x][y] = visit[tempx][tempy] + 1;
					}
				}
			}
		}
		return ret;
	}

}
