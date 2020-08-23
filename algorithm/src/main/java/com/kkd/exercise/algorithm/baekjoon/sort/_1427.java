package com.kkd.exercise.algorithm.baekjoon.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * https://www.acmicpc.net/problem/1427
 */
public class _1427 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		char[] chars = br.readLine().toCharArray();
		process(chars);
		System.out.println(String.valueOf(chars));
	}
	
	public static void process(char[] chars) {
		int len = chars.length;
		for (int i=0 ; i<len ; i++) {
			int maxIdx = i;
			for (int j=i+1 ; j<len ; j++) {
				if (chars[j] > chars[maxIdx]) maxIdx = j;
			}
			char temp = chars[maxIdx];
			chars[maxIdx] = chars[i];
			chars[i] = temp;
		}
		
//		for (int i=0 ; i<len ; i++) {
//			for (int j=1 ; j<len-i ; j++) {
//				if (chars[j-1] < chars[j]) {
//					char temp = chars[j-1];
//					chars[j-1] = chars[j];
//					chars[j] = temp;
//				}
//			}
//		}
		
	}

}
