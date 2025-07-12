package elastic;

import java.util.Arrays;

public class MoveZeroToEnd {
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int idx = 0;
        for (int i=0 ; i<nums.length ; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = 0;
                nums[idx++] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 1, 0, 3, 12};
        moveZeroes(arr1);
        System.out.println(Arrays.toString(arr1)); // [1, 3, 12, 0, 0]
    }
}
