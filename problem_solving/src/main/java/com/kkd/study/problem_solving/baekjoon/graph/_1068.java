package com.kkd.study.problem_solving.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1068
 */
public class _1068 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static Map<Integer, List<Integer>> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int root = 0;
		for (int i=0 ; i<n ; i++) {
			if (arr[i] == -1) {
				root = i;
			} else {
				List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
				list.add(i);
				map.put(arr[i], list);
			}
		}
		int skip = Integer.parseInt(br.readLine());
		
		int ans = doDFS(root, skip);
		System.out.println(ans);
	}

	private static int doDFS(int node, int skip) {
		if (node == skip) {
			return 0;
		}
		
		List<Integer> children = map.getOrDefault(node, new ArrayList<>());
		if (children.isEmpty()) {
			return 1; // leaf node
		}

		int ret = 0;
		int cnt = children.size();
		if (cnt == 1 && children.get(0) == skip) {
			return 1;
		}
		
		for (int child : children) {
			ret += doDFS(child, skip);
		}
		return ret;
	}
}