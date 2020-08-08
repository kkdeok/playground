package com.kkd.exercise.algorithm.baekjoon.graph;

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
	
	private static int size = 1001;
	private static int[][] board = new int[size][size];
	private static int[][] visit = new int[size][size];
	
	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int m = Integer.parseInt((line[0]));
		int n = Integer.parseInt((line[1]));
		
		// init
		for (int i=0 ; i<size ; i++) {
			Arrays.fill(visit[i], -1);
		}
		
		for (int i=0 ; i<n ; i++) {
			line = br.readLine().split(" ");
			for (int j=0 ; j<m ; j++) {
				int val = Integer.parseInt(line[j]);
				board[i][j] = val;
			}
		}
		
		int ans = doBFS(n, m); // n = 세로, m = 가로
		System.out.println(ans);
	}
	
	private static int doBFS(int n, int m) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		
		for (int x=0 ; x<n ; x++) {
			for (int y=0 ; y<m ; y++) {
				if (board[x][y] == 1) {
					xq.add(x); yq.add(y);
					visit[x][y] = 0;
				}
			}
		}
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			
			for (int i=0 ; i<4 ; i++) {
				int x = tempx + dx[i];
				int y = tempy + dy[i];
				if (x>=0 && x<n && y>=0 && y<m) {
					if (board[x][y] == 0 && visit[x][y] == -1) {
						board[x][y] = 1;
						xq.add(x); yq.add(y);
						visit[x][y] = visit[tempx][tempy] + 1;
					}
				}
			}
		}
		
		int ans = -1;
		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<m ; j++) {
				if (board[i][j] == 0) {
					return -1;
				}
				ans = Math.max(ans, visit[i][j]);
			}
		}
		return ans;
	}
}
