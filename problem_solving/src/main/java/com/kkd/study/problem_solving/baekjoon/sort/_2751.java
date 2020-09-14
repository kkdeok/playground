package com.kkd.study.problem_solving.baekjoon.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2751
 */
public class _2751 {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i=0 ; i<n ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		quickSort(arr, 0, n-1);
		for (int i=0 ; i<n ; i++) {
			bw.write(arr[i] + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int pos = partition(arr, left, right);
			quickSort(arr, left, pos-1);
			quickSort(arr, pos+1, right);
		}
	}

	private static int partition(int[] arr, int left, int right) {
		int pivot = arr[left];
		int l = right, r = right;
		while (l > left) {
			if (arr[l] > pivot) {
				int tmp = arr[l];
				arr[l] = arr[r];
				arr[r] = tmp;
				r--;
			}
			l--;
		}
		arr[left] = arr[r];
		arr[r] = pivot;
		return r;
	}

//	public static void main(String[] args) throws Exception {
//		int n = Integer.parseInt(br.readLine());
//		int[] narr = new int[n];
//		int[] parr = new int[n];
//		int nidx = 0, pidx = 0;
//		for (int i=0 ; i<n ; i++) {
//			int value = Integer.parseInt(br.readLine());
//			if (value < 0) {
//				narr[nidx++] = value * -1;
//			} else {
//				parr[pidx++] = value;
//			}
//		}
//
//		radixSort(narr, nidx);
//		radixSort(parr, pidx);
//
//		for (int i=nidx-1 ; i>=0 ; i--) {
//			bw.write(narr[i] * -1 + "\n");
//		}
//		for (int i=0 ; i<pidx ; i++) {
//			bw.write(parr[i] + "\n");
//		}
//
//		bw.flush();
//		bw.close();
//		br.close();
//	}
//
//	private static void radixSort(int[] arr, int n) throws Exception {
//		// max position of decimal.
//		int p = getMaxPosition(arr, n);
//		for (int i=0 ; i<p ; i++) {
//			int[] cnt = new int[10];
//			int[] tmp = new int[n];
//
//			Arrays.fill(cnt, 0);
//
//			int power = (int) Math.pow(10, i);
//			for (int j=0 ; j<n ; j++) {
//				int digit = (arr[j] / power) % 10;
//				cnt[digit]++;
//			}
//
//			for (int j=1 ; j<10 ; j++) {
//				cnt[j] += cnt[j-1];
//			}
//
//			for (int j=n-1 ; j>=0 ; j--) {
//				int index = (arr[j] / power) % 10;
//				tmp[cnt[index]-1] = arr[j];
//				cnt[index]--;
//			}
//
//			for (int j=0 ; j<n ; j++) {
//				arr[j] = tmp[j];
//			}
//		}
//	}
//
//	private static int getMaxPosition(int[] arr, int n) {
//		int maxPos = 0;
//		for (int i=0 ; i<n ; i++) {
//			int tmp = arr[i];
//			int pos = 0;
//			while (tmp != 0) {
//				pos++;
//				tmp /= 10;
//			}
//			maxPos = Math.max(maxPos, pos);
//		}
//		return maxPos;
//	}
}
