package com.kkd.study.problem_solving.baekjoon.graph;

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
	
	private static final int SIZE = 51;
	
	private static int[][] board = new int[SIZE][SIZE];
	private static int[][] visit = new int[SIZE][SIZE];
		
	private static int[] dx = new int[]{0, 0, -1, 1, -1, -1 ,1 ,1};
	private static int[] dy = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		while (true) {
			String[] line = br.readLine().split(" ");
			int w = Integer.parseInt(line[0]);
			int h = Integer.parseInt(line[1]);
			
			if (w == 0 && h == 0) {
				break;
			}
			
			for (int i=0 ; i<h ; i++) {
				line = br.readLine().split(" ");
				for (int j=0 ; j<w ; j++) {
					board[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			int count = 0;
			for (int i=0 ; i<h ; i++) {
				for (int j=0 ; j<w ; j++) {
					if (board[i][j] == 1 && visit[i][j] == 0) {
						doBFS(h, w, i, j);
						count++;
					}
				}
			}
			System.out.println(count);
			
			for (int i=0 ; i<SIZE ; i++) {
				Arrays.fill(board[i], 0);
				Arrays.fill(visit[i], 0);
			}
		}
	}
	
	private static void doBFS(int h, int w, int x, int y) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		
		xq.add(x); yq.add(y);
		visit[x][y] = 1;
		
		while (!xq.isEmpty()) {
			int tempx = xq.poll();
			int tempy = yq.poll();
			
			for (int i=0 ; i<8 ; i++) {
				x = tempx + dx[i];
				y = tempy + dy[i];
				
				if (x >= 0 && x < h && y >=0 && y < w) {
					if (board[x][y] == 1 && visit[x][y] == 0) {
						xq.add(x); yq.add(y);
						visit[x][y] = 1;
					}
				}
			}
		}
	}
}
