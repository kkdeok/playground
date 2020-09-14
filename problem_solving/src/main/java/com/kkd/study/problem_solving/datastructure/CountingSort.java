package com.kkd.study.problem_solving.datastructure;

import java.util.Arrays;

public class CountingSort {
	
	private static final int MAX_SIZE = 10;

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 3, 2 };
		int n = arr.length;
		int[] count = new int[MAX_SIZE];
		int[] tmp = new int[arr.length + 1];

		Arrays.fill(count, 0);
		for (int i=0 ; i<n ; i++) {
			count[arr[i]]++; // 각 숫자를 index로 해서 count 해준다.
		}
		for (int i=1 ; i<MAX_SIZE ; i++) {
			count[i] += count[i-1]; // 누적값을 구한다.
		}
		
		
		for (int i=n-1 ; i>=0 ; i--) { // 뒤에서부터 처리하는 이유는 stable 속성 유지 목적.
			// 누적값을 index로하여 tmp에 값을 넣어준다.
			tmp[ count[arr[i]] ] = arr[i];
			count[arr[i]]--;
		}
		for (int i=1 ; i<=n ; i++) {
			arr[i-1] = tmp[i];
		}

		System.out.println(Arrays.toString(arr));
	}
}
