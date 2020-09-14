package com.kkd.study.problem_solving.baekjoon.bsearch;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1939 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int size = 10001;
	private static ArrayList<Pair<Integer, Integer>>[] grid = new ArrayList[size]; 
	private static int[] visit = new int[size]; // 섬 갯수
	
	public static void main(String[] args) throws Exception {
		
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		
		Arrays.fill(visit, -1);
		for (int i=0 ; i<=n ; i++) {
			grid[i] = new ArrayList<>();
		}
		
		for (int i=0 ; i<m ; i++) {
			line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			// 서로 같은 두 섬 사이에 여러 개의 다리가 있는 경우, 최대 중량만을 저장한다.
			grid[a].add(new Pair<>(b, w));
			grid[b].add(new Pair<>(a, w));
		}
		// 공장이 있는 섬의 위치
		line = br.readLine().split(" ");
		int a = Integer.parseInt(line[0]);
		int b = Integer.parseInt(line[1]);
		int ans = doDFS(n, a, b, Integer.MAX_VALUE);
		System.out.println(ans);
	}
	
	private static int doDFS(int n, int curr, int dest, int weight) {
		if (visit[curr] != -1) {
			return visit[curr];
		}
		if (curr == dest) {
			return weight;
		}
		
		visit[curr] = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (Pair<Integer, Integer> p : grid[curr]) {
			int key = p.getKey();
			int val = p.getValue();
			val = Math.max(map.getOrDefault(key, 0), val);
			map.put(key, val);
		}
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int k = entry.getKey();
			int v = entry.getValue();
			
			int temp = v >= weight 
					? doDFS(n, k, dest, weight)
					: doDFS(n, k, dest, v);
			visit[curr] = Math.max(visit[curr], temp);
		}
		return visit[curr];
	}
}
