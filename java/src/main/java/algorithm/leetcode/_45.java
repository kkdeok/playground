package algorithm.leetcode;

import java.util.Arrays;

public class _45 {
    public int jump(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[0] = 0;
        for (int i=0 ;i<nums.length ; i++) {
            int jumpLimit = nums[i];
            for (int j=1 ; j<=jumpLimit && i + j < nums.length ; j++) {
                int next = i + j;
                memo[next] = Math.min(memo[next], memo[i]+ 1);
            }
        }
        return memo[nums.length-1];
    }
}
