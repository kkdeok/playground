package com.kkd.exercise.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {


	public static void main(String[] args) {
		System.out.println(new Solution().canFinish(3,
				new int[][]{{0,1},{1,0}}));
	}

	static class Solution {
		public boolean canFinish(int numCourses, int[][] prerequisites) {
			List<List<Integer>> adj = new ArrayList<>();
			int[] check = new int[numCourses];
			int[] ind = new int[numCourses];
			for (int i = 0; i < numCourses; i++) {
				adj.add(new ArrayList<>());
				check[i] = ind[i] = 0;
			}

			for (int[] pre : prerequisites) {
				int x = pre[0];
				int y = pre[1];
				adj.get(y).add(x);
				ind[x]++;
			}

			Queue<Integer> q = new LinkedList<>();
			for (int i=0 ; i<numCourses ; i++) {
				if (ind[i] == 0) {
					q.add(i);
				}
			}

			if (q.isEmpty()) {
				return false;
			}

			int count = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				for (int i=0 ; i<size ; i++) {
					int course = q.poll();
					count++;

					int len = adj.get(course).size();
					for (int j = 0; j < len; j++) {
						int next = adj.get(course).get(j);
						ind[next]--;
						if (ind[next] == 0) {
							q.add(next);
						}
					}
				}
			}
			return count == numCourses;
		}
	}
}

