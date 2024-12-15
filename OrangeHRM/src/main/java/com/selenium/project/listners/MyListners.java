package com.selenium.project.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListners implements ITestListener{
	public static ExtentReports extentReporter;
	public static ExtentTest extentTest;

	public void onStart(ITestContext context) {
		extentReporter=com.selenium.project.utils.ExtentReporter.generateExtendReport();
		System.out.println("Execution of the Project Tests Started.");
	}
	public void onTestStart(ITestResult result) {
		extentTest=extentReporter.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+" Started Executing.");
		System.out.println(result.getName()+" Started Executing.");
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+" Successfully Executed.");
		System.out.println(result.getName()+" Successfully Executed.");
	}

	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, result.getName()+" Got Failed.");
		extentTest.log(Status.INFO, result.getThrowable());

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {

		} catch (SecurityException e) {
			e.printStackTrace();
		}

		String DestFile = com.selenium.project.utils.Utilities.getScreenShot(driver,  result.getName());

		extentTest.addScreenCaptureFromPath(DestFile);
		System.out.println(result.getName()+" Got Failed.");
		System.out.println(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, result.getName() + " Got Skipped");
		extentTest.log(Status.INFO, result.getThrowable());
		System.out.println(result.getName()+" Got Skipped.");
		System.out.println(result.getThrowable());
	}

	

	public void onFinish(ITestContext context) {
		System.out.println("Finished Executing the Project Tests.");
		extentReporter.flush();
		String FilePathOfExtentReport= System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
		File extentreport=new File(FilePathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
