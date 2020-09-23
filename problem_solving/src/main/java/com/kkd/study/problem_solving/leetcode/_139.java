package com.kkd.study.problem_solving.leetcode;

import java.util.*;

public class _139 {
	// DESCRIBE
	// s = non empty string
	// wordDict = non empty word
	// determine if s can be segented into a space-separated sequence of on or more dictionary 
	// words.

	// CHECK
	// Q. s.size()? -> We don't know.
	// Q. case sensivitve? -> exactly match
	// Q. wordDict.size() ? -> we don't know

	// current -> next visit
	// 1. cars    -> s, rs, ca (startWith(first))
	// 2. Check whether if we visit already.)
	// 3. return true if the current is empty String.
	// Time complexity: O( Sigk=1..n-1 nCk * m ) , n = s.legnth and m = wordDict.length
	public boolean wordBreak(String s, List<String> wordDict) {
		Queue<String> q = new LinkedList<>();
		Set<String> visit = new HashSet<>();

		q.offer(s);
		visit.add(s);

		while (!q.isEmpty()) {
			String str = q.poll();

			if (str.isEmpty()) {
				return true;
			}

			for (String word : wordDict) {
				String temp = str;
				if (temp.startsWith(word)) {
					temp = temp.substring(word.length());
				}
				if (!visit.contains(temp)) {
					q.offer(temp);
					visit.add(temp);
				}
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		_139 program = new _139();
		boolean ret = program.wordBreak("ddadddbdddadd", Arrays.asList("dd","ad","da","b"));
		System.out.println(ret);
	}
}
