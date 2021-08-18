package com.kkd.study.algorithm.baekjoon.datastructure;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/10824
 */
public class _10824 {
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
        String ab = input[0] + input[1];
        String cd = input[2] + input[3];
        bw.write(Long.parseLong(ab) + Long.parseLong(cd) + "\n");
    }
}
