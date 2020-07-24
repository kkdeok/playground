package com.kkd.exercise.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/2617
 */
public class _2617 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int[] visit = new int[100];

	public static void main (String[] args) throws Exception {
		String[] input = br.readLine().split( " ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		List<List<Integer>> bigAdj = new ArrayList<>();
		List<List<Integer>> smlAdj = new ArrayList<>();

		// init
		for (int i=0 ; i<=n ; i++) {
			bigAdj.add(new ArrayList<>());
			smlAdj.add(new ArrayList<>());
		}

		for (int i=0 ; i<m ; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			bigAdj.get(y).add(x);
			smlAdj.get(x).add(y);
		}

		int ans = 0;
		int mid = (n+1) / 2;
		for (int i=1 ; i<=n ; i++) {
			// 특정 숫자 (i)를 기준으로 i 보다 큰 것을 찾을 때와 작은 것을 찾을 때, 방문하는 위치가
			// 절대 겹칠 수 없기 때문에 visit 초기화를 각가 해줄 필요가 없이, 여기서 한번만 해주면 된다.
			Arrays.fill(visit, 0);
			if (dfs(i, bigAdj) >= mid || dfs(i, smlAdj) >= mid) {
				ans++;
			}
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int dfs (int idx, List<List<Integer>> adj) {
		visit[idx] = 1;
		int cnt = 0;
		List<Integer> list = adj.get(idx);
		for (int next : list) {
			if (visit[next] == 0) {
				cnt += dfs(next, adj) + 1;
			}
		}
		return cnt;
	}
}
