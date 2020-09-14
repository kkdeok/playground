package com.kkd.study.problem_solving.baekjoon.datastructure;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/10820
 */
public class _10820 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        start();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void start() throws IOException {
        String input;
        while ((input = br.readLine()) != null) {
            char[] arr = input.toCharArray();
            int len = arr.length;
            int l=0, u=0, n=0, e=0;
            for (int i=0 ; i<len ; i++) {
                char c = arr[i];
                if (c >= 'a' && c <= 'z') {
                    l++;
                } else if (c >= 'A' && c <= 'Z') {
                    u++;
                } else if (c >= '0' && c <= '9') {
                    n++;
                } else if (c == ' ') {
                    e++;
                }
            }
            bw.write(l + " " + u + " " + n + " " + e + "\n");
        }
    }
}
