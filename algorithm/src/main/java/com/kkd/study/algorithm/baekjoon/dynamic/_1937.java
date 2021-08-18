package com.kkd.study.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/1937
 */
public class _1937 {

	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 500;
	private static int[][] map = new int[SIZE][SIZE];
	private static int[][] memo = new int[SIZE][SIZE];

	private static int[] dx = new int[]{0, 0, -1, 1}; // 좌 우 위, 아래
	private static int[] dy = new int[]{-1, 1, 0, 0};

	private static void start() throws Exception {
		int n = Integer.parseInt(br.readLine());
		for (int i=0 ; i<n ; i++) {
			int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			map[i] = temp;
			Arrays.fill(memo[i], -1);
		}

		int ret = Integer.MIN_VALUE;
		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<n ; j++) {
				ret = Math.max(ret, search(i, j, n) + 1);
			}
		}

		bw.write(ret + "\n");
	}

	private static int search(int x, int y, int n) {
		if (memo[x][y] != -1) {
			return memo[x][y];
		}

		int max = 0;
		for (int i=0 ; i<4 ; i++) {
			int tmpx = x + dx[i];
			int tmpy = y + dy[i];
			if (tmpx >= 0 && tmpx < n && tmpy >= 0 && tmpy < n) {
				if (map[tmpx][tmpy] > map[x][y]) {
					max = Math.max(search(tmpx, tmpy, n) + 1, max);
				}
			}
		}
		return memo[x][y] = max;
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
