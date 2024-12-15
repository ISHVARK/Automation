package com.selenium.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtendReport() {
		ExtentReports extentReport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"//test-output//extentReports//extentReport.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Optimiser CRM Automation Tests Report.");
		sparkReporter.config().setDocumentTitle("OptimiserCRM Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);

		Properties prop = new Properties();

		try {
			FileInputStream fin = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\selenium\\project\\config\\config.properties");
			prop.load(fin);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		extentReport.setSystemInfo("Application URL", prop.getProperty("BaseURL"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browserName"));
		extentReport.setSystemInfo("Email", prop.getProperty("Username"));
		extentReport.setSystemInfo("Password", prop.getProperty("Password"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("java.version"));
		
		return extentReport;
		
	}
}
