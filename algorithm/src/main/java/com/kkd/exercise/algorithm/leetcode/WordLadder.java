package com.kkd.exercise.algorithm.leetcode;

import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {
	
	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = Arrays.asList("hot","dog","cog");
		System.out.println(ladderLength(beginWord, endWord, wordList));
	}

	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		int len = beginWord.length();
		Map<String, List<String>> map = new HashMap<>();
		for (String word : wordList) {
			for (int i=0 ; i<len ; i++) {
				String tempWord = word.substring(0, i) + "*" + word.substring(i+1, len);
				List<String> list = map.getOrDefault(tempWord, new ArrayList<>());
				list.add(word);
				map.put(tempWord, list);
			}
		}
		
		Map<String, Boolean> visit = new HashMap<>();
		Queue<Pair<String, Integer>> q = new LinkedList<>();
		q.add(new Pair<>(beginWord, 1));
		visit.put(beginWord, true);
		
		while (!q.isEmpty()) {
			Pair<String, Integer> pair = q.poll();
			String word = pair.getKey();
			int count = pair.getValue();
			
			for (int i=0 ; i<len ; i++) {
				String tempWord = word.substring(0, i) + "*" + word.substring(i+1, len);
				for (String next : map.getOrDefault(tempWord, new ArrayList<>())) {
					
					if (next.equals(endWord)) {
						return count + 1;
					}
					
					if (!visit.containsKey(next)) {
						q.add(new Pair<>(next, count + 1));
						visit.put(next, true);
					}
				}
			}
		}
		
		return 0;
	}
}
