package com.doubleknd26.exercise.algorithm.baekjoon.backtracking;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/14888
 */
public class _14888 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int[] arr;
	private static int[] ops;
	private static Stack<Integer> s = new Stack<>();
	private static int min = Integer.MAX_VALUE;
	private static int max = Integer.MIN_VALUE;

	private static void start() throws IOException {
		int n = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		ops = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		search(n);
		bw.write(max + "\n");
		bw.write(min + "\n");
	}

	private static void search(int n) {
		for (int i=0 ; i<4; i++) {
			if (ops[i] > 0) {
				ops[i]--;
				dfs(i, n);
				ops[i]++;
			}
		}
	}

	private static void dfs(int op, int n) {
		s.push(op);
		if (s.size() == n-1) {
			calculate(n);
		} else {
			search(n);
		}
		s.pop();
	}

	private static void calculate(int n) {
		int num = arr[0];
		for (int i=1 ; i<n ; i++) {
			int tmp = arr[i];
			int op = s.get(i-1);
			num = operate(op, num, tmp);
		}
		if (num < min) {
			min = num;
		}
		if (num > max) {
			max = num;
		}
	}

	private static int operate(int op, int num, int tmp) {
		if (op == 0) {
			return num + tmp;
		} else if (op == 1) {
			return num - tmp;
		} else if (op == 2) {
			return num * tmp;
		} else {
			if (num < 0) {
				num *= -1;
				return num / tmp * -1;
			} else {
				return num / tmp;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
