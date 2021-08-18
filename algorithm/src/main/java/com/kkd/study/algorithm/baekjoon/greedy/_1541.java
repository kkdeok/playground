package com.kkd.study.algorithm.baekjoon.greedy;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/1541
 */
public class _1541 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main (String[] args) throws Exception {
		char[] line = br.readLine().toCharArray();
		int n = line.length;

		int[] nums = new int[50];
		char[] ops = new char[50];
		int k = 0;
		for (int i=0 ; i<n ; i++) {
			int j = i;
			while (i<n && line[i] != '-' && line[i] != '+') {
				i++;
			}
			int num = Integer.parseInt(String.copyValueOf(line, j, i-j));
			nums[k] = num;
			if (i<n) {
				ops[++k] = line[i];
			}
		}
		int ret = nums[0];
		for (int i=1 ; i<=k ; i++) {
			if (ops[i] == '-') {
				int tmp = nums[i];
				while (i+1<=k && ops[i+1] == '+') {
					tmp += nums[i+1];
					i++;
				}
				ret -= tmp;
			} else {
				ret += nums[i];
			}
		}
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
