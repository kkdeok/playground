package com.kkd.study.algorithm.baekjoon.backtracking;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/14888
 */
public class _14888 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static List<Integer> ansList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] op = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		process(arr, op, 1, arr[0]);
		System.out.println(ansList.stream().mapToInt(i -> i).max().getAsInt());
		System.out.println(ansList.stream().mapToInt(i -> i).min().getAsInt());
	}


	public static void process(int[] arr, int[] op, int idx, int result) {
		if (!hasOperator(op)) {
			ansList.add(result);
		}

		for (int i=0 ; i<4 ; i++) {
			if (op[i] == 0) continue;

			op[i]--;
			if (i == 0) process(arr, op, idx + 1, result + arr[idx]);
			else if (i == 1) process(arr, op, idx + 1, result - arr[idx]);
			else if (i == 2) process(arr, op, idx + 1, result * arr[idx]);
			else process(arr, op, idx + 1, result / arr[idx]);
			op[i]++;
		}
	}

	public static boolean hasOperator(int[] op) {
		for (int i=0 ; i<4 ; i++) {
			if (op[i] > 0) {
				return true;
			}
		}
		return false;
	}

}
