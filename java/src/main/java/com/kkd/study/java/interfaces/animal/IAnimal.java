package com.kkd.study.java.interfaces.animal;

public interface IAnimal <T extends Context> {
	
	void bark(T context);
}
