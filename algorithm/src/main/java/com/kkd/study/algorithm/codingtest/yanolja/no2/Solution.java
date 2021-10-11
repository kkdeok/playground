package com.kkd.study.algorithm.codingtest.yanolja.no2;

import java.util.Arrays;

public class Solution {

	/**
	 *
	 * 문제 해석:
	 * N blocks -> 0 to n-1, arranged in a row
	 * 1개의 블럭에 여러 개구리들이 앉아있다.
	 * 점프를 해서 서로 최대한 멀리 떨어진 곳으로 이동하고 싶어한다.
	 * 블록 J와 K 간의 거리는 (J<=K) K - J + 1 이다.
	 * 개구리는 "점프 업" 만 할 수 있다. 즉, 개구리가 현재 있는 블록과 인접해 있고 높이가 같거나 더 높은 곳으로만 이동 가능하다.
	 *
	 * What is the longest distance that they can possibly create between each other,
	 * if they also chose to sit on the optimal starting block initially?
	 * 그들이 처음에 최적의 출발 블록에 앉기로 선택한 경우 서로 간에 만들 수 있는 가장 긴 거리는 얼마입니까?
	 *
	 * N is an integer within the range [2 .. 200,000]
	 * element of array blocks is an integer within the range [1 .. 1,000,000,000]
	 *
	 * @param blocks -> N개의 블록, 높이값을 가지고 있다.
	 * @return
	 */

	private static final int MAX_SIZE = 200001;

	public int solution(int[] blocks) {
		// write your code in Java SE 8
		int[][] memo = new int[MAX_SIZE][2];

		for (int i=0 ; i<MAX_SIZE ; i++) {
			Arrays.fill(memo[i], -1);
		}

		int result = 2;
		for (int i=0 ; i<blocks.length ; i++) {
			int leftBlockNum = findBlockNum(blocks, i, true, memo);
			int rightBlockNum = findBlockNum(blocks, i, false, memo);
			int distance = rightBlockNum - leftBlockNum + 1;
			if (result < distance) {
				result = distance;
			}
		}
		return result;
	}

	private int findBlockNum(int[] blocks, int currBlockNum, boolean isLeftDirection, int[][] memo) {
		int memoIndex = isLeftDirection ? 0 : 1;
		if (memo[currBlockNum][memoIndex] != -1) {
			return memo[currBlockNum][memoIndex];
		}

		int nextBlockNum = isLeftDirection ? currBlockNum - 1 : currBlockNum + 1;

		if (!isValidBlockNum(blocks, nextBlockNum)) {
			return memo[currBlockNum][memoIndex] = currBlockNum;
		}

		memo[currBlockNum][memoIndex] = isVisitable(blocks, currBlockNum, nextBlockNum)
			? findBlockNum(blocks, nextBlockNum, isLeftDirection, memo)
			: currBlockNum;

		return memo[currBlockNum][memoIndex];
	}

	private boolean isValidBlockNum(int[] blocks, int blockNum) {
		return blockNum >= 0 && blockNum < blocks.length;
	}

	private boolean isVisitable(int[] blocks, int currBlockNum, int nextBlockNum) {
		return blocks[currBlockNum] <= blocks[nextBlockNum];
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int result = solution.solution(new int[]{1,1});
		System.out.println(result);
	}
}
