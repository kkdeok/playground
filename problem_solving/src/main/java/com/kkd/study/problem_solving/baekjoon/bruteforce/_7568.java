package com.kkd.study.problem_solving.baekjoon.bruteforce;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * https://www.acmicpc.net/problem/7568
 */
public class _7568 {

	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static void start() throws IOException {
		int n = Integer.parseInt(br.readLine());
		Tuple[] tuples = new Tuple[n];
		for (int i=0 ; i<n ; i++) {
			String[] input = br.readLine().split(" ");
			int weight = Integer.parseInt(input[0]);
			int height = Integer.parseInt(input[1]);
			tuples[i] = new Tuple(weight, height);
		}

		int[] rank = new int[n];
		for (int i=0 ; i<n ; i++) {
			rank[i]++;
			for (int j=0 ; j<n ; j++) {
				if (tuples[i].compareTo(tuples[j]) < 0) {
					rank[i]++;
				}
			}
		}
		String ans = Arrays.stream(rank).boxed().map(String::valueOf).collect(Collectors.joining(" "));
		bw.write(ans + "\n");
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Tuple implements Comparable<Tuple> {
		int weight;
		int height;

		public Tuple(int weight, int height) {
			this.weight = weight;
			this.height = height;
		}

		@Override
		public int compareTo(Tuple that) {
			if (this.height > that.height && this.weight > that.weight) {
				return 1;
			} else if (this.height < that.height && this.weight < that.weight) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
