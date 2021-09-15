package com.kkd.study.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class _155 {
	class MinStack {
		private List<Integer> stack;
		private List<Integer> minList;

		/** initialize your data structure here. */
		public MinStack() {
			stack = new ArrayList<>();
			minList = new ArrayList<>();
		}

		public void push(int val) {
			stack.add(val);

			if (minList.isEmpty()) {
				minList.add(val);
			} else {
				int min = minList.get(minList.size()-1);
				if (min > val) minList.add(val);
				else minList.add(min);
			}
		}

		public void pop() {
			stack.remove(stack.size() - 1);
			minList.remove(minList.size() - 1);
		}

		public int top() {
			return stack.get(stack.size() - 1);
		}

		public int getMin() {
			return minList.get(minList.size() - 1);
		}
	}
}
