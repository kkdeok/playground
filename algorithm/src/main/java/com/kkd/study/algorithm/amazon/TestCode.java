package com.kkd.study.algorithm.amazon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Amazon Online Test Demo
 */
public class TestCode {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 1001;
	private static int[][] board = new int[SIZE][SIZE];
	private static boolean[] visit = new boolean[SIZE];

	public static void main(String[] args) throws Exception {

//		List<Integer> ans = cellCompete(new int[]{1, 1, 1, 0, 1, 1, 1, 1} ,0);
//		for (Integer a : ans) {
//			System.out.print(a + " ");
//		}

		System.out.println(generalizedGCD(5, new int[]{2,4,6,8,10}));

		bw.flush();
		bw.close();
		br.close();
	}

	public static int generalizedGCD(int num, int[] arr) {
		int gcd = arr[0];
		for (int i=1 ; i<num ; i++) {
			gcd = getGcd(gcd, arr[i]);
		}
		return gcd;
	}

	public static int getGcd(int x, int y) {
		if (y == 0) {
			return x;
		} else {
			return getGcd(y, x%y);
		}
	}


	public static List<Integer> cellCompete(int[] states, int days) {
		if (days == 0) {
			return Arrays.stream(states).boxed().collect(Collectors.toList());
		}
		int len = states.length;
		int[] next = new int[len];
		for (int i=0 ; i<len ; i++) {
			int lState = i == 0 ? 0 : states[i-1];
			int rState = i == len - 1 ? 0 : states[i+1];
			next[i] = lState == rState ? 0 : 1;
		}
		return cellCompete(next, days - 1);
	}


//
//	private static void doDFS(int v, int n) {
//		visit[v] = true;
//		System.out.print(v + " ");
//		for (int i=1 ; i<=n ; i++) {
//			if (board[v][i] == 1 && !visit[i]) {
//				doDFS(i, n);
//			}
//		}
//	}
//
//	private static void doBFS(int v, int n) {
//		Queue<Integer> q = new LinkedList<>();
//		q.add(v);
//		visit[v] = true;
//		while (!q.isEmpty()) {
//			int tmp = q.poll();
//			System.out.print(tmp + " ");
//			for (int i=1 ; i<=n ; i++) {
//				if (board[tmp][i] == 1 && !visit[i]) {
//					q.add(i);
//					visit[i] = true;
//				}
//			}
//		}
//	}
}
