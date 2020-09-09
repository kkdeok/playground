package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/4386
 */
public class _4386 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static class Edge {
		int s;
		int e;
		double w;

		public Edge(int s, int e, double w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	private static class Star {
		int n;
		double x, y;

		public Star(int n, double x, double y) {
			this.n = n;
			this.x = x;
			this.y = y;
		}
		
		public double distance(Star other) {
			double temp = (this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y);
			return Math.sqrt(temp);
		}
	}
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		
		List<Star > list = new ArrayList<>();
		for (int i=1 ; i<=n ; i++) {
			String[] line = br.readLine().split(" ");
			double x = Float.parseFloat(line[0]);
			double y = Float.parseFloat(line[1]);
			list.add(new Star(i, x, y));
		}
		
		Map<Integer, List<Edge>> map = new HashMap<>();
		for (int i=0 ; i<n ; i++) {
			for (int j=i+1 ; j<n ; j++) {
				Star a = list.get(i);
				Star b = list.get(j);
				double distance = a.distance(b);
				
				List<Edge> edges = map.getOrDefault(a.n, new ArrayList<>());
				edges.add(new Edge(a.n, b.n, distance));
				map.put(a.n, edges);
				
				edges = map.getOrDefault(b.n, new ArrayList<>());
				edges.add(new Edge(b.n, a.n, distance));
				map.put(b.n, edges);
			}
		}
		
		boolean[] visit = new boolean[n+1];
		PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> Double.compare(a.w, b.w));
		q.addAll(map.get(1));
		visit[1] = true;
		
		double ans = 0.0d;
		while (!q.isEmpty()) {
			Edge edge = q.poll();
			if (!visit[edge.e]) {
				ans += edge.w;
				q.addAll(map.getOrDefault(edge.e, new ArrayList<>()));
				visit[edge.e] = true;
			}
		}
		System.out.printf("%.2f", ans);
	}
}
