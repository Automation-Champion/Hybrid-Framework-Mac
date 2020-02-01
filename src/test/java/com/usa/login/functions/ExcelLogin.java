package com.usa.login.functions;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ExcelLogin {
	public WebDriver driver;
	public String testData = "/Users/mohammedalam/eclipse-workspace/Hybrid-Framework/PMCTestData/TestData.xlsx";

	@Test
	public void loginZoplaWithExcelData() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "/Users/mohammedalam/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.zoopla.co.uk/signin/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		ArrayList<String> userName = readExceldata(0);
		ArrayList<String> password = readExceldata(1);
		for (int i = 0; i < userName.size(); i++) {
			driver.findElement(By.xpath("//*[@id='signin_email']")).sendKeys(userName.get(i));
			driver.findElement(By.xpath("//*[@id='signin_password']")).sendKeys(password.get(i));
			driver.findElement(By.xpath("//*[@id='signin_submit']")).click();
			Thread.sleep(50000);
			driver.close();
			driver.quit();

		}
	}

	@SuppressWarnings("resource")
	public ArrayList<String> readExceldata(int colno) throws Throwable {
		FileInputStream file = new FileInputStream(testData);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("testData");
		Iterator<Row> rowiterator = sheet.iterator();
		rowiterator.next();
		ArrayList<String> list = new ArrayList<String>();
		while (rowiterator.hasNext()) {
			list.add(rowiterator.next().getCell(colno).getStringCellValue());
		}
		System.out.println("List ::: " + list);
		return list;

	}
}
