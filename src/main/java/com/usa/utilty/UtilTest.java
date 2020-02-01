package com.usa.utilty;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxml4j.exceptions.InvalidFormatException;

import com.usa.basepage.SupperClass;

public class UtilTest extends SupperClass {
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 15;
	// get Excel Data first method
	public static String TESTDATA_SHEET_PATH1 = "/PMCTestData/TestData.xlsx";

	static Workbook book;

	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, InvalidFormatException {
		FileInputStream file = null;
		try {
			String workingDir = System.getProperty("user.dir");
			System.out.println(workingDir + TESTDATA_SHEET_PATH1);
			file = new FileInputStream(workingDir + TESTDATA_SHEET_PATH1);
		} catch (FileNotFoundException e) {
			System.out.println("UtilTest: FileInputStream failed " + e.getMessage());
			e.printStackTrace();
		}
		try {
			System.out.println("WorkbookFactory create " + file);
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			System.out.println("UtilTest: WorkbookFactory create failed " + e.getMessage());
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() + "--------" + sheet.getRow(0).getLastCellNum());

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				try {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				} catch (Exception e) {
					data[i][k] = "";
				}
				System.out.println(data[i][k]);
			}
		}
		System.out.println("data returning");
		return data;
	}

	public static String captureScreenShot(WebDriver driver, String ScreenShotName) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();
		String dateTime = dateFormat.format(date.getTime());
		String destination = currentDir + "//Screen Shot//" + dateTime + "//" + ScreenShotName + ".png";
		FileUtils.copyFile(source, new File(destination));
		System.out.println("Screen shot taken");
		return destination;
	}

	public static WebElement getExplicitWait(WebElement element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(element));
		return element1;
	}

	public static void PrintMethod() throws Exception {
		Robot robot = new Robot();
		// Press Enter Key
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		// Write Print Copy Name
		String text = "PrintCopy";
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);

		Thread.sleep(3000);

		// Enter Print Copy Name
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		// Press Enter Key for save print copy
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public static void WindowHandle() throws Throwable {
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			System.out.println(driver.getTitle());
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);

				// Closing the Child Window.
				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("MM.dd.yyyy-hh.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static Actions getAction() {
		Actions action = new Actions(driver);
		return action;
	}

	public static void customClick(WebDriver driver, By by) {
		try {
			(new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(by));
			driver.findElement(by).click();
		} catch (StaleElementReferenceException sere) {
			// simply retry finding the element in the refreshed DOM
			driver.findElement(by).click();
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void customWait() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				// Wait for the condition
				.withTimeout(60, TimeUnit.SECONDS)
				// which to check for the condition with interval of 5 seconds.
				.pollingEvery(5, TimeUnit.SECONDS)
				// Which will ignore the NoSuchElementException
				.ignoring(NoSuchElementException.class);
	}

	public static boolean retryingFindClick(By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(by).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public static void waitThenClick(WebDriver driver, WebElement element) {
		try {
			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).pollingEvery(10, TimeUnit.SECONDS)
					.withTimeout(120, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class)
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

	public static void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	public static void scrollView(String Element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	public static void jsClick(String locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", locator);
	}

	public static String threeDaysBefore() {
		String threeDaysBefore = "";
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, -3);
		Date before = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		threeDaysBefore = formatter.format(before);
		return threeDaysBefore;
	}

	public static WebElement getWebElClickable(String xpath, int waitSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait = new WebDriverWait(driver, waitSeconds);
		return wait.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath(xpath))));
	}

	public boolean isElementPresent(WebElement element, WebDriver driver, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void datePickers(String num) {
		UtilTest.customClick(driver, By.xpath("//*[@id='dateSection']/descendant::input[1]"));
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
