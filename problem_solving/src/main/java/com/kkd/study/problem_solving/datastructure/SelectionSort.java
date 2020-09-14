package com.kkd.study.problem_solving.datastructure;

import java.util.Arrays;

/**
 * stable, in-place sorting (추가 공간이 필요없음)
 */
public class SelectionSort {
	
	public static void main(String[] args) {
		int[] arr = new int[]{1, 8, 6, 2, 5, 4, 3, 7};
		
		for (int i=0 ; i<arr.length ; i++) {
			int minIdx = i;
			for (int j=i+1 ; j<arr.length ; j++) {
				if (arr[minIdx] > arr[j]) {
					minIdx = j;
				}
			}
			int temp = arr[minIdx];
			arr[minIdx] = arr[i];
			arr[i] = temp;
		}

		System.out.println(Arrays.toString(arr));
	}
}
