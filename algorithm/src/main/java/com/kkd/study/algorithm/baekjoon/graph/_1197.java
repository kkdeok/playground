package com.kkd.study.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1197
 * MST (minimum spanning tree) - Prim Algorithm
 */
public class _1197 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static class Edge {
		int s, e, w;

		Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int v = Integer.parseInt(line[0]);
		int k = Integer.parseInt(line[1]);
		
		Map<Integer, List<Edge>> map = new HashMap<>();
		for (int i=0 ; i<k ; i++) {
			line = br.readLine().split(" ");
			int s = Integer.parseInt(line[0]);
			int e = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			
			List<Edge> list = map.getOrDefault(s, new ArrayList<>());
			list.add(new Edge(s, e, w));
			map.put(s, list);
			
			list = map.getOrDefault(e, new ArrayList<>());
			list.add(new Edge(e, s, w));
			map.put(e, list);
		}

		boolean[] visit = new boolean[v + 1];
		PriorityQueue<Edge> q = new PriorityQueue<>((a,b) -> Integer.compare(a.w, b.w));
		q.addAll(map.get(1));
		visit[1] = true;
		
		int ans = 0;
		while (!q.isEmpty()) {
			Edge edge = q.poll();
			if (!visit[edge.e]) { 
				visit[edge.e] = true;
				q.addAll(map.get(edge.e));
				ans += edge.w;
			}
		}
		System.out.println(ans);
	}
}
