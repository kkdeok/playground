package com.kkd.study.java.interfaces.animal.pig;

import com.kkd.study.java.interfaces.animal.IAnimal;

public class PigAnimal implements IAnimal<PigContext> {

	@Override
	public void bark(PigContext context) {
		System.out.println("꿀꿀");
		context.setParentData("꿀꿀 부모");
		context.setChildData("꿀꿀 자식");
	}
}
