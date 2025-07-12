package algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // input validation:
    public static int[] minimumN(int[] arr1, int[] arr2, int n) {
        int[] merged = new int[arr1.length + arr2.length];
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 4, 12, 14};
        int[] arr2 = new int[]{3, 6, 7, 19};
        int[] res = minimumN(arr1, arr2, 5);
        System.out.println(Arrays.toString(res)); // [1, 2, 3, 4, 6]}
    }
}