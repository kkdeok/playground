package com.kkd.study.problem_solving.baekjoon.datastructure;

import java.io.*;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/1406
 */
public class _1406 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        char[] line = br.readLine().toCharArray();
        for (char c : line) {
            left.push(c);
        }
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            String[] input = br.readLine().split(" ");
            String cmd = input[0];
            if ("L".equals(cmd)) {
                if (!left.empty()) {
                    right.push(left.pop());
                }
            } else if ("D".equals(cmd)) {
                if (!right.empty()) {
                    left.push(right.pop());
                }
            } else if ("B".equals(cmd)) {
                if (!left.empty()) {
                    left.pop();
                }
            } else if ("P".equals(cmd)) {
                char c = input[1].charAt(0);
                left.push(c);
            }
        }

        int size = left.size();
        for (int i=0 ; i<size ; i++) {
            bw.write(left.elementAt(i));
        }
        while (!right.empty()) {
            bw.write(right.pop());
        }
        bw.write("\n");
    }
}