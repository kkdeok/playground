package com.kkd.exercise.algorithm.baekjoon.heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1927
 * NOTE: heap sort has almost same performance with PriorityQueue.
 */
public class MinHeap {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int size = 100001;
	private static int[] heap = new int[size];
	private static int idx = 0;
	
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		while (n-- > 0) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				int ret = isEmpty() ? 0 : pop();
				System.out.println(ret);
			} else {
				push(input);
			}
		}
	}
	
	private static void push(int val) {
		heap[++idx] = val;
		for (int i=idx ; i>1 ; i/=2) {
			if (heap[i] < heap[i/2]) {
				swap(i, i/2);
			} else {
				break;
			}
		}
	}
	
	private static int pop() {
		int ret = heap[1]; // root
		heap[1] = heap[idx];
		heap[idx] = Integer.MAX_VALUE;
		idx--;
		
		for (int i=1 ; i*2<=idx ;) {
			if ( heap[i] < heap[i*2] && heap[i] < heap[i*2+1]) {
				break;
			} else if (heap[i*2] < heap[i*2+1]) {
				swap(i*2, i);
				i = i*2;
			} else {
				swap(i*2+1, i);
				i = i*2+1;
			}
		}
		return ret;
	}
	
	private static boolean isEmpty() {
		return idx == 0;
	}
	
	private static void swap(int idx1, int idx2) {
		int temp = heap[idx1];
		heap[idx1] = heap[idx2];
		heap[idx2] = temp;
	}
}
