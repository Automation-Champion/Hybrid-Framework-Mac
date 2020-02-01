package com.usa.testNG;

import org.testng.annotations.Test;

public class DependencyTestUsingAnnotation {

	@Test
	public void testPrintMessage() {
		System.out.println("I am runing first");
	}

	@Test(dependsOnMethods = { "testPrintMessage" })
	public void testSalutationMessage() {
		System.out.println("I am runing second");
	}

	@Test(dependsOnMethods = { "testSalutationMessage" })
	public void initEnvironmentTest() {
		System.out.println("I am runing third");
	}
}
