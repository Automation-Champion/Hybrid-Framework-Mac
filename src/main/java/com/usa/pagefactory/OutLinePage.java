package com.usa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.usa.basepage.SupperClass;


public class OutLinePage extends SupperClass {

	public OutLinePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//*[@class='ui-button-text'])[3]")
	@CacheLookup
	WebElement myaccount;

	@FindBy(xpath = "//*[@name='email_address']")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(xpath = "//*[@name='password']")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(xpath = "//*[text()='Sign In']")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(xpath = "//*[text()='My Account Information']")
	@CacheLookup
	WebElement title;

	@FindBy(xpath = "//*[text()='Log Off']")
	@CacheLookup
	WebElement lnkLogout;

	public void setUserName(String uname) {
		txtEmail.clear();
		txtEmail.sendKeys(uname);
	}

	public void getMyaccount() {
		myaccount.click();
	}

	public WebElement getTitle() {
		return title;
	}

	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public void clickLogout() {
		lnkLogout.click();
	}
}
