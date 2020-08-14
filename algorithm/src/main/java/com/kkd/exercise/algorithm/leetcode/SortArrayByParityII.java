package com.kkd.exercise.algorithm.leetcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/sort-array-by-parity-ii/
 */
public class SortArrayByParityII {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		SortArrayByParityII main = new SortArrayByParityII();
		main.start();
		bw.flush();
		bw.close();
		br.close();
	}

	private void start() throws Exception {
		int[] a = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
		sortArrayByParityII(a);
		for (int i=0 ; i<a.length ; i++) {
			bw.write(a[i] + "\n");
		}
	}

	public int[] sortArrayByParityII(int[] A) {
		for (int i=0 ; i<A.length ; i++) {
			int idx = -1;
			if (i % 2 == 0 && A[i] % 2 != 0) {
				idx = i;
			}
			if (i % 2 == 1 && A[i] % 2 != 1) {
				idx = i;
			}
			if (idx != -1) {
				for (int k=idx+1 ; k<A.length ; k+=2) {
					if (k % 2 != A[k] % 2) {
						int tmp = A[idx];
						A[idx] = A[k];
						A[k] = tmp;
						break;
					}
				}
			}
		}
		return A;
	}
}
