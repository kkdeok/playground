package com.doubleknd26.exercise.algorithm.baekjoon.bruteforce;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/1018
 */
public class _1018 {
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int SIZE = 8;
	private static final char BLACK = 'B';
	private static final char WHITE = 'W';

	private static char[][][] CHESS_BOARD = new char[2][SIZE][SIZE];

	private static void prepareChessBoard() {
		for (int n=0 ; n<2 ; n++) {
			char[][] chessBoard = CHESS_BOARD[n];
			char color = n % 2 == 0 ? BLACK : WHITE;
			for (int i=0 ; i<SIZE ; i++) {
				for (int j=0 ; j<SIZE ; j++) {
					chessBoard[i][j] = color;
					if (j + 1 != SIZE) {
						color = color == BLACK ? WHITE : BLACK;
					}
				}
			}
		}
	}

	private static void start() throws IOException {
		prepareChessBoard();

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		char[][] board = new char[n][m];
		for (int i=0 ; i<n ; i++) {
			board[i] = br.readLine().toCharArray();
		}

		int ans = Integer.MAX_VALUE;
		for (int x = 0; x <= n - SIZE; x++) {
			for (int y = 0; y <= m - SIZE; y++) {

				// chess board total count = 2
				for (int c = 0; c < 2; c++) {
					int cnt = 0;
					for (int i = 0; i < SIZE; i++) {
						for (int j = 0; j < SIZE; j++) {
							if (CHESS_BOARD[c][i][j] != board[x + i][y + j]) {
								cnt++;
							}
						}
					}
					ans = Math.min(ans, cnt);
				}
			}
		}
		bw.write(ans + "\n");
	}

	public static void main(String[] args) throws Exception {
		start();
		bw.flush();
		bw.close();
		br.close();
	}
}