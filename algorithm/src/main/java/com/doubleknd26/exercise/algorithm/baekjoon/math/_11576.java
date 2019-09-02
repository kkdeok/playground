package com.doubleknd26.exercise.algorithm.baekjoon.math;

import java.io.*;
import java.util.Stack;

public class _11576 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        String input[] = br.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int m = Integer.parseInt(br.readLine());

        String values[] = br.readLine().split(" ");
        int i=0, dec=0;
        while (--m >= 0) {
            dec += Integer.parseInt(values[i++]) * (int) Math.pow(a, m);
        }

        Stack<Integer> s = new Stack<>();
        while (dec != 0) {
            s.push(dec % b);
            dec = dec / b;
        }

        while (!s.isEmpty()) {
            bw.write(s.pop() + " ");
        }
    }
}
