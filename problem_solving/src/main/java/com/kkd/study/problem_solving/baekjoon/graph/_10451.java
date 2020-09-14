package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/10451
 */
public class _10451 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int SIZE = 1001;
	private static int[][] BOARD = new int[SIZE][SIZE];
	private static int[] VISITED = new int[SIZE];
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int t = Integer.parseInt(line[0]);
		while (t-- > 0) {
			line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			line = br.readLine().split(" ");
			for (int i=1 ; i<=n ; i++) {
				int j = Integer.parseInt(line[i-1]);
				BOARD[i][j] = 1;
			}
			
			int count = 0;
			for (int i=1 ; i<=n ; i++) {
				if (VISITED[i] == 0) {
					doBFS(n, i);
					count++;
				}
			}
			System.out.println(count);
			
			// init
			for (int i=0 ; i<SIZE ; i++) {
				Arrays.fill(BOARD[i], 0);
				VISITED[i] = 0;
			}
		}
	}
	
	private static void doBFS(int n, int v) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		VISITED[v] = 1;
		
		while (!q.isEmpty()) {
			int tempv = q.poll();
			for (int i=1 ; i<=n ; i++) {
				if (BOARD[tempv][i] == 1 && VISITED[i] == 0) {
					q.add(i);
					VISITED[i] = 1;
				}
			}
		}
	}
}
