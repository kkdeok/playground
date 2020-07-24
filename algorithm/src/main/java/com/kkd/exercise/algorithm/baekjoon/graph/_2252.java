package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2252
 */
public class _2252 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		int[] ind = new int[n + 1];
		List<List<Integer>> adj = new ArrayList<>();
		for (int i=0 ; i<=n ; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i=0 ; i<m ; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			adj.get(a).add(b);
			ind[b]++;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i=1 ; i<=n ; i++) {
			if (ind[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int tmp = q.poll();
			bw.write(tmp + " ");
			for (Integer next : adj.get(tmp)) {
				if (--ind[next] == 0) {
					q.add(next);
				}
			}
		}
		bw.write("\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
