package com.kkd.study.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class _20 {
	public boolean isValid(String s) {
		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');

		Stack<Character> stack = new Stack<>();

		for (char c : s.toCharArray()) {
			if (map.containsKey(c)) {
				// ), ], or }
				if (stack.isEmpty() || stack.peek() != map.get(c)) return false;
				stack.pop();
			} else {
				// (, [, or {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		_20 program = new _20();
		System.out.println(program.isValid("()"));
	}
}
