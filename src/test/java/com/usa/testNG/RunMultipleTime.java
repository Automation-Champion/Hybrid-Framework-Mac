package com.usa.testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RunMultipleTime {

	
	 @Test(invocationCount = 10)
	// @Test
	  public void testMethod() {
		      Assert.assertTrue(true); // false
		  System.out.println("This is test testMethod : ");
		
	  }
}

