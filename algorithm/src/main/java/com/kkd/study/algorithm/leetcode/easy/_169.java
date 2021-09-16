package com.kkd.study.algorithm.leetcode.easy;

public class _169 {
	public int majorityElement(int[] nums) {
		mergeSort(0, nums.length-1, nums);

		int current=0;
		int count=0;

		int majority=0;
		int mcount = -1;

		for (int i=0 ; i<nums.length ; i++) {
			if (i == 0) {
				current = nums[i];
				count++;
			} else {
				if (current == nums[i]) {
					count++;
				} else {
					if (mcount < count) {
						majority = current;
						mcount = count;
					}
					current = nums[i];
					count = 1;
				}
			}
		}
		if (mcount < count) {
			majority = current;
		}
		return majority;
	}

	private void mergeSort(int l, int r, int[] nums) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(l, m, nums);
			mergeSort(m+1 , r, nums);
			sort(l, m, r, nums);
		}
	}

	private void sort(int l, int m, int r, int[] nums) {
		int[] tmp = new int[r- l + 1];
		int idx = 0, lidx = l , ridx = m + 1;

		while (lidx <= m && ridx <= r) {
			if (nums[lidx] < nums[ridx]) tmp[idx++] = nums[lidx++];
			else tmp[idx++] = nums[ridx++];
		}

		while (lidx<=m) {
			tmp[idx++] = nums[lidx++];
		}

		while (ridx<=r) {
			tmp[idx++] = nums[ridx++];
		}

		for (int i=0 ; i<idx ; i++) {
			nums[l+i] = tmp[i];
		}
	}

	public static void main(String[] args) {
		_169 program = new _169();

		int[] arr = new int[]{2,2,3,3,3,3,2};
		System.out.println(program.majorityElement(arr));
	}
}
