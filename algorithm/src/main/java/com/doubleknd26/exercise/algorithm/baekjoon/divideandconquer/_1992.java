package com.doubleknd26.exercise.algorithm.baekjoon.divideandconquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.acmicpc.net/problem/1992
 */
public class _1992 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main (String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		List<Integer>[] board = new List[n];

		for (int i=0 ; i<n ; i++) {
			char[] line = br.readLine().toCharArray();
			board[i] = new ArrayList<>();
			for (int j=0 ; j<line.length ; j++) {
				board[i].add(line[j] - '0');
			}
		}

		String ans = find(0, n, 0, n, board);
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static String find(int sx, int ex, int sy, int ey, List<Integer>[] board) {
		int color = checkColor(sx, ex, sy, ey, board);
		if (color == 0 || color == 1) {
			return String.valueOf(color);
		}

		StringBuilder sb = new StringBuilder();
		int tmpX = sx + ((ex - sx)  / 2);
		int tmpY = sy + ((ey - sy)  / 2);
		sb.append("(");
		sb.append(find(sx, tmpX, sy, tmpY, board));
		sb.append(find(sx, tmpX, tmpY, ey, board));
		sb.append(find(tmpX, ex, sy, tmpY, board));
		sb.append(find(tmpX, ex, tmpY, ey, board));
		sb.append(")");
		return sb.toString();
	}

	private static int checkColor(int sx, int ex, int sy, int ey, List<Integer>[] board) {
		int color = board[sx].get(sy);
		for (int i=sx ; i<ex ; i++) {
			for (int j=sy ; j<ey ; j++) {
				if (color != board[i].get(j)) {
					return -1;
				}
			}
		}
		return color;
	}
}
