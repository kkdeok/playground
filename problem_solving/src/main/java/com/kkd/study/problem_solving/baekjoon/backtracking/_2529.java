package com.kkd.study.problem_solving.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/2529
 */
public class _2529 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static String[] o;
	private static int k;
	private static List<Integer> list = new ArrayList<>();
	private static boolean[] visit = new boolean[10];

	public static void main(String[] args) throws Exception {
		k = Integer.parseInt(br.readLine());
		o = br.readLine().split(" ");

		for (int i = 9; i >= 0; i--) {
			if (doDFS(i, 0, true))
				break;
		}

		list.clear();
		Arrays.fill(visit, false);

		for (int i = 0; i <= 9; i++) {
			if (doDFS(i, 0, false))
				break;
		}
	}

	private static boolean doDFS(int n, int idx, boolean isMax) {
		visit[n] = true;
		list.add(n);

		if (list.size() == k + 1) {
			StringBuilder sb = new StringBuilder();
			list.iterator().forEachRemaining(sb::append);
			System.out.println(sb.toString());
			return true;
		}

		boolean ret = isMax ? doMaxDFS(n, idx) : doMinDFS(n, idx);
		if (ret) {
			return ret;
		}

		list.remove(list.size() - 1);
		visit[n] = false;
		return false;
	}

	private static boolean doMaxDFS(int n, int idx) {
		int s, e;
		String op = o[idx];
		if (op.equals("<")) {
			s = 9;
			e = n + 1;
		} else {
			s = n - 1;
			e = 0;
		}

		for (int i = s; i >= e; i--) {
			if (!visit[i]) {
				if (doDFS(i, idx + 1, true)) {
					return true;
				}
			}
		}
		return false; 
	}

	private static boolean doMinDFS(int n, int idx) {
		int s, e;
		String op = o[idx];
		if (op.equals("<")) {
			s = n + 1;
			e = 9;
		} else {
			s = 0;
			e = n - 1;
		}

		for (int i = s; i <= e; i++) {
			if (!visit[i]) {
				if (doDFS(i, idx + 1, false)) {
					return true;
				}
			}
		}
		return false;
	}
}