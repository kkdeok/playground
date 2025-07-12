package elastic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubArraySumZero {

    public static int[] findSubarraySumZero(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        Map<Integer, Integer> sumToIndex = new HashMap<>();
        int sum = 0;

        sumToIndex.put(0, -1); // 누적합 0 이 있다고 가정.
        for (int i=0 ; i<nums.length ; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum)) {
                return new int[]{sumToIndex.get(sum) + 1, i};
            }
            sumToIndex.put(sum, i);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findSubarraySumZero(new int[]{1, 2, -3, 4}))); // [0, 2]
        System.out.println(Arrays.toString(findSubarraySumZero(new int[]{1, 2, 3})));     // null
        System.out.println(Arrays.toString(findSubarraySumZero(new int[]{3, -1, -2, 5}))); // [0, 2]
        System.out.println(Arrays.toString(findSubarraySumZero(new int[]{0})));           // [0, 0]
    }

}
