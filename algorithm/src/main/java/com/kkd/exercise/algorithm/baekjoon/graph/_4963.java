package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/4963
 */
public class _4963 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static final int SIZE = 50;
	private static int[][] board = new int[SIZE][SIZE];
	private static int[][] check = new int[SIZE][SIZE];

	private static int[] dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
	private static int[] dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};

	public static void main (String[] args) throws Exception {

		while(true) {
			String[] input = br.readLine().split(" ");
			int w = Integer.parseInt(input[0]);
			int h = Integer.parseInt(input[1]);

			if (w == 0 && h == 0) {
				break;
			}

			for (int x=0 ; x<SIZE ; x++) {
				Arrays.fill(board[x], 0);
				Arrays.fill(check[x], 0);
			}

			for (int i=0 ; i<h ; i++) {
				board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			int ret = 0;
			for (int x=0 ; x<h ; x++) {
				for (int y=0 ; y<w ; y++) {
					if (board[x][y] == 1 && check[x][y] == 0) {
						bfs(h, w, x, y);
						ret++;
					}
				}
			}
			bw.write(ret + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int h, int w, int x, int y) {
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();

		qx.add(x);
		qy.add(y);
		check[x][y] = 1;

		while (!qx.isEmpty() && !qy.isEmpty()) {
			x = qx.poll();
			y = qy.poll();

			for (int i=0 ; i<8 ; i++) {
				int tmpx = x + dx[i];
				int tmpy = y + dy[i];
				if (tmpx >= 0 && tmpx < h && tmpy >= 0 && tmpy < w) {
					if (board[tmpx][tmpy] == 1 && check[tmpx][tmpy] == 0) {
						check[tmpx][tmpy] = 1;
						qx.add(tmpx);
						qy.add(tmpy);
					}
				}
			}
		}
	}
}
