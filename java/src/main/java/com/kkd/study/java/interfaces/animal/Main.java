package com.kkd.study.java.interfaces.animal;

import com.kkd.study.java.interfaces.animal.dog.DogAnimal;
import com.kkd.study.java.interfaces.animal.dog.DogContext;
import com.kkd.study.java.interfaces.animal.pig.PigAnimal;
import com.kkd.study.java.interfaces.animal.pig.PigContext;

public class Main {

	public static void main(String[] args) {
		IAnimal animal = new DogAnimal();
		Context context = new DogContext();
		
		animal.bark(context);

		System.out.println(context.getParentData());
		System.out.println( ((DogContext) context).getChildData());
		
		animal = new PigAnimal();
		context = new PigContext();
		animal.bark(context);

		System.out.println(context.getParentData());
		System.out.println( ((DogContext) context).getChildData());
	}
}
