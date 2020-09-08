package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1922
 * MST (minimum spanning tree) - Prim Algorithm
 */
public class _1922 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static class Edge {
		int s;
		int e;
		int w;

		Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		int m = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수
		
		boolean[] visit = new boolean[n+1];
		Map<Integer, List<Edge>> map = new HashMap<>(); 
		for (int i=0 ; i<m ; i++) {
			String[] line = br.readLine().split(" ");
			int s = Integer.parseInt(line[0]);
			int e = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			Edge edge = new Edge(s, e, w);
			List<Edge> list = map.getOrDefault(s, new ArrayList<>());
			list.add(edge);
			map.put(s, list);
			
			edge = new Edge(e, s, w);
			list = map.getOrDefault(e, new ArrayList<>());
			list.add(edge);
			map.put(e, list);
		}
		PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
		q.addAll(map.get(1));
		visit[1] = true;
		
		int ans = 0;
		while (!q.isEmpty()) {
			Edge edge = q.poll();
			if (!visit[edge.e]) {
				visit[edge.e] = true;
				ans += edge.w;
				q.addAll(map.get(edge.e));
			}
		}
		System.out.println(ans);
	}
}
