package com.usa.testNG;

import org.testng.annotations.Factory;

public class TestNGFactoryClass {
	@Factory()
	public Object[] getTestCaseClasses() {
		Object[] testObject = new Object[3];
		testObject[0] = new TestNGOne();
		testObject[1] = new TestNGTwo();
		testObject[2] = new TestNGThree();
		return testObject;
	}

}
