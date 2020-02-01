package com.usa.login.functions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberLogin {
	public WebDriver driver;

	@Given("^User Launch Chrome browser$")
	public void user_Launch_Chrome_browser() {
		System.setProperty("webdriver.chrome.driver", "/Users/mohammedalam/chromedriver");
		driver = new ChromeDriver();
	}

	@When("^User opens URL \"([^\"]*)\"$")
	public void user_opens_URL(String URL) {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("^User enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_Email_as_and_Password_as(String username, String password) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='Email']")).sendKeys(username);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='Password']")).sendKeys(password);
	}

	@When("^Click on Login$")
	public void click_on_Login() throws InterruptedException {
		driver.findElement(By.xpath("//*[@class='button-1 login-button']")).click();
		Thread.sleep(4000);
	}

	@Then("^Page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be(String title) throws InterruptedException {
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(title, driver.getTitle());
			System.out.println(title);
		}
		Thread.sleep(3000);
	}
	@When("^User click on Log out link$")
	public void user_click_on_Log_out_link() throws InterruptedException {
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(4000);
	}
	@Then("^Page logout Title should be \"([^\"]*)\"$")
	public void page_logout_Title_should_be(String logOutTitle) throws Throwable {
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(logOutTitle, driver.getTitle());
			System.out.println(logOutTitle);
		}
		Thread.sleep(3000);
	}
	@Then("^close browser$")
	public void close_browser() throws InterruptedException {
		driver.close();
		driver.quit();
		Thread.sleep(4000);
	}
}