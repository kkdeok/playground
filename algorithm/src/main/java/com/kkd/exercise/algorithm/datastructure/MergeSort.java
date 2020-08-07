package com.kkd.exercise.algorithm.datastructure;

public class MergeSort {
	public static int[] tmp = null;
	
	public static void main(String[] args) {
		int[] arr = new int[]{1, 8, 6, 2, 5, 4, 3, 7};
		tmp = new int[arr.length];
		mergeSort(arr, 0, arr.length-1);
		
		for (int v : arr) {
			System.out.print(v + " ");
		}
	}
	
	private static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}
	
	private static void merge(int[] arr, int left, int mid, int right) {
		int idx = left;
		int l = left;
		int r = mid + 1;
		
		while (l<=mid && r<=right) {
			if (arr[l] < arr[r]) {
				tmp[idx++] = arr[l++];
			} else {
				tmp[idx++] = arr[r++];
			}
		}
		
		while (l<=mid) {
			tmp[idx++] = arr[l++];
		}
		while (r<=right) {
			tmp[idx++] = arr[r++];
		}
		
		for (int i=left ; i<=right ; i++) {
			arr[i] = tmp[i];
		}
	}
}
