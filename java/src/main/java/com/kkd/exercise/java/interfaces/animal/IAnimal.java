package com.kkd.exercise.java.interfaces.animal;

public interface IAnimal <T extends Context> {
	
	void bark(T context);
}
