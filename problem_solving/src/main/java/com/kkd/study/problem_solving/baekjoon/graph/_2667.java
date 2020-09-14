package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/2667
 */
public class _2667 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int SIZE = 26;
	
	private static int[][] board = new int[SIZE][SIZE];
	private static int[][] visited = new int[SIZE][SIZE]; 
	
	private static int[] dx = new int[]{0, 0, -1, 1};
	private static int[] dy = new int[]{-1, 1, 0, 0};
	
	
	public static void main(String[] args) throws Exception {
		String line = br.readLine();
		int n = Integer.parseInt(line);
		
		for (int i=0 ; i<n ; i++) {
			line = br.readLine();
			char[] arr = line.toCharArray(); 
			for (int j=0 ; j<n ; j++) { 
				int value = arr[j] == '0' ? 0 : 1;
				board[i][j] = value;
			}
		}
		
		List<Integer> counts = new ArrayList<>(); 
		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<n ; j++) {
				if (board[i][j] == 1 && visited[i][j] == 0) {
					int count = doBFS(n, i, j);
					counts.add(count);
				}
			}
		}
		Collections.sort(counts);
		System.out.println(counts.size());
		for (int count : counts) {
			System.out.println(count);
		}
	}
	
	private static int doBFS(int n, int x, int y) {
		Queue<Integer> xq = new LinkedList<>();
		Queue<Integer> yq = new LinkedList<>();
		int count = 0;
		
		xq.add(x); yq.add(y);
		visited[x][y] = 1;
		
		while (!xq.isEmpty()) {
			x = xq.poll();
			y = yq.poll();
			count++;
			
			for (int i=0 ; i<4 ; i++) {
				int tempx = x + dx[i];
				int tempy = y + dy[i];
				if (tempx >= 0 && tempx < n && tempy >= 0 && tempy < n) {
					if (board[tempx][tempy] == 1 && visited[tempx][tempy] == 0) {
						xq.add(tempx); yq.add(tempy);
						visited[tempx][tempy] = 1;
					}
				}
			}
		}
		
		return count;
	}
}
