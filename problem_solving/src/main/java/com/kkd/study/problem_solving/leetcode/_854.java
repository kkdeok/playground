package com.kkd.study.problem_solving.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/k-similar-strings/
 */
public class _854 {
	class Solution {
		// CHECK:
		// Q. 인접한 값만 swap할 수 있나요? -> NO

		// DESCRIBE:
		// BFS:
		//    abac baca
		// 1) i    i   
		// 2)  j        : add(baac)
		// 3)   j  i
		// 4)    j i

		//    baac baca
		// 1)   i    i
		// 2)    j      : add(baca)


		// need to check visit, Set<String> visit; visit.contains('xxx')
		// 1. start from string A.
		// 2. add all possible swapped string into queue. (in this progress, we can check whether if we already made the string or not. means visit).
		// 3. find B from the queue. first find will be answer.
		// Time complexity: -> 문자열의 길이 (20)

		// IMPLEMENTATION:
		public int kSimilarity(String A, String B) {
			Map<String, Integer> dist = new HashMap<>();
			Queue<String> q = new LinkedList<>();

			q.offer(A);
			dist.put(A, 0);

			while (!q.isEmpty()) {
				String S = q.poll();

				if (S.equals(B)) {
					return dist.get(S);
				}

				for (String T : getNext(S, B)) {
					if (!dist.containsKey(T)) {
						q.offer(T);
						dist.put(T, dist.get(S) + 1);
					}
				}
			}
			return 0;
		}

		private List<String> getNext(String S, String B) {
			List<String> ret = new ArrayList<>();

			int i=0;
			for (; i<S.length() ; i++) {
				if (S.charAt(i) != B.charAt(i)) {
					break;
				}
			}

			char[] charArr = S.toCharArray();
			for (int j=i+1 ; j<S.length() ; j++) {
				if (S.charAt(j) == B.charAt(i)) {
					swap(charArr, j, i);
					ret.add(String.valueOf(charArr));
					swap(charArr, j, i);
				}
			}
			return ret;
		}

		private void swap(char[] arr, int a, int b) {
			char temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
	}
}
