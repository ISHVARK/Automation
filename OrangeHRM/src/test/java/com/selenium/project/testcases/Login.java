package com.selenium.project.testcases;

import java.awt.TextField;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.project.base.Base;
import com.selenium.project.pageobjects.LoginPage;

public class Login extends Base {
	LoginPage loginPage;
	protected Login() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver = InitializeBrowserAndOpenApplicationURL(prop.getProperty("Browser"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		loginPage = new LoginPage(driver);
	}

	@AfterMethod
	public void tearDorn() {
		//driver.quit();
		//driver.close();
		
		//Stagging 2
	}

	@Test
public void loginWithValidCredenetials() throws Exception{
		loginPage.login(dataProp.getProperty("Username"), dataProp.getProperty("Password"));
	
}
	
	@Test
public void loginWithInValidCredenetials() throws Exception{
		loginPage.login(dataProp.getProperty("Username"), dataProp.getProperty("invalidPassword"));
		Assert.assertEquals(loginPage.GetErrorMessage(), dataProp.getProperty("invalidCredentailsErrorMessage"));

	}
}
