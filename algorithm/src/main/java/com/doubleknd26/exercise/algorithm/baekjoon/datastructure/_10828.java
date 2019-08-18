package com.doubleknd26.exercise.algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/10828
 */
public class _10828 {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        execute();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void execute() throws IOException {
        int n;
        Stack<Integer> stack = new Stack<>();

        n = Integer.parseInt(br.readLine());
        while (n > 0) {
            String[] input = br.readLine().split(" ");
            String cmd = input[0];
            if ("push".equals(cmd)) {
                int v = Integer.parseInt(input[1]);
                stack.push(v);
            } else if ("pop".equals(cmd)) {
                if (stack.empty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(stack.pop() + "\n");
                }
            } else if ("size".equals(cmd)) {
               bw.write(stack.size() + "\n");
            } else if ("empty".equals(cmd)) {
                bw.write((stack.empty() ? "1" : "0") + "\n");
            } else if ("top".equals(cmd)) {
                if (stack.empty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(stack.peek() + "\n");
                }
            }
            n--;
        }
    }
}
