package com.kkd.exercise.algorithm.leetcode.string;

import java.util.Stack;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeString {
    
    public static void main(String[] args) {
        DecodeString app = new DecodeString();
        System.out.println(app.decodeString("100[leetcode]"));
    }

    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        
        for (int i=0 ; i<chars.length ; i++) {
            if (chars[i] == ']') { 
                StringBuilder sb = new StringBuilder(); 
                while (stack.peek() != '[') {
                    sb.insert(0, stack.pop());
                }
                String substr = sb.toString();
                stack.pop();
                
                sb = new StringBuilder(); 
                while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9') {
                   sb.insert(0, stack.pop()); 
                }
                int repeat = Integer.parseInt(sb.toString());
                
                sb = new StringBuilder();
                for (int j=0 ; j<repeat ; j++) {
                    sb.append(substr);
                }
                for (char c : sb.toString().toCharArray()) {
                    stack.push(c);
                }
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.stream().unordered().map(Object::toString).collect(Collectors.joining(""));
    }
}
