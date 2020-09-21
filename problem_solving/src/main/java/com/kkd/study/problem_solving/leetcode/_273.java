package com.kkd.study.problem_solving.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/integer-to-english-words/
 */
public class _273 {
	static class Solution {

		// 123 -> One Hunred Twenty Three
		// 12 345 -> Twelve Thousand Three Hundred Forty Five.
		// 1 234 567 -> ONe Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven.
		// 1 234 567 891 -> One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One

		// 1. iterate from the back.
		// 2. cut off 3 integers and convert it to the words. count it to say Thousand, Million, Billion.

		// 12345/ 1000 = 12 .. 345
		
		private static Map<Integer, String> singleUnitMap = new HashMap<>();
		private static Map<Integer, String> doubleUnitMap = new HashMap<>();
		private static Map<Integer, String> thousandUnitMap = new HashMap<>();

		static {
			singleUnitMap.put(0, "");
			singleUnitMap.put(1, " One");
			singleUnitMap.put(2, " Two");
			singleUnitMap.put(3, " Three");
			singleUnitMap.put(4, " Four");
			singleUnitMap.put(5, " Five");
			singleUnitMap.put(6, " Six");
			singleUnitMap.put(7, " Seven");
			singleUnitMap.put(8, " Eight");
			singleUnitMap.put(9, " Nine");

			doubleUnitMap.put(0, "");
			doubleUnitMap.put(2, " Twenty");
			doubleUnitMap.put(3, " Thirty");
			doubleUnitMap.put(4, " Forty");
			doubleUnitMap.put(5, " Fifty");
			doubleUnitMap.put(6, " Sixty");
			doubleUnitMap.put(7, " Seventy");
			doubleUnitMap.put(8, " Eighty");
			doubleUnitMap.put(9, " Ninety");
			doubleUnitMap.put(10, " Ten");
			doubleUnitMap.put(11, " Eleven");
			doubleUnitMap.put(12, " Twelve");
			doubleUnitMap.put(13, " Thirteen");
			doubleUnitMap.put(14, " Fourteen");
			doubleUnitMap.put(15, " Fifteen");
			doubleUnitMap.put(16, " Sixteen");
			doubleUnitMap.put(17, " Seventeen");
			doubleUnitMap.put(18, " Eighteen");
			doubleUnitMap.put(19, " Nineteen");

			thousandUnitMap.put(0, "");
			thousandUnitMap.put(1, " Thousand");
			thousandUnitMap.put(2, " Million");
			thousandUnitMap.put(3, " Billion");

		}

		public String numberToWords(int num) {
			if (num == 0) {
				return "Zero";
			}

			String ret = "";
			int thousandUnit = 0;
			while (num != 0) {
				int remain = num % 1000;
				String temp = convertIntToEnglishWords(remain);
				if (!temp.isEmpty()) {
					temp += thousandUnitMap.get(thousandUnit);
				}
				thousandUnit++;
				ret = temp + ret;
				num /= 1000;
			}
			return ret.trim();
		}

		private String convertIntToEnglishWords(int num) {
			String ret = "";
			if (num >= 100) {
				ret += convertIntToEnglishWords(num/100) + " Hundred";
				num %= 100;
			}

			if (num == 0) {
				return ret;
			}

			if (num / 10 == 1) {
				ret += doubleUnitMap.get(num);
			} else {
				ret += doubleUnitMap.get(num/10);
				ret += singleUnitMap.get((num%10));
			}
			return ret;
		}
	}
}
