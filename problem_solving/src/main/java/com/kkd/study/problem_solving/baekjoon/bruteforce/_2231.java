package com.kkd.study.problem_solving.baekjoon.bruteforce;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/2231
 */
public class _2231 {

	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static void start() throws IOException {
		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		for (int i = n/2 ; i<n ; i++) {
			int temp = i;
			for (char c : String.valueOf(i).toCharArray()) {
				temp += c - '0';
			}
			if (temp == n) {
				ans = i;
				break;
			}
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
