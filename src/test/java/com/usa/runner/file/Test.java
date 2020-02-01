package com.usa.runner.file;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Test {

	public static void main(String[] args) {
		System.setProperty("webdriver.safari.noinstall", "true"); //To stop uninstall each time
		WebDriver driver = new SafariDriver();
		driver.get("Url");

	}

}
