package elastic;

import java.util.HashSet;
import java.util.Set;

public class SecondLongNumber {

    public static Integer findSecondLargest(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        Set<Integer> unique = new HashSet<>();
        for (int num : nums) {
            unique.add(num);
        }
        if (unique.size() < 2) {
            return null;
        }

        Integer first = null;
        Integer second = null;
        for (int num : unique) {
            if (first == null || num > first) {
                second = first;
                first = num;
            } else if (second == null || (num > second && num < first)) {
                second = num;
            }
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(findSecondLargest(new int[]{5, 1, 4, 5, 3})); // 4
        System.out.println(findSecondLargest(new int[]{10, 10, 10}));    // null
        System.out.println(findSecondLargest(new int[]{1}));             // null
        System.out.println(findSecondLargest(new int[]{2, 3, 1})); // 2
    }
}
