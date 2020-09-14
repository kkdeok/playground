package com.kkd.study.problem_solving.baekjoon.backtracking;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://www.acmicpc.net/problem/2580
 */
public class _2580 {
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 9;
	private static int[][] BOARD = new int[SIZE+1][SIZE+1];
	private static List<Integer> X = new ArrayList<>();
	private static List<Integer> Y = new ArrayList<>();

	private static int[] check;
	// 정답을 찾으면 바로 빠져나와도 되기 때문에 break point 처럼 사용하기 위함.
	private static boolean FIND_ANSWER = false;

	private static void start() throws IOException {
		for (int x=1 ; x<=SIZE ; x++) {
			String[] line = br.readLine().split(" ");
			for (int y=1 ; y<=SIZE ; y++) {
				BOARD[x][y] = Integer.parseInt(line[y-1]);
				if (BOARD[x][y] == 0) {
					X.add(x);
					Y.add(y);
				}
			}
		}
		int zcnt = X.size();
		check = new int[zcnt];
		dfs(0, zcnt);
		print();
	}

	private static void dfs(int idx, int cnt) {
		if (idx == cnt) {
			FIND_ANSWER = true;
			return;
		}
		// Because input is always given by considering that answer always
		// exists, we can check first than find the possible answer.
		check[idx] = 1;

		int x = X.get(idx);
		int y = Y.get(idx);
		Set<Integer> nums = getPossibleNums(x, y);

		if (!nums.isEmpty()) {
			for (Integer num : nums) {
				BOARD[x][y] = num;
				dfs(idx + 1, cnt);
				if (FIND_ANSWER) {
					return;
				}
			}
		}
		BOARD[x][y] = 0;
		check[idx] = 0;
	}

	/**
	 * 해당 빈 칸에 들어갈 수 있는 가능한 모든 수를 구해서 반환한다.
	 * @param x
	 * @param y
	 * @return
	 */
	private static Set<Integer> getPossibleNums(int x, int y) {
		Set<Integer> nums = new HashSet<>();

		// check horizontal
		int[] numCount = new int[SIZE + 1];
		for (int i = 1; i <= SIZE; i++) {
			numCount[BOARD[x][i]]++;
		}
		// check vertical
		for (int i = 1; i <= SIZE; i++) {
			numCount[BOARD[i][y]]++;
		}
		// check small square
		int sx = getStartIndex(x);
		int sy = getStartIndex(y);
		for (int i = sx; i < sx + 3; i++) {
			for (int j = sy; j < sy + 3; j++) {
				numCount[BOARD[i][j]]++;
			}
		}
		// add possible nums
		for (int i = 1; i <= SIZE; i++) {
			if (numCount[i] == 0) {
				nums.add(i);
			}
		}
		return nums;
	}

	private static int getStartIndex(int idx) {
		return 6 < idx ? 7 : 3 < idx ? 4 : 1;
	}

	private static void print() throws IOException {
		for (int x=1 ; x<=SIZE ; x++) {
			for (int y=1 ; y<=SIZE ; y++) {
				bw.write(BOARD[x][y] + " ");
			}
			bw.write("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}