package com.kkd.exercise.algorithm.baekjoon.heap;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/2696
 */
public class _2696 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			int c = n;
			List<Integer> list = new ArrayList<>();
			do {
				String[] line = br.readLine().split(" ");
				for (int i=0 ; i<line.length ; i++) {
					list.add(Integer.parseInt(line[i]));
				}
				c = c - line.length; 
			} while(c > 0);
			
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
			List<Integer> ans = new ArrayList<>(); 
			
			for (int i=1 ; i<=n ; i++) {
				int val = list.get(i-1);
				if (minHeap.size() == maxHeap.size()) {
					maxHeap.add(val);
				} else {
					minHeap.add(val);
				}
				
				if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
					int max = maxHeap.poll();
					int min = minHeap.poll();
					maxHeap.add(min);
					minHeap.add(max);
				}
				
				if (i % 2 == 1) {
					ans.add(maxHeap.peek());
				}
			}

			System.out.println(ans.size());
			for (int i=1 ; i <= ans.size() ; i++) {
				System.out.print(ans.get(i-1) + " ");
				if (i == 10) {
					System.out.println();
				}
			}
			System.out.println();
		}
	}
}
