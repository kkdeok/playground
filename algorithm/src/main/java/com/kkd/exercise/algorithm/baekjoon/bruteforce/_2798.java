package com.kkd.exercise.algorithm.baekjoon.bruteforce;

import java.io.*;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2798
 */
public class _2798 {
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static void start() throws IOException {
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int ans = 0;
		for (int i=0 ; i<n ; i++) {
			for (int j=i+1 ; j<n ; j++) {
				for (int k=j+1 ; k<n ; k++) {
					int temp = arr[i] + arr[j] + arr[k];
					if (temp <= m && ans < temp) {
						ans = temp;
					}
				}
			}
		}
		bw.write(ans + "\n");
	}

	public static void main(String[] args) throws IOException {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}
