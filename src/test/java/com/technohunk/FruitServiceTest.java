package com.technohunk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FruitServiceTest {

	private FruitService fs=new FruitService();
	
	@Test
	void testCheckFruitWhenTrue() {
		 boolean input= true;
		 String result=fs.checkFruit(input);
		 assertEquals("Sweet", result);
	}
	
	@Test
	void testCheckFruitWhenFalse() {
		 boolean input= false;
		 String result=fs.checkFruit(input);
		 assertEquals("Sour", result);
	}

}
