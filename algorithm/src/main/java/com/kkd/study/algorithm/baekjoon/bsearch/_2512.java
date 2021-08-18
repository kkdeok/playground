package com.kkd.study.algorithm.baekjoon.bsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _2512 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int m = Integer.parseInt(br.readLine());
		
		int ans = 0;
		int l = 0, r = m;
		
		// NOTE: 여기서 반드시 l<=r을 해줘야 한다.
		// 왜냐하면, 예를 들어서 l=0, r=1 인 경우에 (l+r)/2를 하면 0이 나와서 1을 검사하지 못할 수도 있기 때문이다.
		while (l <= r) { 
			int mid = (l + r) / 2;
			int p = 0;
			int max = 0;
			for (int a : arr) {
				if (mid < a) {
					p += mid;
					max = Math.max(max, mid);	
				} else {
					p += a;
					max = Math.max(max, a);
				}
			}
			if (p <= m) {
				l = mid + 1;
				ans = Math.max(ans, max);
			} else {
				r = mid - 1;
			}
		}
		System.out.println(ans);
	}
}
