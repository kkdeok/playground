package com.kkd.study.problem_solving.leetcode;

public class DivisorGame {
	
	public static int player = 1;
	
	public static void main(String[] args) {
		System.out.println(divisorGame(4));
	}

	public static boolean divisorGame(int N) {
		if (N == 1) {
			 return player != 1;
		}
		for (int x=N-1 ; x>0 ; x--) {
			if (N % x == 0) {
				player = 3 - player;
				return divisorGame(N - x);
			}
		}
		return player != 1;
	}
}
