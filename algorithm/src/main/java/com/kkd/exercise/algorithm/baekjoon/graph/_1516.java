package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1516
 */
public class _1516 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static final int size = 501;
	private static List<Integer>[] grid = new ArrayList[size];
	private static int[] time = new int[size];
	private static int[] ind = new int[size];
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		
		for (int i=1 ; i<=n ; i++) {
			grid[i] = new ArrayList<>();
		}
		
		for (int i=1 ; i<=n ; i++) {
			line = br.readLine().split(" ");
			time[i] = Integer.parseInt(line[0]);
			int j=1;
			while (true) {
				int temp = Integer.parseInt(line[j++]);
				if (temp == -1) {
					break;
				}
				grid[temp].add(i);
				ind[i]++;
			}
		}
		
		int[] ans = new int[n + 1];
		Queue<Integer> q = new LinkedList<>(); 
		for (int i=1 ; i<=n ; i++) {
			if (ind[i] == 0) {
				q.add(i);
				ans[i] = time[i];
			}
		}
		
		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int next : grid[temp]) {
				if (ans[next] < ans[temp] + time[next]) {
					ans[next] = ans[temp] + time[next];
				}
				if (--ind[next] == 0) {
					q.add(next);
				}
			}
		}
		
		for (int i=1 ; i<=n ; i++) {
			System.out.println(ans[i] + " ");
		}
	}
}
