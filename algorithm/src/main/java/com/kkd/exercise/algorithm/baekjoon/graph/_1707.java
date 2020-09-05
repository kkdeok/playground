package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1707
 */
public class _1707 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static Map<Integer, List<Integer>> adj = new HashMap<>(); 
	private static final int[] visit = new int[20001];
	
	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String[] line = br.readLine().split(" ");
			int v = Integer.parseInt(line[0]);
			int e = Integer.parseInt(line[1]);
			
			for (int i=0 ; i<e ; i++) {
				line = br.readLine().split(" ");
				int x = Integer.parseInt(line[0]);
				int y = Integer.parseInt(line[1]);
				
				List<Integer> listX = adj.getOrDefault(x, new ArrayList<>());
				listX.add(y);
				adj.put(x, listX);
				
				List<Integer> listY = adj.getOrDefault(y, new ArrayList<>());
				listY.add(x);
				adj.put(y, listY);
			}
			
			boolean isBipartiteGraph = true;
			for(int i=1 ; i<=v ; i++) {
				if (visit[i] == 0) {
					isBipartiteGraph = doDFS(i, 1);
				}
				if (!isBipartiteGraph) {
					break;
				}
			}
			System.out.println(isBipartiteGraph ? "YES" : "NO");
			
			Arrays.fill(visit, 0);
			adj.clear();
		}
	}
	
	private static boolean doDFS(int v, int c) {
		visit[v] = c;
		boolean ret = true;
		for (Integer next : adj.getOrDefault(v, new ArrayList<>())) {
			if (visit[next] == 0) {
				ret = doDFS(next, 3 - c);
			} else if (visit[next] == c) {
				ret = false;
			}
			if (!ret) break;
		}
		return ret;
	}
}
