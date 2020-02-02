package com.doubleknd26.exercise.algorithm.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/2352
 */
public class _2352 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static final int SIZE = 40001;
	private static int[] s = new int[SIZE], e = new int[SIZE];
	private static int[] ts = new int[SIZE], te = new int[SIZE];

	public static void main (String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		for (int i=0 ; i<n ; i++) {
			int l = i + 1;
			int r = Integer.parseInt(input[i]);
			if (l < r) {
				s[i+1] = l;
				e[i+1] = r;
			} else {
				s[i+1] = r;
				e[i+1] = l;
			}
		}
		mergeSort(1, n);

		for (int i=1 ; i<=n ; i++) {
			bw.write(s[i] + " " + e[i] + "\n");
		}

		int ret = 0;
		int available = 0;
		for (int i=1 ; i<=n ; i++) {
			if (available <= s[i]) {
				ret++;
				available = e[i];
			}
		}
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void mergeSort(int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid+1, right);
			merge(left, mid, right);
		}
	}

	private static void merge(int left, int mid, int right) {
		int l = left, r = mid+1, idx = left;

		while (l <= mid && r <= right) {
			if (e[l] < e[r]) {
				ts[idx] = s[l];
				te[idx++] = e[l++];
			} else if (e[r] < e[l]) {
				ts[idx] = s[r];
				te[idx++] = e[r++];
			} else if (s[l] < s[r]) {
				ts[idx] = s[l];
				te[idx++] = e[l++];
			} else {
				ts[idx] = s[r];
				te[idx++] = e[r++];
			}
		}

		while (l <= mid) {
			ts[idx] = s[l];
			te[idx++] = e[l++];
		}

		while (r <= right) {
			ts[idx] = s[r];
			te[idx++] = e[r++];
		}

		for (int i=left ; i<=right; i++) {
			s[i] = ts[i];
			e[i] = te[i];
		}
	}
}
