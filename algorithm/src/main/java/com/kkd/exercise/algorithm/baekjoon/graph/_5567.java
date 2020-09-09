package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/5567
 */
public class _5567 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static boolean[] visit = new boolean[501];
	private static Map<Integer, List<Integer>> map = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		for (int i=0 ; i<m ; i++) {
			String[] line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			
			List<Integer> list = map.getOrDefault(a, new ArrayList<>());
			list.add(b);
			map.put(a, list);
			
			list = map.getOrDefault(b, new ArrayList<>());
			list.add(a);
			map.put(b, list);
		}
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> r = new LinkedList<>();
		q.add(1); r.add(0);
		visit[1] = true;
		int ans = 0;
		while (!q.isEmpty()) {
			int tempq = q.poll();
			int tempr = r.poll();
			
			if (tempq != 1) {
				ans += 1;
			}
			
			for (int next : map.getOrDefault(tempq, new ArrayList<>())) {
				if (!visit[next] && tempr + 1 <= 2) {
					q.add(next); r.add(tempr + 1);
					visit[next] = true;
				}
			}
		}
		System.out.println(ans);
	}
}
