package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _5427 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static final int size = 1001;
	private static int[][] fire = new int[size][size];
	private static char[][] board = new char[size][size];
	private static boolean[][] visit = new boolean[size][size];
	
	private static final int[] dx = new int[]{0, 0, -1, 1};
	private static final int[] dy = new int[]{-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			// init
			for (int i=0 ; i<size ; i++) {
				Arrays.fill(fire[i], Integer.MAX_VALUE);
				Arrays.fill(board[i], '\u0000');
				Arrays.fill(visit[i], false);
			}
			
			String[] line = br.readLine().split(" ");
			int w = Integer.parseInt(line[0]);
			int h = Integer.parseInt(line[1]);
			int sx=-1, sy=-1;
			for (int i=0 ; i<h ; i++) {
				board[i] = br.readLine().toCharArray();
				for (int j=0 ; j<w ; j++) {
					if (board[i][j] == '@') {
						sx = i; sy = j;
						break;
					}
				}
			}
			
			setFire(h, w);
			for (int i=0 ; i<size ; i++) Arrays.fill(visit[i], false);
			
			int ans = escape(sx, sy, h, w);
			if (ans == Integer.MIN_VALUE) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(ans);
			}
		}
	}
	
	private static int escape(int sx, int sy, int h, int w) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		Queue<Integer> cq = new LinkedList<>();
		
		xq.add(sx); yq.add(sy); cq.add(0);
		visit[sx][sy] = true;
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			int tempc = cq.poll();
			
			if (tempx == 0 || tempx == h-1 || tempy == 0 || tempy == w-1) {
				return tempc + 1;
			}
			
			for (int i=0 ; i<4 ; i++) {
				int x = tempx + dx[i];
				int y = tempy + dy[i];
				if (x>=0 && x<h && y>=0 && y<w) {
					if (board[x][y] != '#' && !visit[x][y] && fire[x][y] > tempc + 1) {
						xq.add(x); yq.add(y); cq.add(tempc + 1);
						visit[x][y] = true;
					}
				}
			}
		}
		return Integer.MIN_VALUE;
	}
	
	private static void setFire(int h, int w) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		Queue<Integer> cq = new LinkedList<>();
		
		for (int i=0 ; i<h ; i++) {
			for (int j = 0; j < w; j++) {
				if (board[i][j] == '*') {
					xq.add(i); yq.add(j); cq.add(0);
					visit[i][j] = true;
				}
				if (board[i][j] == '#') {
					fire[i][j] = Integer.MIN_VALUE;
				}
			}
		}
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			int tempc = cq.poll();
			fire[tempx][tempy] = tempc;
			
			for (int i=0 ; i<4 ; i++) {
				int x = tempx + dx[i];
				int y = tempy + dy[i];
				if (x>=0 && x<h && y>=0 && y<w) {
					if (board[x][y] != '#' && !visit[x][y]) {
						xq.add(x); yq.add(y); cq.add(tempc + 1);
						visit[x][y] = true;
					}
				}
			}
		}
	}
}
