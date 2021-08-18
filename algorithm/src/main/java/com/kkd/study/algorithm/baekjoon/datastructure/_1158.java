package com.kkd.study.algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *  https://www.acmicpc.net/problem/1158
 */
public class _1158 {
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
        int k = Integer.parseInt(input[1]);

        Queue<Integer> q = new ArrayBlockingQueue(5001);
        for (int i=1 ; i<=n ; i++) {
            q.add(i);
        }
        bw.write("<");
        while (!q.isEmpty()) {
            int i = k;
            while (--i > 0) {
                q.add(q.poll());
            }
            bw.write(String.valueOf(q.poll()));
            if (!q.isEmpty()) {
                bw.write(", ");
            }
        }
        bw.write(">\n");
    }
}