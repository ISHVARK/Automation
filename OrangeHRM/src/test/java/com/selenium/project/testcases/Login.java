package com.selenium.project.testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.project.base.Base;

public class Login extends Base {
	protected Login() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver = InitializeBrowserAndOpenApplicationURL(prop.getProperty("Browser"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}

	@AfterMethod
	public void tearDorn() {
		driver.quit();
		//driver.close();
	}

	@Test
public void loginWithValidCredenetials() {
	
}
}
