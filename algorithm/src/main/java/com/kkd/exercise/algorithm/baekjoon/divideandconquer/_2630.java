package com.kkd.exercise.algorithm.baekjoon.divideandconquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.acmicpc.net/problem/2630
 */
public class _2630 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int white = 0;
	private static int blue = 0;

	public static void main (String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		List<Integer>[] board = new List[n];

		for (int i=0 ; i<n ; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(
					Integer::parseInt).boxed().collect(Collectors.toList());
		}

		find(0, n, 0, n, board);
		bw.write(white + "\n");
		bw.write(blue + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void find(int sx, int ex, int sy, int ey, List<Integer>[] board) {
		int color = checkColor(sx, ex, sy, ey, board);
		if (color == 0) {
			white++;
			return;
		}
		if (color == 1) {
			blue++;
			return;
		}

		int tmpX = sx + ((ex - sx)  / 2);
		int tmpY = sy + ((ey - sy)  / 2);
		find(sx, tmpX, sy, tmpY, board);
		find(sx, tmpX, tmpY, ey, board);
		find(tmpX, ex, sy, tmpY, board);
		find(tmpX, ex, tmpY, ey, board);
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
