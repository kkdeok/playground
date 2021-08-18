package com.kkd.study.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _12851 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] visit = new int[100001];
	private static int n, k;

	private static int min = Integer.MAX_VALUE;
	private static int cnt = 0;

	public static void main(String[] args) throws Exception {
		Arrays.fill(visit, -1);
		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		k = Integer.parseInt(line[1]);

		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> t = new LinkedList<>();

		q.add(n); t.add(0);
		visit[n] = 0;

		while (!q.isEmpty()) {
			int tempq = q.poll();
			int tempt = t.poll();
			
			if (tempq == k) {
				checkAnswer(tempt);
				continue;
			}

			List<Integer> list = Arrays.asList(tempq - 1, tempq + 1, tempq * 2);
			for (int a : list) {
				if (a >= 0 && a <= 100000) {
					if (a == k || visit[a] == -1 || visit[a] == tempt + 1) {
						q.add(a);
						t.add(tempt + 1);
						visit[a] = tempt + 1;
					}
				}
			}
		}

		System.out.println(min);
		System.out.println(cnt);
	}

	private static void checkAnswer(int temp) {
		if (min > temp) {
			min = temp;
			cnt = 1;
		} else if (min == temp) {
			cnt++;
		}
	}



}
