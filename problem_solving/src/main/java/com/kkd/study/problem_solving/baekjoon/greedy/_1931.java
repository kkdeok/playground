package com.kkd.study.problem_solving.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1931
 */
public class _1931 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int[] sTimes = new int[100000];
	private static int[] eTimes = new int[100000];
	private static int[] tmpS = new int[100000];
	private static int[] tmpE = new int[100000];

	public static void main (String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());

		for (int i=0 ; i<n ; i++) {
			String[] input = br.readLine().split(" ");
			sTimes[i] = Integer.parseInt(input[0]);
			eTimes[i] = Integer.parseInt(input[1]);
		}
		mergeSort(0, n-1);
		int ret = 0;
		int availableTime = 0;
		for (int i=0 ; i<n ; i++) {
			if (availableTime <= sTimes[i]) {
				ret++;
				availableTime = eTimes[i];
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
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}
	}

	private static void merge(int left, int mid, int right) {
		int l = left, r = mid+1, idx = left;
		while (l<=mid && r<=right) {
			if (eTimes[l] < eTimes[r]) {
				tmpS[idx] = sTimes[l];
				tmpE[idx++] = eTimes[l++];
			} else if (eTimes[r] < eTimes[l]) {
				tmpS[idx] = sTimes[r];
				tmpE[idx++] = eTimes[r++];
			} else if (sTimes[l] < sTimes[r]) {
				// e.g) 1-3 다음 수업으로 5-5 or 3-5 ? 3-5 -> 5-5
				tmpS[idx] = sTimes[l];
				tmpE[idx++] = eTimes[l++];
			} else {
				tmpS[idx] = sTimes[r];
				tmpE[idx++] = eTimes[r++];
			}
		}
		while (l<=mid) {
			tmpS[idx] = sTimes[l];
			tmpE[idx++] = eTimes[l++];
		}
		while (r<=right) {
			tmpS[idx] = sTimes[r];
			tmpE[idx++] = eTimes[r++];
		}
		for (int i=left ; i<=right ; i++) {
			sTimes[i] = tmpS[i];
			eTimes[i] = tmpE[i];
		}
	}
}
