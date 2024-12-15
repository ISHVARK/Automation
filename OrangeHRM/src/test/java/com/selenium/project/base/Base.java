package com.selenium.project.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public Properties prop;
	public Properties dataProp;
	
	protected Base() throws Exception {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\selenium\\project\\config\\config.properties");
		FileInputStream fin = new FileInputStream(file);
		System.out.println(fin);
		prop = new Properties();
		prop.load(fin);
		
		dataProp = new Properties();
		File datafile = new File(System.getProperty("user.dir")
				+ "//src//main//java//com//selenium//project//testdata//testdata.properties");
		try{
			FileInputStream fis = new FileInputStream(datafile);
		dataProp.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver InitializeBrowserAndOpenApplicationURL(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			//options.addArguments("user-data-dir=C:\\Users\\ishva\\AppData\\Local\\Google\\Chrome\\User Data\\");
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.get(prop.getProperty("BaseURL"));
		return driver;
	}


}
