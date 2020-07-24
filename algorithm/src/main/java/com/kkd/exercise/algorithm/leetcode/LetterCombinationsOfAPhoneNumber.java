package com.kkd.exercise.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {
	public static void main(String[] args) {
		List<String> ret = new Solution().letterCombinations("");
		for (String str : ret) {
			System.out.println(str);
		}
	}

	static class Solution {
		private static Map<Character, List<Character>> map = new HashMap<>();
		private List<String> ret = new ArrayList<>();

		static {
			map.put('2', Arrays.asList('a', 'b', 'c'));
			map.put('3', Arrays.asList('d', 'e', 'f'));
			map.put('4', Arrays.asList('g', 'h', 'i'));
			map.put('5', Arrays.asList('j', 'k', 'l'));
			map.put('6', Arrays.asList('m', 'n', 'o'));
			map.put('7', Arrays.asList('p', 'q', 'r', 's'));
			map.put('8', Arrays.asList('t', 'u', 'v'));
			map.put('9', Arrays.asList('w', 'x', 'y', 'z'));

		}

		public List<String> letterCombinations(String digits) {
			if (!digits.isEmpty()) {
				cal(0, digits.toCharArray(), "");
			}
			return ret;
		}

		private void cal(int idx, char[] cs, String s) {
			if (idx == cs.length) {
				ret.add(s);
				return;
			}
			List<Character> list = map.get(cs[idx]);
			for (Character c : list) {
				cal(idx + 1, cs, s + c);
			}
		}
	}

}