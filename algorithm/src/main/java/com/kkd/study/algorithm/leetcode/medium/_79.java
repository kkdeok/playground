package com.kkd.study.algorithm.leetcode.medium;

/**
 * https://leetcode.com/problems/word-search/submissions/
 */
public class _79 {
	private static final int[] dx = new int[]{-1, 1, 0, 0};
	private static final int[] dy = new int[]{0, 0, -1, 1};

	private boolean[][] visit;
	private int m;
	private int n;

	public boolean exist(char[][] board, String word) {
		char first = word.charAt(0);
		m = board.length;
		n = board[0].length;
		visit = new boolean[m][n];

		for (int i=0 ; i<m ; i++) {
			for (int j=0 ; j<n ; j++) {
				if (board[i][j] == first) {
					if (find(0, i, j, board, word)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean find(int start, int x, int y, char[][] board, String word) {
		visit[x][y] = true;

		if (word.length()-1 <= start) {
			return true;
		}
		if (start+1 >= word.length()) {
			return false;
		}

		for (int i=0 ; i<4 ; i++) {
			int tempX = x + dx[i];
			int tempY = y + dy[i];

			// check outbound & visit
			if (tempX<0 || tempX>=m || tempY<0 || tempY>=n || visit[tempX][tempY]) {
				continue;
			}

			if (word.charAt(start+1) == board[tempX][tempY]) {
				if (find(start+1, tempX, tempY, board, word)) {
					return true;
				}
			}
		}

		visit[x][y] = false;
		return false;
	}

	public static void main(String[] args) {
		_79 program = new _79();
		program.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "SEE");
	}
}
