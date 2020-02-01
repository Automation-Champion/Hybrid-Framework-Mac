package com.usa.stepdefinition;

import java.io.IOException;
import org.testng.Assert;
import com.usa.basepage.SupperClass;
import com.usa.pagefactory.OutLinePage;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioOutlineStefintion extends SupperClass {

	@Before
	public void setup() throws IOException {
		try {
			openBrowser();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Given("^User able to open chrome browser$")
	public void user_able_to_open_chrome_browser() {
		ot = new OutLinePage(driver);
	}

	@Given("^Put \"([^\"]*)\" and go to login page$")
	public void put_and_go_to_login_page(String URL) {
		logger.info("******** Opening URL*********");
		driver.get(URL);
	}

	@When("^User able to click my account$")
	public void user_able_to_click_my_account() {
		logger.info("******** Click on my account *********");
		ot.getMyaccount();
	}

	@When("^User able to use valid username\"([^\"]*)\"$")
	public void user_able_to_use_valid_username(String userName) {
		logger.info("******** Providing user name details*********");
		ot.setUserName(userName);
	}

	@When("^User able to put valid  password\"([^\"]*)\"$")
	public void user_able_to_put_valid_password(String password) {
		logger.info("******** Providing user password details*********");
		ot.setPassword(password);
	}

	@When("^User able to click singin button$")
	public void user_able_to_click_singin_button() {
		logger.info("******** started login*********");
		ot.clickLogin();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("^User able to validated Login status$")
	public void user_able_to_validated_Login_status() {
		if (driver.getPageSource().contains("GCR Shop")) {
			logger.info("******** Login SuccessFully*********");
			Assert.assertTrue(true);
		} else {
			logger.info("******** Login Filed*********");
			Assert.assertTrue(false);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}

	}
	@Then("^closeing the browser$")
	public void closeing_the_browser() {
		logger.info("********closing browser********");
		driver.quit();
	}
}
