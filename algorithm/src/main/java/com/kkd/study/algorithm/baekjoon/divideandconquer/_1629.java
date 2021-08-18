package com.kkd.study.algorithm.baekjoon.divideandconquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1629
 */
public class _1629 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split(" ");
		int a = Integer.parseInt(input[0]);
		int b = Integer.parseInt(input[1]);
		int c = Integer.parseInt(input[2]);

		bw.write(find(a, b, c) + " \n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static long find(int a, int b, int c) {
		if (b == 1) {
			return a % c;
		}
		long sub = find(a, b/2, c);
		long tmp = sub * sub % c;
		return b % 2 == 1 ? tmp * a % c : tmp;
	}
}
