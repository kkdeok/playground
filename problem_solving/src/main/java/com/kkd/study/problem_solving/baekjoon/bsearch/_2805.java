package com.kkd.study.problem_solving.baekjoon.bsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2805
 */
public class _2805 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		
		int[] trees = new int[n];
		line = br.readLine().split(" ");
		for (int i=0 ; i<n ; i++) {
			trees[i] = Integer.parseInt(line[i]);
		}
		
		long l = 1; 
		long r = Arrays.stream(trees).max().getAsInt();
		long ans = 0;
		while (l<=r) {
			long mid = (l+r)/2; // height
			long total = 0;
			for (long tree : trees) {
				long temp = tree - mid;
				if (temp > 0) {
					total += temp;
				}
			}
			if (total >= m) {
				ans = Math.max(ans, mid);
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		System.out.println(ans);
	}
}
