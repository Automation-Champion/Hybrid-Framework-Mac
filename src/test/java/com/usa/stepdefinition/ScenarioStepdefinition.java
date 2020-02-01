package com.usa.stepdefinition;

import java.io.IOException;
import org.testng.Assert;
import com.usa.basepage.SupperClass;
import com.usa.pagefactory.ScenarioPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioStepdefinition extends SupperClass {

	@Before
	public void setup() throws IOException {
		try {
			initialization();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	@Given("^User Launch Chrome browser$")
	public void user_Launch_Chrome_browser() {
		pf = new ScenarioPage(driver);
	}

	@When("^User opens URL \"([^\"]*)\"$")
	public void user_opens_URL(String url) {
		logger.info("******** Opening URL*********");
		driver.get(url);
	}

	@When("^User enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("******** Providing login details*********");
		pf.setUserName(email);
		pf.setPassword(password);
	}

	@When("^Click on Login$")
	public void click_on_Login() throws Throwable {
		logger.info("******** started login*********");
		pf.clickLogin();
		Thread.sleep(3000);
	}

	@Then("^Page logout Title should be \"([^\"]*)\"$")
	public void page_logout_Title_should_be(String title) throws Throwable {
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			logger.info("******** Login Filed*********");
			Assert.assertTrue(false);
		} else {
			logger.info("******** Login Passed*********");
			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(3000);
	}

	@When("^User click on Log out link$")
	public void user_click_on_Log_out_link() {
		logger.info("******** Click on logout link*********");
		pf.clickLogout();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Then("^Page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be(String logOutTitle) {
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(logOutTitle, driver.getTitle());
			System.out.println(logOutTitle);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("^close browser$")
	public void close_browser() {
		logger.info("********closing browser********");
		driver.close();
		driver.quit();
		
	}

}
