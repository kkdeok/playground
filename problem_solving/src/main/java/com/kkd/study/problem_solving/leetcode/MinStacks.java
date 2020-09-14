package com.kkd.study.problem_solving.leetcode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 */
public class MinStacks {

	public static void main(String[] args) {

	}

	class MinStack {
		private Stack<Integer> s;

		/** initialize your data structure here. */
		public MinStack() {
			s = new Stack<>();
		}

		public void push(int x) {
			s.push(x);
		}

		public void pop() {
			s.pop();
		}

		public int top() {
			return s.peek();
		}

		public int getMin() {
			int size = s.size();
			int min = Integer.MAX_VALUE;
			for (int i=0 ; i<size ; i++) {
				min = Math.min(min, s.get(i));
			}
			return min;
		}
	}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}