package com.kkd.exercise.algorithm.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 */
public class NumberOfDiceRollsWithTargetSum {
    
    public static void main(String[] args) {
        System.out.println(numRollsToTarget(30, 30, 500)); 
    }
    
    private static int[][] memo;

    public static int numRollsToTarget(int d, int f, int target) {
        if (target < d || target > d * f) return 0;
        if (d == 1) return target <= f ? 1 : 0;
        
        memo = new int[d+1][target+1];
        for (int i=0 ; i<d+1 ; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return dp(d, f, target);
    }
    
    private static int dp(int d, int f, int target) {
        // base condition
        if (d == 0) {
            return target != 0 ? 0 : 1;
        }
        if (target == 0) {
            return d != 0 ? 0 : 1;
        }
        if (memo[d][target] != -1) {
            return memo[d][target];
        }
        
        long count = 0;
        for (int i=1 ; i<=f ; i++) {
            if (target - i >= 0) {
                try {
                    count = (dp(d - 1, f, target - i) + count) % 1000000007L;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return memo[d][target] = (int) count;
    }
}
