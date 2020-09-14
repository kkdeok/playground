package com.kkd.study.problem_solving.leetcode.sorting;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleII {
    
    public static void main(String[] args) {
        CourseScheduleII app = new CourseScheduleII();
//        int[] ans = app.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        int[] ans = app.findOrder(2, new int[][]{{1, 0}});
        for (int val : ans) {
            System.out.print(val + " ");
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // init 
        Map<Integer, List<Integer>> adj = new HashMap<>(); 
        int[] ind = new int[numCourses];
        for (int i=0 ; i<numCourses ; i++) {
            adj.put(i, new ArrayList<>());
        }
        
        // make adj and in-degrees
        for (int i=0 ; i<prerequisites.length ; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            List<Integer> list = adj.get(b);
            list.add(a);
            adj.put(b, list);
            ind[a]++;
        }

        // topological sort
        Queue<Integer> q = new LinkedList<>();
        Queue<List<Integer>> l = new LinkedList<>();
        for (int i=0 ; i<numCourses ; i++) {
            if (ind[i] == 0) {
                q.add(i);
                List<Integer> list = new ArrayList<>();
                list.add(i);
                l.add(list);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int tempq = q.poll();
            ans.add(tempq);
            for (int next : adj.get(tempq)) {
                if (--ind[next] == 0) {
                    q.add(next);
                }
            }
        }
        
        // print result
        if (ans.size() != numCourses) {
            return new int[]{};
        } else {
            return ans.stream().mapToInt(i -> i).toArray();
        }
    }
}
