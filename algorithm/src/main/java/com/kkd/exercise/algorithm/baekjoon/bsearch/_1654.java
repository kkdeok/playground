package com.kkd.exercise.algorithm.baekjoon.bsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1654
 */
public class _1654 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int k = Integer.parseInt(line[0]);
		int n = Integer.parseInt(line[1]);
		
		int[] arr = new int[k];
		int max = 0;
		for (int i=0 ; i<k ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		
		long l = 1, r = max;
		long ans = 0;
		while (l<=r) {
			long mid = (l + r) / 2;
			if (isPossibleLength(arr, n, mid)) {
				// 우리는 정확히 n 개의 랜선을 만들 때의 최대 길이를 구해야 한다. 하지만 isPossibleLength 는
				// n개보다 더 많이 만들 수 있는 길이도 true를 반환하여 이 조건문으로 들어올 것이다. 하지만,
				// 그런 길이는 반드시 n 개를 만들 수 있는 길이보다 짧을 것이다.
				ans = Math.max(ans, mid);
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		System.out.println(ans);
	}
	
	private static boolean isPossibleLength(int[] arr, int n, long len) {
		int count = 0;
		for (int a : arr) {
			count += a / len;
		}
		return count >= n;
	}
}
