package com.kkd.exercise.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKfrequentWords {

    public static void main(String[] args) {
        String[] input =
            new String[] { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" };
        int k = 4;
        List<String> ans = topKFrequent(input, k);

        for (String str : ans) {
            System.out.println(str);
        }
    }
    
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2))
            ? w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }
//
//    public static List<String> topKFrequent(String[] words, int k) {
//        Map<String, Integer> map = new HashMap<>();
//        for (String word : words) {
//            if (!map.containsKey(word)) {
//                map.put(word, 0);
//            }
//            int temp = map.get(word);
//            map.put(word, temp + 1);
//        }
//	
//        Map<String, Integer> newMap = map.entrySet().stream()
//            .sorted(Collections.reverseOrder(Map.Entry.<String, Integer>comparingByValue()).thenComparing(Map.Entry.comparingByKey()))
//            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//        List<String> keys = new ArrayList<>(newMap.keySet());
//        List<String> ret = new ArrayList<>();
//        for (int i = 0; i < k; i++) {
//            ret.add(keys.get(i));
//        }
//        return ret;
//    }
}
