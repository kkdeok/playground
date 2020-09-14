package com.kkd.study.problem_solving.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/6603
 */
public class _6603 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static List<List<Integer>> ans;

	public static void main(String[] args) throws Exception {
		while(true) {
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			if (n == 0) break;

			ans = new ArrayList<>();
			int[] arr = new int[n];
			// insert given data
			for (int i = 0; i < n; i++)
				arr[i] = Integer.parseInt(line[i + 1]);
			process(arr, n, -1, new ArrayList<>());
			
			for (List<Integer> list : ans) {
				for (int val : list) {
					System.out.print(val + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	private static void process(int[] arr, int n, int idx, List<Integer> list) {
		if (list.size() == 6) {
			ans.add(new ArrayList<>(list));
			return;
		}
		
		for (int i=idx+1 ; i<n ; i++) {
			list.add(arr[i]);
			process(arr, n, i, list);
			list.remove(list.size()-1);
		}
	}
}
