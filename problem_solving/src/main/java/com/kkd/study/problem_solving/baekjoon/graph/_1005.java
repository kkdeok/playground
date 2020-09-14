package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1005
 */
public class _1005 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int size = 1001;
	private static int[] ind = new int[size];
	private static int[] time = new int[size];
	private static int[][] adj = new int[size][size];
	
	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int k = Integer.parseInt(line[1]);
			
			line = br.readLine().split(" ");
			for (int i=1 ; i<=n ; i++) {
				time[i] = Integer.parseInt(line[i-1]);
			}
			
			for (int i=0 ; i<k ; i++) {
				line = br.readLine().split(" ");
				int x = Integer.parseInt(line[0]);
				int y = Integer.parseInt(line[1]);
				adj[x][y] = 1;
				ind[y]++;
			}
			
			int w = Integer.parseInt(br.readLine());
			System.out.println(doBFS(n, w));
			
			Arrays.fill(ind, 0);
			Arrays.fill(time, 0);
			for (int i=0 ; i<size ; i ++) {
				Arrays.fill(adj[i], 0);
			}
		}
	}
	
	private static int doBFS(int n, int w) {
		Map<Integer, Integer> map = new HashMap<>(); 
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> t = new LinkedList<>();
		
		for (int i=1 ; i<=n ; i++) {
			if (ind[i] == 0) {
				q.add(i);
				t.add(time[i]);
			}
		}
		
		while (!q.isEmpty()) {
			int tempq = q.poll();
			int tempt = t.poll();
			
			if (tempq == w) {
				return tempt;
			}
			
			for (int i=1 ; i<=n ; i++) {
				if (adj[tempq][i] == 1) {
					ind[i]--;
					if (ind[i] == 0) {
						q.add(i);
						t.add(Math.max(tempt + time[i], map.getOrDefault(i, 0)));
					} else {
						int max = map.getOrDefault(i, 0);
						map.put(i, Math.max(max, tempt + time[i]));
					}
				}
			}
		}
		return 0;
	}
}
