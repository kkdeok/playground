package com.kkd.exercise.java.interfaces.animal.dog;

import com.kkd.exercise.java.interfaces.animal.IAnimal;

public class DogAnimal implements IAnimal<DogContext> {
	
	@Override
	public void bark(DogContext context) {
		System.out.println("멍멍");
		context.setParentData("멍멍 부모");
		context.setChildData("멍멍 자식");
	}
}
