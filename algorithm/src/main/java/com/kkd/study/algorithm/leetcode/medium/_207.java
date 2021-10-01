package com.kkd.study.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule/
 */
public class _207 {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (prerequisites.length == 0) return true;

		List<List<Integer>> list = new ArrayList<>();
		for (int i=0 ; i<numCourses ; i++) {
			list.add(new ArrayList<>());
		}

		int[] ind = new int[numCourses];
		for (int i=0 ; i<prerequisites.length ; i++) {
			int x = prerequisites[i][0];
			int y = prerequisites[i][1];
			list.get(y).add(x);
			ind[x]++;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i=0 ; i<numCourses ; i++) {
			// if indegree is zero, then add into the queue to use start node.
			if (ind[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int node = q.poll();
			for (Integer next : list.get(node)) {
				if (--ind[next] == 0) {
					q.offer(next);
				}
			}
		}

		for (int i=0 ; i<numCourses ; i++) {
			if (ind[i] > 0) return false;
		}
		return true;
	}
}
