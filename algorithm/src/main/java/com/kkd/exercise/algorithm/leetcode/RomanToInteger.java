package com.kkd.exercise.algorithm.leetcode;

/**
 * https://leetcode.com/problems/roman-to-integer/
 */
public class RomanToInteger {

    public static void main(String[] args) {
        RomanToInteger app = new RomanToInteger();
        System.out.println(app.romanToInt("MCMXCIV"));
    }

    private enum RomanEnum {
        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

        private int value;

        RomanEnum(int value) {
            this.value = value;
        }

    }

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int ret = 0;
        for (int i=0 ; i<chars.length ; i++) {
            int j = i+1 < chars.length ? i+1 : -1;
            RomanEnum roman1 = RomanEnum.valueOf(String.valueOf(chars[i]));
            if (j != -1) {
                RomanEnum roman2 = RomanEnum.valueOf(String.valueOf(chars[j]));
                if (roman1.ordinal() < roman2.ordinal()) {
                    ret += roman2.value - roman1.value;
                    i++;
                    continue;
                }
            }
            ret += roman1.value;
        }
        return ret;
    }
}
