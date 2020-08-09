package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1766
 */
public class _1766 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		
		int[] ind = new int[n+1];
		List<List<Integer>> grid = new ArrayList<>(); 
		for (int i=0 ; i<=n ; i++) {
			grid.add(i, new ArrayList<>());
		}
		
		for (int i=0 ; i<m ; i++) {
			line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			grid.get(a).add(b);
			ind[b]++;
		}

		Queue<Integer> q = new PriorityQueue<>();
		for (int i=1 ; i<=n ; i++) {
			if (ind[i] == 0) {
				q.add(i);
			}
		}
		
		List<Integer> ans = new ArrayList<>();
		while (!q.isEmpty()) {
			int temp = q.poll();
			ans.add(temp);
			
			for (int next : grid.get(temp)) {
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
