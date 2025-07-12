package algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _560 {
    public int subarraySum(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);

        int count = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            int target = sum - k;
            if (countMap.containsKey(target)) {
                count = countMap.get(target);
            }
            countMap.put(target, countMap.getOrDefault(target, 0) + 1);
        }
        return count;
    }
}
