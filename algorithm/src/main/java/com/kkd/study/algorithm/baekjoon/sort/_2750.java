package com.kkd.study.algorithm.baekjoon.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2750
 */
public class _2750 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int MAX_SIZE = 1001;

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int ncnt = 0;
		for (int i=0 ; i<n ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] < 0) {
				ncnt++;
			}
		}

		int a=0, b=0;
		int[] narr = new int[ncnt];
		int[] parr = new int[n - ncnt];
		for (int i=0 ; i<n ; i++) {
			if (arr[i] <0) {
				narr[a++] = arr[i] * -1;
			} else {
				parr[b++] = arr[i];
			}
		}

		countingSort(narr, ncnt);
		countingSort(parr, n - ncnt);

		for (int i=ncnt-1 ; i>=0 ; i--) {
			bw.write(narr[i] * -1 + "\n");
		}
		for (int i=0 ; i<n - ncnt ; i++) {
			bw.write(parr[i] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void countingSort(int[] arr, int n) {
		int[] count = new int[MAX_SIZE];
		int[] tmp = new int[arr.length + 1];

		Arrays.fill(count, 0);
		for (int i=0 ; i<n ; i++) {
			count[arr[i]]++;
		}
		for (int i=1 ; i<MAX_SIZE ; i++) {
			count[i] += count[i-1];
		}

		for (int i=n-1 ; i>=0 ; i--) {
			tmp[ count[arr[i]] ] = arr[i];
			count[arr[i]]--;
		}
		for (int i=1 ; i<=n ; i++) {
			arr[i-1] = tmp[i];
		}
	}
}
