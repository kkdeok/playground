package elastic;

import java.util.HashSet;
import java.util.Set;

public class findThirdLargestNumber {
    public int thirdMax(int[] nums) {
        Set<Integer> distinct = new HashSet<>();
        for (int num : nums) {
            distinct.add(num);
        }
        int first = Integer.MIN_VALUE;
        int second =  Integer.MIN_VALUE;
        int third =  Integer.MIN_VALUE;

        for (int num : distinct) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second && num < first) {
                third = second;
                second = num;
            } else if (num > third && num < second) {
                third = num;
            }
        }

        if (nums.length < 3) {
            return first;
        } else {
            return third;
        }
    }
}
