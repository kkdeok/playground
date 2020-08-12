package com.kkd.exercise.algorithm.leetcode;

import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 1, 1, 2, 2, 3 };
        int k = 2;
        int[] ret = topKFrequent(nums, k);

        for (int a : ret) {
            System.out.println(a);
        }
    }

    //    public static int[] topKFrequent(int[] nums, int k) {
    //        Map<Integer, Long> count = new HashMap<>();
    //        for (int num : nums) {
    //            count.put(num, count.getOrDefault(num, 0L) + 1);
    //        }
    //        List<Integer> list = new ArrayList<>(count.keySet());
    //        list.sort((a, b) -> count.get(b).compareTo(count.get(a)));
    //        return list.subList(0, k).stream().mapToInt(i->i).toArray();
    //    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0L) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparing(count::get));
        return heap.stream().mapToInt(i -> i).toArray();
    }
}