package com.usa.read.and.write.data;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	//Create a workbook
	//Create a sheet in workbook
	//Create a row in sheet
	//Add cells in sheet
	//Repeat step 3 and 4 to write more data
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("SmartTech Woner");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "ID", "NAME", "LASTNAME" });
		data.put("2", new Object[] { 101, "Mohammed", "Alam" });
		data.put("3", new Object[] { 202, "Ahmed", "Saroware" });
		data.put("4", new Object[] { 303, "Saidur", "Rahaman" });
		data.put("5", new Object[] { 404, "Abdus", "Subahan" });
		data.put("6", new Object[] { 505, "Rohul", "Amin" });

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			// this creates a new row in the sheet
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				// this line creates a cell in the next column of that row
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// this Writes the workbook Alam
			FileOutputStream out = new FileOutputStream(new File("Alam.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Alam.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
