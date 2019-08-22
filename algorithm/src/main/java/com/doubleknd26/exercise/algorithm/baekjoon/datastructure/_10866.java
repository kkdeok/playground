package com.doubleknd26.exercise.algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://www.acmicpc.net/problem/10866
 */
public class _10866 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();
        while (n-- > 0) {
            String[] input = br.readLine().split(" ");
            String cmd = input[0];
            if ("push_front".equals(cmd)) {
                int v = Integer.parseInt(input[1]);
                deque.addFirst(v);
            } else if ("push_back".equals(cmd)) {
                int v = Integer.parseInt(input[1]);
                deque.addLast(v);
            } else if ("pop_front".equals(cmd)) {
                int v = deque.isEmpty() ? -1 : deque.pollFirst();
                bw.write(v + "\n");
            } else if ("pop_back".equals(cmd)) {
                int v = deque.isEmpty() ? -1 : deque.pollLast();
                bw.write(v + "\n");
            } else if ("size".equals(cmd)) {
                int v = deque.size();
                bw.write(v + "\n");
            } else if ("empty".equals(cmd)) {
                int v = deque.isEmpty() ? 1 : 0;
                bw.write(v + "\n");
            } else if ("front".equals(cmd)) {
                int v = deque.isEmpty() ? -1 : deque.peekFirst();
                bw.write(v + "\n");
            } else if ("back".equals(cmd)) {
                int v = deque.isEmpty() ? -1 : deque.peekLast();
                bw.write(v + "\n");
            }
        }
    }
}
