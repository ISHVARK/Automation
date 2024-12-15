package com.selenium.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	public static final Duration Implicit_Wait_Time = Duration.ofSeconds(10);
	public static final Duration Page_Wait_Time = Duration.ofSeconds(15);

	public static Object[][] getTestDataFromExcel(String Sheetname,String FilePath) {
		XSSFWorkbook workbook = null;
		File xlsFile = new File(
				System.getProperty("user.dir") + FilePath);
		try {

			FileInputStream fis = new FileInputStream(xlsFile);
			workbook = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(Sheetname);
		int Rowcount = sheet.getLastRowNum();
		int Colcount = sheet.getRow(0).getLastCellNum();
		System.out.println("rowcnt  :- " + Rowcount + " colcnt :- " + Colcount);
		Object[][] data = new Object[Rowcount][Colcount];
		for (int i = 0; i < Rowcount; i++) {
			XSSFRow row = sheet.getRow(i+1);
			for (int j = 0; j < Colcount; j++) {
				XSSFCell cell = row.getCell(j);
				switch (cell.getCellType()) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = cell.getNumericCellValue();
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					break;
				}

			}
		}
		return data;

	}
	public static String getScreenShot(WebDriver driver,String TestName)
	{
		File SrcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String DestFile = System.getProperty("user.dir") + "\\Screenshots\\" + TestName + ".png";
		try {
			FileHandler.copy(SrcFile, new File(DestFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DestFile;
	}
}
