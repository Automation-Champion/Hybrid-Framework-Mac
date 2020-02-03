package com.usa.login.functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class TextFileLogin {
	public static WebDriver driver;
	public static Properties prop;

	public TextFileLogin() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/usa/config/Config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getpropertiesfile() throws Throwable {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			Reporter.log("=====Browser Session Started=====", true);
			System.setProperty("webdriver.chrome.driver", "/Users/mohammedalam/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("headless")) {
			String chromeDriverPath = "./Driver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
					"--ignore-certificate-errors", "--silent");
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(prop.getProperty("URL"));
	}

	@Test
	public void textFileLogin() throws Throwable {
		getpropertiesfile();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='signin_email']")).sendKeys(prop.getProperty("userName"));
		driver.findElement(By.xpath("//*[@id='signin_password']")).sendKeys(prop.getProperty("textPassword"));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='signin_submit']")).click();
		driver.quit();
	}
}
