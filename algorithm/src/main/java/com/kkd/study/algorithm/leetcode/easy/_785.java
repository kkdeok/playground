package com.kkd.study.algorithm.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

public class _785 {
	public boolean isBipartite(int[][] graph) {
		int xLen = graph.length;
		int yLen = graph[0].length;

		int[] visit = new int[xLen];

		for (int j=0 ; j<xLen ; j++) {
			if (visit[j] != 0) continue;

			Queue<Integer> q = new LinkedList<>();
			q.offer(j);
			visit[j] = 1;

			while (!q.isEmpty()) {
				int node = q.poll();
				for (int i=0 ; i<graph[node].length ; i++) {
					int connectedNode = graph[node][i];
					if (visit[connectedNode] == 0) {
						q.offer(connectedNode);
						visit[connectedNode] = 3 - visit[node];
					} else if (visit[connectedNode] == visit[node]) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
