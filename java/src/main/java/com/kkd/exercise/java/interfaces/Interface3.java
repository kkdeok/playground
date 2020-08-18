package com.kkd.exercise.java.interfaces;

public interface Interface3 extends Interface1, Interface2 {
	void three();

	default void printInterface3() {
		System.out.println("interface3");
	}
}
