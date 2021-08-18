package com.kkd.study.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2056
 */
public class _2056 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int size = 10001;
	private static List<Integer>[] grid = new ArrayList[size];
	private static int[] ind = new int[size];
	private static int[] time = new int[size];
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		
		for (int i=1 ; i<=n ; i++) {
			grid[i] = new ArrayList<>();
			line = br.readLine().split(" ");
			time[i] = Integer.parseInt(line[0]);
			
			int c = Integer.parseInt(line[1]);
			for (int j=0 ; j<c ; j++) {
				int v = Integer.parseInt(line[2 + j]);
				grid[v].add(i);
				ind[i]++;
			}
		}
		
		int[] ans = new int[n+1];
		Queue<Integer> q = new LinkedList<>();
		for (int i=1 ; i<=n ; i++) {
			if (ind[i] == 0) {
				q.add(i);
				ans[i] = time[i];
			}
		}
		
		while (!q.isEmpty()) {
			int temp = q.poll(); // job 번호
			for (int next : grid[temp]) {
				if (ans[next] < ans[temp] + time[next]) {
					ans[next] = ans[temp] + time[next]; 
				}
				if (--ind[next] == 0) {
					q.add(next);
				}
			}
		}
		
		// 문제를 읽어보면, '서로 선행 관계가 없는 작업들은 동시에 수행 가능하다.'
		// 라는 말이 나온다. 이 말은 n 번째가 수행 시간이 가장 오래 걸린다고 보장할 수 없다는 의미와도 같다.
		int a = 0;
		for (int i=1 ; i<=n ; i++) {
			a = Math.max(a, ans[i]);
		}
		System.out.println(a);
	}
}
