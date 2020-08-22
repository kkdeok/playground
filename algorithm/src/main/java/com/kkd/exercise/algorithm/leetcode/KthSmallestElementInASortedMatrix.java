package com.kkd.exercise.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class KthSmallestElementInASortedMatrix {

	public static void main(String[] args) {
		KthSmallestElementInASortedMatrix app = new KthSmallestElementInASortedMatrix();
		
		int[][] matrix = new int[][]{{1,5,9},{10,11,13},{12,13,15}};
		System.out.println(app.kthSmallest(matrix, 8));
	}
	
//	public int kthSmallest(int[][] matrix, int k) {
//		List<Integer> list = new ArrayList<>();
//		int n = matrix.length;
//
//		for (int i=0 ; i<n ; i++)
//			for (int j=0 ; j<n ; j++)
//				list.add(matrix[i][j]);
//
//		list.sort(Comparator.naturalOrder());
//		return list.get(k-1);
//	}


	public int kthSmallest(int[][] matrix, int k) {
		Queue<Integer> pq = new PriorityQueue<>((a,b) -> {
			return b - a;
	});

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++) {
				pq.add(matrix[i][j]);
				if (pq.size() > k)
					pq.poll();
			}

		return pq.poll();
	}
}
