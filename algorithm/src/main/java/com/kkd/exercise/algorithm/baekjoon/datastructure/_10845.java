package com.kkd.exercise.algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.LinkedList;

/**
 * https://www.acmicpc.net/problem/10845
 */
public class _10845 {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        int n = Integer.parseInt(br.readLine());
        LinkedList<Integer> q = new LinkedList();

        while (n-- > 0) {
            String[] input = br.readLine().split(" ");
            String cmd = input[0];
            if ("push".equals(cmd)) {
                int value = Integer.parseInt(input[1]);
                q.add(value);
            } else if ("pop".equals(cmd)) {
                int value = q.isEmpty() ? -1 : q.poll();
                bw.write(value + "\n");
            } else if ("size".equals(cmd)) {
                bw.write(q.size() + "\n");
            } else if ("empty".equals(cmd)) {
                int isEmpty = q.isEmpty() ? 1 : 0;
                bw.write(isEmpty + "\n");
            } else if ("front".equals(cmd)) {
                int value = q.isEmpty() ? -1 : q.getFirst();
                bw.write(value + "\n");
            } else if ("back".equals(cmd)) {
                int value = q.isEmpty() ? -1 : q.getLast();
                bw.write(value + "\n");
            }
        }
    }
}
