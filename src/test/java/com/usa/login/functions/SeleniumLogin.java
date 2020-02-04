package com.usa.login.functions;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumLogin {
	public static WebDriver driver;
	static String textFile;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver", "/Users/mohammedalam/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// How to do mouse Hover by actions class
		Actions actions = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.id("nav-link-accountList"));
		actions.moveToElement(mainMenu);
		actions.build().perform();

		// How to click on element using Action class
		WebElement subMenu = driver.findElement(By.cssSelector("span.nav-action-inner"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();

		// How to send key using Action class
		WebElement element = driver.findElement(By.xpath("//*[@id='ap_email']"));
		String text = "weloveusa4ever@gmail.com";
		actions.sendKeys(element, text).perform();

		// How to click on Java script
		WebElement locator = driver.findElement(By.xpath("(//*[@id='continue'])[2]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", locator);

		// How to send key using Java script Executor class
		String password = "SAYEDawan2008@";
		WebElement elem = driver.findElement(By.xpath("//*[@id='ap_password']"));
		js.executeScript("arguments[0].setAttribute('value', '" + password + "')", elem);

		// How to click btn by looping until find element
		WebElement buttonClick = driver.findElement(By.xpath("//*[@id='signInSubmit']"));
		for (int i = 0; i <= 20; i++) {
			buttonClick.click();
			break;
		}
		// How to enter text in search box and hit enter
		WebElement textbox = driver.findElement(By.id("twotabsearchtextbox"));
		textbox.sendKeys("Iphone");
		textbox.sendKeys(Keys.ENTER);

		// How to select dynamic element and print all the value
		List<WebElement> price = driver.findElements(By.xpath("//*[@class='a-price']"));
		System.out.println("Total Iphone count : " + price.size());
		for (WebElement select : price) {
			String priceList = select.getText();
			System.out.println("Price List : " + priceList);
		}
		// How to select dynamic element and click the value as our requirement
		for (int i = 0; i < price.size(); i++) {
			if (i > 5) {
				System.out.println("Selected number : " + i + " And Iphone price : " + price.get(i).getText());
				price.get(i).click();
				String output = driver.findElement(By.xpath("//*[@class='a-price']")).getText();
				File DestFile = new File("Alam.txt");
				FileUtils.writeStringToFile(DestFile, output);
				textFile = output;
				System.out.println("Selected iphone price : " + output);
				break;
			}
		}

		// How to verify text form web page
		FileReader fr = new FileReader("/Users/mohammedalam/eclipse-workspace/Hybrid-Framework/Alam.txt");
		int i;
		while ((i = fr.read()) != -1)
			System.out.print((char) i);

		WebElement verify = driver.findElement(By.id("priceblock_ourprice"));
		if (verify == fr) {
			System.out.println("Expected Iphone price : " + verify.getText());
		} else {
			System.out.println(">>>>>>>Test fail<<<<<<");
		}

		// How to mouse Hover with java Script
		WebElement log = driver.findElement(By.id("nav-link-accountList"));
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, log);

		// How to click on Java script
		WebElement locators = driver
				.findElement(By.xpath("//*[@id='nav-al-your-account']//*[contains(text(),'Sign Out')]"));
		js.executeScript("arguments[0].click();", locators);
		driver.quit();
	}

}
