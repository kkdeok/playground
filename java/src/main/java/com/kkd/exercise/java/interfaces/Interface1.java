package com.kkd.exercise.java.interfaces;


public interface Interface1 {
	void one();
	
	default void printInterface1() {
		System.out.println("interface1");
	}
	
	static void printStaticInterface1() {
		System.out.println("static interface1");
	} 
}
