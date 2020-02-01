package com.usa.read.and.write.data;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;

//How to read excel files using Apache POI

//Create workbook instance from excel sheet
//Get to the desired sheet
//Increment row number
//iterate over all cells in a row
//repeat step 3 and 4 until all data is read

public class ReadExcel {
	
	@SuppressWarnings("resource")
	public static void readData(int colno) throws Throwable {
		FileInputStream file = new FileInputStream("./PMCTestData/TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("testData");
		Iterator<Row> rowiterator = sheet.iterator();
		rowiterator.next();
		ArrayList<String> list = new ArrayList<String>();
		while (rowiterator.hasNext()) {
			list.add(rowiterator.next().getCell(colno).getStringCellValue());
		}  //readData(0); or 1
	}
	
	
	@SuppressWarnings({ "deprecation", "resource" })
	public static void main(String[] args) {
		try {
			FileInputStream file = new FileInputStream(new File("./PMCTestData/TestData.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + " ");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + " ");
						break;
					}
				}
				System.out.println(" ");
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
