package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/9372
 */
public class _9372 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int m = Integer.parseInt(line[1]);

			int[][] board = new int[n+1][n+1];
			boolean[] visit = new boolean[n+1];

			for (int i=0 ; i<m ; i++) {
				line = br.readLine().split(" ");
				int a = Integer.parseInt(line[0]);
				int b = Integer.parseInt(line[1]);
				board[a][b] = board[b][a] = 1;
			}

			Queue<Integer> q = new LinkedList<>();
			q.add(1); visit[1] = true;

			int ans = 0;
			while (!q.isEmpty()) {
				int temp = q.poll();
				for(int i=1 ; i<=n ; i++) {
					if (board[temp][i] == 1 && !visit[i]) {
						q.add(i); ans++; visit[i] = true;
					}
				}
			}
			System.out.println(ans);
		}
	}
}
