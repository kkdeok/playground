package com.kkd.study.algorithm.datastructure;

import java.util.Arrays;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] arr = new int[]{1, 8, 6, 2, 5, 4, 3, 7};
		
		for (int i=0 ; i<arr.length ; i++) {
			for (int j=1 ; j<arr.length - i ; j++) {
				if (arr[j-1] > arr[j]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
}
