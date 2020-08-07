package com.kkd.exercise.algorithm.leetcode;

public class RemoveOuterParentheses {
	
	public static void main(String[] args) {
		String s = "";
		System.out.println(removeOuterParentheses(s));
	}

	public static String removeOuterParentheses(String S) {
		int stack = 0;
		StringBuilder ret = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		for (char c : S.toCharArray()) {
			stack += c == '(' ? 1 : -1;
			sb.append(c);
			if (stack == 0) {
				String sub = sb.toString();
				ret.append(sub, 1, sub.length() - 1);
				sb = new StringBuilder();
			}
		}
		return ret.toString();
	}
}
