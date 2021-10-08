package com.kkd.study.algorithm.codingtest.baemin._20211002.no2;

public class _2 {
	public static void main(String[] args) {
		_2 program = new _2();
		program.solution(3);
	}

	// 벌집의 한 변을 구성하는 육각형의 개수를 나타내는 정수 n이 매개변수로 주어진다.
	// 한 변의 길이가 n인 육각형 벌집을 맨 위부터 시계방향으로 돌아가면서 달팽이처럼 채운뒤,
	// 위에서부터 내려오면서 좌에서 우로 벌집을 훑었을 때 나오는 숫자들을 차례대로 배열에 담아 return 하라.
	// 1 <= n <= 500
	public int[] solution(int n) {
		int[][] cycles = new int[n][6*(n-1)];
		int num = 1;
		int idx = 0;
		for (int i=n ; i>=2 ; i--) {
			int cnt = 6 * (i-1);

			for (int j=0 ; j<cnt ; j++) {
				cycles[idx][j] = num++;
			}
			idx++;
		}
		cycles[idx][0] = num;

		for (int i=0 ; i<cycles.length ; i++) {
			for (int j=0 ; j<cycles[i].length ; j++) {
				System.out.print(cycles[i][j] + " ");
			}
			System.out.println();
		}
		return null;
	}
}
