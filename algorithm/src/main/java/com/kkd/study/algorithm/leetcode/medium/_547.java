package com.kkd.study.algorithm.leetcode.medium;

/**
 * https://leetcode.com/problems/number-of-provinces/
 */
public class _547 {
	public int findCircleNum(int[][] isConnected) {
		int[] visit = new int[isConnected.length];

		int result = 0;
		for (int i=0 ; i<isConnected.length ; i++) {
			if (visit[i] == 0) {
				search(isConnected, visit, i);
				result++;
			}
		}

		return result;
	}

	private void search(int[][] isConnected, int[] visit, int idx) {
		visit[idx] = 1;

		for (int i=0 ; i<isConnected.length ; i++) {
			if (idx == i) {
				continue;
			}
			if (isConnected[idx][i] == 1 && visit[i] == 0) {
				search(isConnected, visit, i);
			}
		}
	}
}
