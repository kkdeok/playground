package com.kkd.study.algorithm.baekjoon.heap;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.acmicpc.net/problem/11286
 */
public class _11286 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>(
			new Comparator<Pair<Integer, Integer>>() {
				@Override
				public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
					int comp = o1.getValue().compareTo(o2.getValue());
					return comp != 0 ? comp : o1.getKey().compareTo(o2.getKey());
				}
			});
		
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		
		while (n-- > 0) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				int ret = q.isEmpty() ? 0 : q.poll().getKey();
				System.out.println(ret);
			} else {
				Pair<Integer, Integer> pair = new Pair<>(input, Math.abs(input));
				q.add(pair);
			}
		}
	}
}
