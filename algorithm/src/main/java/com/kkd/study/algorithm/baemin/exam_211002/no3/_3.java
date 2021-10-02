package com.kkd.study.algorithm.baemin.exam_211002.no3;

import java.util.Arrays;

public class _3 {
	public static void main(String[] args) {
		_3 program = new _3();
		int result = program.solution(new int[][]{{2500, 3}, {700, 5}});
		System.out.println(result);
	}

	public int solution(int[][] money) {
		int answer = -1;
		Arrays.sort(money, (a, b) -> b[0] - a[0]);
		int first = 0, second = 0;

		int n = money.length;
		for (int i=0 ; i<n ; i++) {
			while (money[i][1] > 0) {
				if (first > second) {
					second += money[i][0];
				} else {
					first += money[i][0];
				}
				money[i][1]--;
			}
		}
		answer = Math.abs(first - second);
		return answer;
	}
}
