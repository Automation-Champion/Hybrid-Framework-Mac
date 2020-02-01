package com.usa.utilty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;
import com.usa.util.ExtentReports.ExtentTestManager;

public class commonMethods {

	protected static WebDriver driver;
	protected static final int ID = 1;
	protected static final int CLASS = 2;
	protected static final int LINKTEXT = 3;
	protected static final int XPATH = 4;
	protected static final int CSS = 5;
	protected static final int TAGNAME = 6;
	protected static final int VISIBLETEXT = 1;
	protected static final int VALUE = 2;
	protected static final int INDEX = 3;

	public static String fn_GetTimeStamp() {
		DateFormat DF = DateFormat.getDateTimeInstance();
		Date dte = new Date();
		String DateValue = DF.format(dte);
		DateValue = DateValue.replaceAll(":", "_");
		DateValue = DateValue.replaceAll(",", "");
		return DateValue;
	}

	// commonMethods.findElement(driver, locator, 30);
	@SuppressWarnings({ "deprecation", "unused" })
	private static WebElement findElement(final WebDriver driver, final By locator, final int timeoutSeconds) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeoutSeconds, TimeUnit.SECONDS)
				.pollingEvery(1000, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				return driver.findElement(locator);

			}

		});

	}

	public static void customWait() {
		@SuppressWarnings({ "deprecation", "unused" })
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				// Wait for the condition
				.withTimeout(60, TimeUnit.SECONDS)
				// which to check for the condition with interval of 5 seconds.
				.pollingEvery(15, TimeUnit.SECONDS)
				// Which will ignore the NoSuchElementException
				.ignoring(NoSuchElementException.class);
	}

	public static void clickOn(By locator, WebDriver driver, int timeout) {
		final WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
		driver.findElement(locator).click();
	}

	public static void click(WebDriver driver, By by) {
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
			driver.findElement(by).click();
		} catch (StaleElementReferenceException sere) {
			// simply retry finding the element in the refreshed DOM
			driver.findElement(by).click();
		}
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

	public static void clickMultipleElements(WebElement someElement, WebElement someOtherElement) {
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(someElement).click(someOtherElement).keyUp(Keys.CONTROL).build().perform();
	}

	public static void wait(int timeOutInSeconds) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
	}

	public static Actions getAction() {
		Actions action = new Actions(driver);
		return action;
	}

	private static WebElement chooseElement(int byStrategy, String locatorValue) {
		By by = null;
		switch (byStrategy) {
		case ID:
			by = By.id(locatorValue);
			break;
		case CLASS:
			by = By.className(locatorValue);
			break;
		case LINKTEXT:
			by = By.linkText(locatorValue);
			break;
		case XPATH:
			by = By.xpath(locatorValue);
			break;
		case CSS:
			by = By.cssSelector(locatorValue);
			break;
		case TAGNAME:
			by = By.tagName(locatorValue);
			break;
		}
		return driver.findElement(by);
	}

	public static void mouseOver(int byStrategy, String locatorValue) {
		WebElement mO = chooseElement(byStrategy, locatorValue);
		getAction().moveToElement(mO).perform();
	}

	public static void textBox(int byStrategy, String locatorValue, String text) {
		chooseElement(byStrategy, locatorValue).sendKeys(text);
		getAction().sendKeys(Keys.ESCAPE);
	}

	public static void click(int byStrategy, String locatorValue) {
		chooseElement(byStrategy, locatorValue).click();
	}

	public static String getTxt(int byStrategy, String locatorValue) {
		String returnText = chooseElement(byStrategy, locatorValue).getText();
		return returnText;
	}

	public static void dropDown(int byStrategy, String locatorValue, int selectStrategy, Object strategyValue) {
		try {
			WebElement webElement = chooseElement(byStrategy, locatorValue);
			Select select = new Select(webElement);
			switch (selectStrategy) {
			case VISIBLETEXT:
				System.out.println("case 1");
				select.selectByVisibleText((String) strategyValue);
				break;
			case VALUE:
				System.out.println("case 2");
				select.selectByValue((String) strategyValue);
				break;
			case INDEX:
				System.out.println("case 3");
				select.selectByIndex(((Integer) strategyValue).intValue());
				break;
			}
		} catch (NoSuchElementException e) {

		}
	}

	public static void elemClick(long timeout, String locator) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until((WebDriver driver) -> {
			driver.findElement(By.id(locator)).click();
			return true;
		});
	}

	public WebElement getElement(WebDriver driver, WebElement element, By locator) {
		try {
			// Check visibility. If reference is not stale, it will return the same
			// referenced. Otherwise it will go to catch.
			element.isDisplayed();
			return element;
			// Relocate element in catch and return
		} catch (StaleElementReferenceException e) {
			return driver.findElement(locator);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

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

	// test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test
	// Failed");
	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../ErrImages/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	// log.log(Status.INFO, "Google Page
	// opened"+log.addScreenCaptureFromPath(captureScreen()));
	// log.log(Status.PASS, "Passed test
	// 2"+log.addScreenCaptureFromPath(captureScreen()));
	public String captureScreen() throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = "D://Automation_Reports//screenshots//" + getcurrentdateandtime() + ".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}

	public String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {

		}
		return str;
	}

	public static String addScreenshot() {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int) scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64," + encodedBase64;
	}

	public static void OutputAssert(String scenario, Callable<Void> func) {
		try {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Assertion passed ", scenario);
			func.call();
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					" Assertion failed for" + scenario + ": " + ex.getMessage());
		}
	}

	public boolean CheckWebElementDisplayed(String elemName, WebElement elem, WebDriver driver) {
		elem = getExplicitWait(elem, driver, 30);
		boolean bDisplayed = elem.isDisplayed();
		OutputAssert("CheckWebElementDisplayed - " + elemName + " Validation", new Callable<Void>() {
			public Void call() {
				Assert.assertTrue(bDisplayed);
				return null;
			}
		});
		return bDisplayed;
	}

	public static WebElement getExplicitWait(WebElement element, WebDriver driver, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(element));
		return element1;
	}
	
	/**
	 * public void verifyPage() { if (CheckWebElementDisplayed("Verify Tracking
	 * Number", getverifyTrackingNum(), driver)) {
	 * ExtentTestManager.getTest().log(LogStatus.INFO, "Verify Tracking Number: " +
	 * getverifyTrackingNum().getText()); } }
	 */

}