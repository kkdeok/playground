package algorithm.leetcode;

import java.util.Arrays;


class _238 {
    public int[] productExceptSelf(int[] nums) {
        int total = 1;
        int zeroIdx = -1;
        boolean isAllZero = false;
        
        for (int i=0 ; i<nums.length ; i++) {
            if (nums[i] == 0) {
                if (zeroIdx == -1) {
                    zeroIdx = i;
                    continue;
                } else {
                    isAllZero = true;
                    break;
                }
            }
            total *= nums[i];
        }

        int[] result = new int[nums.length];
        if (isAllZero) {
            Arrays.fill(result, 0);
            return result;
        }
        
        for (int i=0 ; i<nums.length ; i++) {
            if (zeroIdx == -1) {
                result[i] = total / nums[i];
            } else if (zeroIdx == i) {
                result[i] = total;
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        _238 process = new _238();

        int[] result = process.productExceptSelf(new int[]{-1, 1, 0, -3, 3});
        for (int n : result) {
            System.out.println(n);
        }
    }
}