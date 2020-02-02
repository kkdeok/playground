package com.doubleknd26.exercise.algorithm.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/11399
 */
public class _11399 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static List<Integer> p = new ArrayList<>();

	public static void main (String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		String[] tmp = br.readLine().split(" ");
		for (int i=0 ; i<n ; i++) {
			p.add(Integer.parseInt(tmp[i]));
		}
		quickSort(0, n-1);
		for (int i=1 ; i<n ; i++) {
			p.set(i, p.get(i-1) + p.get(i));
		}
		int ret = 0;
		for (int i=0 ; i<n ; i++) {
			ret += p.get(i);
		}
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void quickSort(int left, int right) {
		if (left < right) {
			int pivot = partition(left, right);
			quickSort(left, pivot - 1);
			quickSort(pivot + 1, right);
		}
	}

	private static int partition(int left, int right) {
		int l = right, r = right;
		int pNum = p.get(left);

		for (; l > left ; l--) {
			if (p.get(l) >= pNum) {
				Collections.swap(p, l, r);
				r--;
			}
		}
		Collections.swap(p, left, r);
		return r;
	}
}
