package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1766
 */
public class _1766 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


	private static int[] ind;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		adj = new ArrayList[n+1];
		ind = new int[n+1];
		for (int i=1 ; i<=n ; i++) 
			adj[i] = new ArrayList<>();

		for (int i=0 ; i<m ; i++) {
			line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			adj[a].add(b);
			ind[b]++;
		}

		Queue<Integer> q = new PriorityQueue<>();
		for (int i=1 ; i<=n ; i++) {
			if (ind[i] == 0) q.add(i);
		}
		
		while (!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp + " ");

			for (int next : adj[temp]) {
				if (--ind[next] == 0) {
					q.add(next);
				}
			}
		}
	}
}
