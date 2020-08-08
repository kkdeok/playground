package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * https://www.acmicpc.net/problem/1707
 */
public class _1707 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int SIZE = 20001;
	private static ArrayList<Integer>[] BOARD = new ArrayList[SIZE];
	private static int[] VISITED = new int[SIZE];
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int k = Integer.parseInt(line[0]);
		
		while (k-- > 0) {
			line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int e = Integer.parseInt(line[1]);

			for (int i=0 ; i<SIZE ; i++) {
				BOARD[i] = new ArrayList<>();
				VISITED[i] = 0;
			}
			
			for (int i = 0; i < e; i++) {
				line = br.readLine().split(" ");
				int x = Integer.parseInt(line[0]);
				int y = Integer.parseInt(line[1]);
				BOARD[x].add(y);
				BOARD[y].add(x);
			}
			boolean isBipartiteGraph = true;
			for (int i=1 ; i<=n ; i++) {
				if (VISITED[i] == 0) { 
					isBipartiteGraph = isBipartiteGraph(i, 1);
					if (!isBipartiteGraph) {
						break;
					}
				}
			}
			System.out.println(isBipartiteGraph ? "YES" : "NO");
		}
	}

	private static boolean isBipartiteGraph(int v, int flag) {
		VISITED[v] = flag;
		int len = BOARD[v].size();
		for (int i = 0; i < len; i++) {
			int nextv = BOARD[v].get(i);
			
			if (VISITED[nextv] == 0) {
				boolean isBipartiteGraph = isBipartiteGraph(nextv, 3 - flag);
				if (!isBipartiteGraph) {
					return false;
				}
			} else if (VISITED[nextv] == flag) {
				return false;
			}
		}
		return true;
	}
}
