package com.kkd.study.algorithm.codingtest.amazon._20221007;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class No1 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(lis(arr, arr.length));
    }

    static int max_ref; // stores the LIS
    static int _lis(int arr[], int n) {
        // base case
        if (n == 1)
            return 1;

        // 'max_ending_here' is length of LIS ending with
        // arr[n-1]
        int res, max_ending_here = 1;

        /* Recursively get all LIS ending with arr[0],
           arr[1] ... arr[n-2]. If   arr[i-1] is smaller
           than arr[n-1], and max ending with arr[n-1] needs
           to be updated, then update it */
        for (int i = 1; i < n; i++) {
            res = _lis(arr, i);

            if (arr[i - 1] < arr[n - 1] && res + 1 > max_ending_here)
                max_ending_here = res + 1;
        }

        // Compare max_ending_here with the overall max. And
        // update the overall max if needed
        if (max_ref < max_ending_here)
            max_ref = max_ending_here;

        // Return length of LIS ending with arr[n-1]
        return max_ending_here;
    }

    // The wrapper function for _lis()
    static int lis(int arr[], int n)
    {
        // The max variable holds the result
        max_ref = 1;

        // The function _lis() stores its result in max
        _lis(arr, n);

        // returns max
        return max_ref;
    }
}
