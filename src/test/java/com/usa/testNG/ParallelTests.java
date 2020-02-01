package com.usa.testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.Test;

public class ParallelTests {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void getIE() {
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		ieOptions.introduceFlakinessByIgnoringSecurityDomains().equals(true);
		ieOptions.requireWindowFocus().equals(true);
		ieOptions.ignoreZoomSettings().equals(true);
		ieOptions.enableNativeEvents().equals(false);
		ieOptions.enablePersistentHovering().equals(true);
		System.setProperty("webdriver.ie.driver", "/Users/mohammedalam/chromedriver");
		System.out.println("GetIE Method is running on Thread : " + Thread.currentThread().getId());
		WebDriver driver = new InternetExplorerDriver(ieOptions);		
		driver.get("https://coc.ams1907.com/pmci/facade/home");
		//driver.close();
	}
	@Test
	public void getChormeOne() {
		System.setProperty("webdriver.chrome.driver", "/Users/mohammedalam/chromedriver ");
		System.out.println("GetChromeOne Method is running on Thread : " + Thread.currentThread().getId());
		WebDriver driver = new ChromeDriver();
		driver.get("https://coc.ams1907.com/pmci/facade/home");
		//driver.close();
	}
	
	@Test
	public void getChormesTwo() {
		System.setProperty("webdriver.chrome.driver", "/Users/mohammedalam/chromedriver ");
		System.out.println("GetChromeTwo Method is running on Thread : " + Thread.currentThread().getId());
		WebDriver driver = new ChromeDriver();
		driver.get("https://coc.ams1907.com/pmci/facade/home");
		//driver.close();
	}
	
	@Test
	public void getEdge() {
		System.setProperty("webdriver.edge.driver", "./Driver/MicrosoftWebDriver.exe");
		System.out.println("GetEdgeMethod is running on Thread : " + Thread.currentThread().getId());
		WebDriver driver = new EdgeDriver();
		driver.get("https://coc.ams1907.com/pmci/facade/home");
		//driver.close();
	}
	
	@Test
	 public void getFirefox(){
	                System.setProperty("webdriver.gecko.driver", "/Users/mohammedalam/geckodriver");
	                System.out.println("GetFirefox Method is running on Thread : " + Thread.currentThread().getId());
	 WebDriver driver = new FirefoxDriver();
	 driver.get("https://coc.ams1907.com/pmci/facade/home");
	 driver.close();
	 }

}