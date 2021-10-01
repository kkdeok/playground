package com.kkd.study.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _210 {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<List<Integer>> adj = new ArrayList<>();
		for (int i=0 ; i<numCourses ; i++) {
			adj.add(new ArrayList<>());
		}

		int[] ind = new int[numCourses];
		for (int i=0 ; i<prerequisites.length ; i++) {
			// y -> x
			int x = prerequisites[i][0];
			int y = prerequisites[i][1];
			adj.get(y).add(x);
			ind[x]++;
		}

		int idx = 0;
		int[] res = new int[numCourses];
		Queue<Integer> q = new LinkedList<>();
		for (int i=0 ; i<numCourses ; i++) {
			if (ind[i] == 0) {
				q.offer(i);
				res[idx++] = i;
			}
		}

		while (!q.isEmpty()) {
			int node = q.poll();
			for (Integer next : adj.get(node)) {
				if (--ind[next] == 0) {
					q.offer(next);
					res[idx++] = next;
				}
			}
		}

		return idx == numCourses ? res : new int[]{};
	}
}
