package com.kkd.exercise.algorithm.leetcode.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/increasing-subsequences/
 */
public class IncreasingSubSequences {
    
    public static void main(String[] args) {
        IncreasingSubSequences app = new IncreasingSubSequences();
        int[] sample = new int[]{4, 6, 7, 7};
        
        for (List<Integer> a : app.findSubsequences(sample)) {
            for (int aa : a) {
                System.out.print(aa + " ");
            }
            System.out.println();
        }
    }
    
    private Stack<Integer> s;
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        s = new Stack<>();
        Set<List<Integer>> ret = new HashSet<>();
        for (int i=0 ; i<nums.length ; i++) {
            doDFS(ret, nums, i);
        }
        return new ArrayList<>(ret);
    }
    
    private void doDFS(Set<List<Integer>> ret, int[] nums, int idx) {
        s.push(nums[idx]);
        if (s.size() >= 2) {
            ret.add(new ArrayList<>(s));
        }
        
        for (int i=idx+1 ; i<nums.length ; i++) {
            if (nums[idx] <= nums[i]) {
                doDFS(ret, nums, i);
            }
        }
        s.pop();
    }
}
