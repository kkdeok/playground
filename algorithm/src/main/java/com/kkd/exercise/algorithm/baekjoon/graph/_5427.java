package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _5427 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static final int size = 1001;
	private static char[][] board = new char[size][size];
	private static boolean[][] visit = new boolean[size][size];
	private static int[][] fire = new int[size][size];
	
	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			for (int i=0 ; i<size ; i++) {
				Arrays.fill(visit[i], false);
				Arrays.fill(fire[i], Integer.MIN_VALUE);
			}
			
			String[] line = br.readLine().split(" ");
			int width = Integer.parseInt(line[0]);
			int height = Integer.parseInt(line[1]);
			
			for (int i=0 ; i<height ; i++) {
				board[i] = br.readLine().toCharArray();
			}
			
			checkFire(height, width);
			for (int i=0 ; i<size ; i++) {
				Arrays.fill(visit[i], false);
			}
			
			int ans = doBFS(height, width);
			if (ans == Integer.MIN_VALUE) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(ans);
			}
		}
	}
	
	private static int doBFS(int h, int w) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		Queue<Integer> tq = new LinkedList<>();
		
		for (int i=0 ; i<h ; i++) {
			boolean find = false;
			for (int j=0 ; j<w ; j++) {
				if (board[i][j] == '@') {
					xq.add(i); yq.add(j); tq.add(0);
					visit[i][j] = find = true;
					break;
				}
			}
			if (find) break;
		}
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			int tempt = tq.poll();
			
			if (tempx == 0 || tempx == h-1 || tempy == 0 || tempy == w-1) {
				return tempt + 1;
			}
			
			for (int i=0 ; i<4 ; i++) {
				int x = tempx + dx[i];
				int y = tempy + dy[i];
				int t = tempt + 1;
				if (x>=0 && x<h && y>=0 && y<w) {
					if (board[x][y] != '#' && !visit[x][y]) { 
						if (fire[x][y] > t) {
							xq.add(x); yq.add(y); tq.add(t);
							visit[x][y] = true;
						}
					}
				}
			}
		}
		return Integer.MIN_VALUE; 
	}
	
	private static void checkFire(int h, int w) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		Queue<Integer> tq = new LinkedList<>();
		
		for (int i=0 ; i<h ; i++) {
			for (int j=0 ; j<w ; j++) {
				if (board[i][j] == '*') {
					xq.add(i); yq.add(j); tq.add(0);
					visit[i][j] = true;
				}
			}
		}
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			int tempt = tq.poll(); // time
			fire[tempx][tempy] = tempt;
			
			for (int i=0 ; i<4 ; i++) {
				int x = tempx + dx[i];
				int y = tempy + dy[i];
				if (x>=0 && x<h && y>=0 && y<w) {
					if (board[x][y] != '#') {
						if (!visit[x][y]) {
							xq.add(x); yq.add(y); tq.add(tempt + 1);
							visit[x][y] = true;
						}
					}
				}
			}
		}
	}
}
