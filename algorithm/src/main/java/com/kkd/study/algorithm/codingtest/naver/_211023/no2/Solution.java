package com.kkd.study.algorithm.codingtest.naver._211023.no2;

import java.util.*;

/**
 * You are given a directed graph consisting of N vertices, numbered from 1 to N, and N edges.
 * 1에서 N까지 번호가 매겨진 N 꼭짓점과 N 가장자리로 구성된 방향 그래프가 제공됩니다.
 *
 * The graph is described by two array. A and B, both of length N. A pair A[K], B[K] (for K form
 * 0 to N-1) describes a directed edge from vertex A[K] to vertex B[K].
 * 그래프는 두 개의 배열로 설명됩니다. A와 B는 둘 다 길이가 N 입니다. A[K], B[K] 쌍(K 형식 0에서 N-1까지)은 꼭짓점 A[K]에서 꼭짓점 B[K]까지의
 * 방향 모서리를 나타냅니다.
 *
 * Note that the graph might contain self-loops (edges where A[K] = B[K]) and/or multiple edges
 * between the same pair of vertices.
 * 그래프에는 자체 루프(A[K] = B[K]인 모서리) 및/또는 동일한 정점 쌍 사이의 다중 모서리가 포함될 수 있습니다.
 *
 * Your task is to check whether the given graph is a cycle. A graph is a cycle if it is possible
 * to start at some vertex and, by following the provided edges, visit all the other vertices and
 * return to the starting point.
 * 당신의 임무는 주어진 그래프가 사이클인지 확인하는 것입니다. 그래프는 어떤 정점에서 시작하여 제공된 모서리를 따라 다른 모든 정점을 방문하여 시작점으로 돌아갈 수 있는
 * 경우 순환입니다.
 *
 *
 * https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 */
public class Solution {
	public boolean solution(int[] A, int[] B) {
		// write your code in Java SE 8
		int n = A.length;

		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			graph.put(i, new HashSet<>());
		}

		for (int i = 0; i < n; i++) {
			graph.get(A[i]).add(B[i]);
		}

		boolean[] visited = new boolean[n + 1];
		boolean[] callStack = new boolean[n + 1];

		boolean result = find(1, visited, callStack, graph);
		if (isVisitAllNode(visited, n)) {
			return result;
		}
		return false;
	}

	private boolean find(int node, boolean[] visited, boolean[] callStack,
		Map<Integer, Set<Integer>> graph) {
		if (callStack[node]) {
			return true;
		}
		if (visited[node]) {
			return false;
		}
		visited[node] = callStack[node] = true;

		for (int nextNode : graph.get(node)) {
			if (find(nextNode, visited, callStack, graph)) {
				return true;
			}
		}
		callStack[node] = false;
		return false;
	}

	private boolean isVisitAllNode(boolean[] visited, int n) {
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		boolean result = solution.solution(new int[] { 1, 2, 1 }, new int[] { 2, 3, 3 });
		System.out.println(result);
	}

}