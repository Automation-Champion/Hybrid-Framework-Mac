package com.usa.login.functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelandTestNGDatapProviderLogin {
	static Workbook book;
	public static WebDriver driver;
	public static String TESTDATA_SHEET_PATH1 = "/PMCTestData/TestData.xlsx";
	static Sheet sheet;
	String sheetName = "testData";

	public static Object[][] getTestData(String sheetName) throws FileNotFoundException {
		FileInputStream file = null;
		String workingDir = System.getProperty("user.dir");
		System.out.println(workingDir + TESTDATA_SHEET_PATH1);
		file = new FileInputStream(workingDir + TESTDATA_SHEET_PATH1);
		try {
			System.out.println("WorkbookFactory create " + file);
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			System.out.println("UtilTest: WorkbookFactory create failed " + e.getMessage());
			e.printStackTrace();
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

	@DataProvider
	public Object[][] getExcelTestData() throws FileNotFoundException {
		Object data[][] = getTestData(sheetName);
		return data;
	}

	public void elementOfWebpage(String emeil, String password) {

		// How to send key using Action class
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@id='signin_email']"));
		String text = emeil;
		actions.sendKeys(element, text).perform();

		// How to send key using Java script Executor class
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement elem = driver.findElement(By.xpath("//*[@id='signin_password']"));
		js.executeScript("arguments[0].setAttribute('value', '" + password + "')", elem);
	}

	@Test(dataProvider = "getExcelTestData")
	public void loginTest(String UserName, String Password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/mohammedalam/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.zoopla.co.uk/signin/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		elementOfWebpage(UserName, Password);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='signin_submit']")).click();
		driver.quit();
	}

}
