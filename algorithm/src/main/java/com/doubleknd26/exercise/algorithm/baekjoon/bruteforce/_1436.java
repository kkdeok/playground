package com.doubleknd26.exercise.algorithm.baekjoon.bruteforce;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/1436
 */
public class _1436 {
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static void start() throws IOException {
		int n = Integer.parseInt(br.readLine());
		int o = 0;
		int num = 666;
		int ans = 0;
		String str = "666";
		while (o != n) {
			if (Integer.toString(num).contains(str)) {
				o++;
				ans = num;
			}
			num++;
		}
		bw.write(ans + "\n");
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
