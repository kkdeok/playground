package com.kkd.study.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2252
 */
public class _2252 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static final int size = 32001;
	private static ArrayList<Integer>[] grid = new ArrayList[size];
	private static int[] ind = new int[size];
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		
		for (int i=1 ; i<=n ; i++) {
			grid[i] = new ArrayList<>();
		}
		
		for (int i=0 ; i<m ; i++) {
			line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			grid[a].add(b);
			ind[b]++;
		}
		
		List<Integer> ans = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		for (int i=1 ; i<=n ; i++) {
			if (ind[i] == 0) {
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int temp = q.poll();
			ans.add(temp);
			for (int next : grid[temp]) {
				if (--ind[next] == 0) {
					q.add(next);
				}
			}
		}
		
		for (int a : ans) {
			System.out.print(a + " ");
		}
	}
}
