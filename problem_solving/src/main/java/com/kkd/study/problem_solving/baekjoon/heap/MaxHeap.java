package com.kkd.study.problem_solving.baekjoon.heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/11279
 */
public class MaxHeap {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		PriorityQueue q = new PriorityQueue();
		int n = Integer.parseInt(line[0]);
		while (n-- > 0) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				System.out.println(q.pop());
			} else {
				q.push(input);
			}
		}
	}
	
	static class PriorityQueue {
		private int size = 100001;
		private int[] heap = new int[size];
		private int idx = 0;
		
		public void push(int val) {
			heap[++idx] = val;
			for (int i = idx; i > 1; i = i / 2) {
				if (heap[i] > heap[i/2]) {
					swap(i, i/2);
				} else {
					break;
				}
			}
		} 
		
		public int pop() {
			if (isEmpty()) {
				return 0;
			}
			
			int ret = heap[1];
			heap[1] = heap[idx];
			heap[idx] = Integer.MIN_VALUE;
			idx--;
			
			for (int i=1 ; i*2<=idx ;) {
				if (heap[i] > heap[i*2] && heap[i] > heap[i*2+1]) {
					break;
				} else if (heap[i*2] > heap[i*2+1]) {
					swap(i, i*2);
					i = i*2;
				} else {
					swap(i, i*2+1);
					i = i*2+1;
				}
			}
			
			return ret;
		}
		
		private void swap(int idx1, int idx2) {
			int temp  = heap[idx1];
			heap[idx1] = heap[idx2];
			heap[idx2] = temp;
		} 
		
		private boolean isEmpty() {
			return idx == 0;
		}
	}
}
