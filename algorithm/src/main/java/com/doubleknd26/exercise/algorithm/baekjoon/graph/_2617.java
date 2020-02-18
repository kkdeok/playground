package com.doubleknd26.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/2617
 */
public class _2617 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static List<List<Integer>> adj = new ArrayList<>();
	private static int[] visit = new int[100];
	private static int[][] comp = new int[100][2];

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split( " ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		for (int i=0 ; i<=n ; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i=0 ; i<m ; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			adj.get(b).add(a);
		}

		for (int i=1 ; i<=n ; i++) {
			if (visit[i] == 0) {
				comp[i][0] += 0;
				comp[i][1] += dfs(i, 0);
			}
		}

		int cnt = n * (n-1) / 2;
		int ans = 0;
		for (int i=1 ; i<=n ; i++) {
			System.out.println(comp[i][0] + " : " + comp[i][1]);

			if (comp[i][0] >= cnt || comp[i][1] >= cnt) {
				ans++;
			}
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int dfs(int point, int smallCount) {
		visit[point] = 1;
		List<Integer> list = adj.get(point);

		int biggerCount = 0;
		int size = list.size();
		for (int i=0 ; i<size ; i++) {
			int tmp = list.get(i);
			if (visit[tmp] == 0) {
				biggerCount += dfs(tmp, smallCount + 1) + 1;
			} else {
				comp[tmp][0] += smallCount;
				biggerCount += comp[tmp][1];
			}
		}
		comp[point][0] += smallCount;
		comp[point][1] += biggerCount;
		return comp[point][1];
	}
}
