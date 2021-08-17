package com.kkd.study.java;

public class Operator {
	public static void main(String[] args) {
		if (a() || b() && c()) {
			System.out.println("Hello world!");
		}
	}

	private static boolean a() {
		System.out.println("A");
		return true;
	}

	private static boolean b() {
		System.out.println("B");
		return true;
	}

	private static boolean c() {
		System.out.println("C");
		return true;
	}
}
