package com.usa.selenium.practice.code;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class SeleniumChallengePractice {
	static WebDriver driver;
	public static void MouseHoverWithActionClass() {

	}

	public static void handleDropDwonWithListInterFace() {

	}

	public static void DropDwonValueWithSelectClass() {

	}

	public static void DropDwonSelectWithListInterFace() {

	}

	public static void handleAlerts() {

	}

	public static void handleMultipleWindow() {

	}

	public static void handleIfream() {

	}

	public static void handleMultipleElement() {

	}

	public static void handleCertificate() {

	}

	public static void handleProxy() {

	}

	@SuppressWarnings("deprecation")
	public static void waitThenClick(WebDriver driver, WebElement element) {
		try {
			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).pollingEvery(5, TimeUnit.SECONDS)
					.withTimeout(30, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			fluentWait.until(ExpectedConditions.elementToBeClickable(element));
			if (element.isDisplayed()) {
				element.click();
			}
		} catch (TimeoutException toe) {
			toe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clickMultipleTimes() {

	}

	public static void clickWithJavaScriptClass() {

	}

	public static void scrollDownWithJavaScriptClass() {

	}

	public static void enterKeyAndPressKeyWithActionClass() {

		
	}

	public static void clickWithActionClass() {

	}

	public static void handleCalender() {

	}

	public static void handleDrugAndDrop() {

	}

	public static void headLessBrowser() {

	}
	public static void datePickers(String num) {
		List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
		for (WebElement select : allDates) {
			String date = select.getText();
			if (date.equalsIgnoreCase(num)) {
				select.click();
				break;
			}
		}
	}
}
