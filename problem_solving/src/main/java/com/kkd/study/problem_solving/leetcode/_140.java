package com.kkd.study.problem_solving.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break-ii/
 */
public class _140 {
	// Description:
	// non-empty string s and a dictionary wordDict contating a list of non-empty words, 
	// add spaces in s to construct a sentence where each word is a valid dictionary word. 
	// Return all such possible sentences.

	// Check:
	// Non Empty s,
	// Non Empty dics

	// Idea 1: dynamic programming
	// s: catsanddog    dict: ["cat", "cats", "and", "sand", "dog"]
	// cat  s anddog
	// ca t s anddog
	// cats anddog
	// d[word] = all possible sentences
	// T.C = dynamic programming time complexity -> Worst case: O(s.len * dict.size)

	private Map<String, List<String>> memo = null;

	public List<String> wordBreak(String s, List<String> wordDict) {
		// init
		memo = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("");
		memo.put("", list);

		return makeSentences(s, wordDict);
	}

	private List<String> makeSentences(String s, List<String> wordDict) {
		if (memo.containsKey(s)) {
			return memo.get(s);
		}

		List<String> sentences = new ArrayList<>();
		for (String word : wordDict) {
			if (s.startsWith(word)) {
				List<String> subSentences = makeSentences(s.substring(word.length()), wordDict);
				for (String subSentence : subSentences) {
					sentences.add((word + " " + subSentence).trim());
				}
			}
		}

		memo.put(s, sentences);
		return sentences;
	}

	public static void main(String[] args) {
		_140 program = new _140();
		List<String> ans =
			program.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
		for (String sentence : ans) {
			System.out.println(sentence);
		}
	}
}
