package com.usa.testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_Annotations {


	   @Test
	   // Marks a class or a method as a part of the test.
	   public void testCase() {
	      System.out.println(" Only One Test case ");
	   }

	   @BeforeMethod
	   //The annotated method will be run before each test method.
	   public void beforeMethod() {
	      System.out.println("I Am running beforeMethod");
	   }

	   @AfterMethod
	   //The annotated method will be run after each test method.
	   public void afterMethod() {
	      System.out.println("I Am running afterMethod");
	   }

	   @BeforeClass
	   //The annotated method will be run only once before the first test method in the current class is invoked.
	   public void beforeClass() {
	      System.out.println("I Am running beforeClass");
	   }

	   @AfterClass
	   //The annotated method will be run only once after all the test methods in the current class have run.
	   public void afterClass() {
	      System.out.println("I Am running afterClass");
	   }

	   @BeforeTest
	   //The annotated method will be run before any test method belonging to the classes inside the <test> tag is run.
	   public void beforeTest() {
	      System.out.println("I Am running beforeTest");
	   }

	   @AfterTest
	   //The annotated method will be run after all the test methods belonging to the classes inside the <test> tag have run.
	   public void afterTest() {
	      System.out.println("I Am running afterTest");
	   }

	   @BeforeSuite
	   //The annotated method will be run only once before all tests in this suite have run.
	   public void beforeSuite() {
	      System.out.println("I Am running beforeSuite");
	   }

	   @AfterSuite
	   //The annotated method will be run only once after all tests in this suite have run.
	   public void afterSuite() {
	      System.out.println("I Am running afterSuite");
	   }
	   		
/*	* *
      @BeforeGroups
      The list of groups that this configuration method will run before. This method is 
       guaranteed to run shortly before the first test method that belongs to any
       of these groups is invoked.
       
       @AfterGroups
       The list of groups that this configuration method will run after. This method is 
        guaranteed to run shortly after the last test method that belongs to any of these
        groups is invoked.
        
       @DataProvider
	   Marks a method as supplying data for a test method. The annotated method must 
	   return an Object[ ][ ], where each Object[ ] can be assigned the parameter list of
	   the test method. The @Test method that wants to receive data from this 
	   DataProvider needs to use a dataProvider name equals to the name of this annotation.
	   
	    @Listeners
	    Defines listeners on a test class.
	   
	    @Parameters
        Describes how to pass parameters to a @Test method.        
*/
	}
