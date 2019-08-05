package com.doubleknd26.exercise.algorithm.baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/1786
 */
public class _1786 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main (String[] args) throws IOException {
        char[] t = br.readLine().toCharArray();
        char[] p = br.readLine().toCharArray();

        int[] pi = getPi(p);
        List<Integer> ans = doKmpSearch(t, p, pi);

        bw.write(ans.size() + "\n");
        for (int a : ans) {
            bw.write(a + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] getPi(char[] p) {
        int len = p.length;
        int[] pi = new int[len];
        int j = 0;
        for (int i = 1 ; i < len ; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j - 1];
            }
            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    private static List<Integer> doKmpSearch(char[] t, char[] p, int[] pi) {
        int tlen = t.length;
        int plen = p.length;
        List<Integer> ans = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < tlen ; i++) {
            while (j > 0 && t[i] != p[j]) {
                j = pi[j - 1];
            }
            if (t[i] == p[j]) {
                if (j == plen - 1) {
                    ans.add(i - j + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        return ans;
    }
}
