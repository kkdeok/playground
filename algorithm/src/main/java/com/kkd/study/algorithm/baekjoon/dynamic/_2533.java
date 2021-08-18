package com.kkd.study.algorithm.baekjoon.dynamic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/2533
 */
public class _2533 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static List<List<Integer>> adj = new ArrayList<>();
	private static final int SIZE = 1000001;
	private static boolean[] visit = new boolean[SIZE];
	// [][0] = no early adaptor , [][1] = early adaptor
	private static int[][] memo = new int[SIZE][2];


	public static void main (String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());

		// init adj list
		for (int i=0 ; i<=n ; i++) {
			adj.add(new ArrayList<>());
		}

		// init memo
		for (int i=0 ; i<SIZE ; i++) {
			Arrays.fill(memo[i], -1);
		}

		int start=0;
		for (int i=0 ; i<n-1 ; i++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			adj.get(x).add(y);
			adj.get(y).add(x);

			// set starting point
			if (start==0) {
				start = x;
			}
		}

		int ret = Math.min(find(start, 0), find(start, 1) + 1);
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int find(int node, int ea) {
		if (memo[node][ea] != -1) {
			return memo[node][ea];
		}

		visit[node] = true;
		int tmp = 0;
		List<Integer> friends = adj.get(node);

		for (int friend : friends) {
			if (visit[friend]) {
				continue;
			}
			if (ea == 0) { // if i'm not early adaptor
				tmp += find(friend, 1) + 1;
			} else {
				tmp += Math.min(find(friend, 0), find(friend, 1) + 1);
			}
		}
		visit[node] = false;
		return memo[node][ea] = tmp;
	}
}
