package com.kkd.exercise.algorithm.datastructure;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] arr = new int[]{1, 8, 6, 2, 5, 4, 3, 7};
		
		for (int i=0 ; i<arr.length ; i++) {
			for (int j=1 ; j<arr.length - i ; j++) {
				if (arr[j] < arr[j-1]) {
					int temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
		}
		
		for (int v : arr) {
			System.out.print(v + " ");
		}
	}
}
