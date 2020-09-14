package com.kkd.study.problem_solving.baekjoon;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/1305
 */
public class _1305 {
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main (String[] args) throws IOException {
        char[] s = br.readLine().toCharArray();
        int[] pi = getPi(s);
        int l = s.length;
        int ans = l - pi[l - 1];
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] getPi(char[] s) {
        int n = s.length;
        int[] pi = new int[n];
        int j = 0;
        for (int i=1 ; i<n ; i++) {
            while (j>0 && s[i] != s[j]) {
                j = pi[j];
            }
            if (s[i] == s[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
