package com.kkd.study.algorithm.baemin.exam_211002.no5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _5 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.lines()
			.filter(line -> line.length() >= 5 && line.length() < 10)
			.map(String::toUpperCase)
			.forEach(System.out::println);
	}
}
