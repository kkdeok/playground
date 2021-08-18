package com.kkd.study.algorithm.baekjoon.divideandconquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1074
 */
public class _1074 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int r, c;
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		r = Integer.parseInt(line[1]);
		c = Integer.parseInt(line[2]);

		n = (int) Math.pow(2, n);
		long ans = divide (0, n, 0, n, 0);
		System.out.println(ans);
	}
	
	private static long divide(int sx, int ex, int sy, int ey, long cnt) {
		if (ex - sx == 2) {
			for (int x=sx ; x<ex ; x++) {
				for (int y=sy ; y<ey ; y++) {
					if (x == r && y == c) {
						return cnt;
					} else {
						cnt++;
					}
				}
			}
			return -1;
		}
		
		long k = (ex - sx) / 2 * (ey - sy) / 2;
		if (sx <= r && r < (sx + ex) / 2) {
			if (sy <= c && c < (sy + ey) / 2) {
				return divide(sx, (sx + ex) / 2, sy, (sy + ey) / 2, cnt); // 왼쪽 위
			} else { 
				return divide(sx, (sx + ex) / 2, (sy + ey) / 2, ey, cnt + k); // 오른쪽 위
			}
		} else {
			if (sy <= c && c < (sy + ey) / 2) {
				return divide((sx + ex) / 2, ex, sy, (sy + ey) / 2, cnt + k * 2); // 왼쪽 아래
			} else {
				return divide((sx + ex) / 2, ex, (sy + ey) / 2, ey, cnt + k * 3); // 오른쪽 위
			}
		}
	}
}
