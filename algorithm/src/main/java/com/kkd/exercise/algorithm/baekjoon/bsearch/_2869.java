package com.kkd.exercise.algorithm.baekjoon.bsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2869
 */
public class _2869 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int a = Integer.parseInt(line[0]);
		int b = Integer.parseInt(line[1]);
		int v = Integer.parseInt(line[2]);
		
		int l = 1, r = v;
		int ans = Integer.MAX_VALUE;
		while(l<=r) {
			// 일 수
			int mid = (l + r) / 2;
			if ((mid-1) * (a-b) + a >= v) {
				ans = Math.min(ans, mid);
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		System.out.println(ans);
	}
}
