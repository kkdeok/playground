package com.kkd.exercise.algorithm.datastructure;

public class SelectionSort {
	
	public static void main(String[] args) {
		int[] arr = new int[]{1, 8, 6, 2, 5, 4, 3, 7};
		
		for (int i=0 ; i<arr.length ; i++) {
			int idx = i;
			for (int j=i+1 ; j<arr.length ; j++) {
				if (arr[idx] > arr[j]) {
					idx = j;
				}
			}
			int temp = arr[idx];
			arr[idx] = arr[i];
			arr[i] = temp;
		}
		
		for (int v : arr) {
			System.out.print(v + " ");
		}
	}
}
