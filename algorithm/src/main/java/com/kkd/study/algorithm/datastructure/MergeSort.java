package com.kkd.study.algorithm.datastructure;

import java.util.Arrays;

public class MergeSort {
	public static int[] tmp = null;
	
	public static void main(String[] args) {
		int[] arr = new int[]{1, 8, 6, 2, 5, 4, 3, 7};
		tmp = new int[arr.length];
		mergeSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
	
	
	private static void mergeSort(int[] arr, int l, int r) {
		if (l<r) {
			int mid = (l + r) / 2;
			mergeSort(arr, l, mid);
			mergeSort(arr, mid+1, r);
			merge(arr, l, mid, r);
		}
	}
	
	private static void merge(int[] arr, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;
		
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
