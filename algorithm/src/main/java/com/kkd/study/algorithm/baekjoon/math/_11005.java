package com.kkd.study.algorithm.baekjoon.math;

import java.io.*;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/11005
 */
public class _11005 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        Stack<Character> s = new Stack<>();
        while (n != 0) {
            s.push(Character.forDigit(n % b, b));
            n = n / b;
        }
        while (!s.empty()) {
            bw.write(String.valueOf(s.pop()).toUpperCase());
        }
        bw.write("\n");
    }
}
