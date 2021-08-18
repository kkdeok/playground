package com.kkd.study.algorithm.datastructure;

public class InsertionSort {
	
	public static void main(String[] args) {
		int[] arr = new int[]{1, 8, 6, 2, 5, 4, 3, 7};
		
		for (int i=1 ; i<arr.length ; i++) {
			int temp = arr[i];
			int j=i-1;
			for (; j>=0 && temp < arr[j] ; j--) {
				arr[j+1] = arr[j];
			}
			arr[j+1] = temp;
		}
		
		for (int v : arr) {
			System.out.print(v + " ");
		}
	}
}
