package com.selenium.project.testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.selenium.project.base.Base;
import com.selenium.project.pageobjects.DashboardPage;
import com.selenium.project.pageobjects.LoginPage;

public class Login extends Base {
	LoginPage loginPage;
	String LoginFilePath = "//src//main//java//com//Selenium//Project//testdata//Login.xlsx";

	protected Login() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;
	DashboardPage dashboard;

	@BeforeMethod
	public void setup() {
		driver = InitializeBrowserAndOpenApplicationURL(prop.getProperty("Browser"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		loginPage = new LoginPage(driver);
		dashboard = new DashboardPage(driver);
	}

	@AfterMethod
	public void tearDorn() {
		driver.quit();
		// driver.close();
	}

	@DataProvider(name = "TestData")
	public Object[][] SupplyData() {
		Object[][] data = com.selenium.project.utils.Utilities.getTestDataFromExcel("login", LoginFilePath);
		System.out.println(data);
		return data;
	}

	@Test(dataProvider = "TestData")
	public void loginWithDataDriven(String username, String password) throws Exception {
		loginPage.login(username, password);
		Assert.assertTrue(dashboard.IsUserNavigatedToDashboard(), "User has navigated to dashboard.");

	}

	@Test
	public void loginWithValidCredenetials() throws Exception {
		loginPage.login(dataProp.getProperty("Username"), dataProp.getProperty("Password"));
		Assert.assertTrue(dashboard.IsUserNavigatedToDashboard(), "User has navigated to dashboard.");

	}

	@Test
	public void loginWithInValidCredenetials() throws Exception {
		loginPage.login(dataProp.getProperty("invalidUsername"), dataProp.getProperty("invalidPassword"));
		Assert.assertEquals(loginPage.GetErrorMessage(), dataProp.getProperty("invalidCredentailsErrorMessage"),
				"User has displayed a Warning Text due to invalid Credentials");
	}

	@Test
	public void loginWithInValidUsernameAndValidPassword() throws Exception {
		loginPage.login(dataProp.getProperty("invalidUsername"), dataProp.getProperty("Password"));
		Assert.assertEquals(loginPage.GetErrorMessage(), dataProp.getProperty("invalidCredentailsErrorMessage"),
				"User has displayed a Warning Text due to invalid Username.");
	}

	@Test
	public void loginWithValidUsernameAndInvalidPassword() throws Exception {
		loginPage.login(dataProp.getProperty("Username"), dataProp.getProperty("invalidPassword"));
		Assert.assertEquals(loginPage.GetErrorMessage(), dataProp.getProperty("invalidCredentailsErrorMessage"),
				"User has displayed a Warning Text due to invalid Password.");
	}

	@Test
	public void loginWithoutProvidingCredentials() throws Exception {
		loginPage.login("", "");
		if (loginPage.GetUsernameRequiredWarning().equals(loginPage.GetPasswordRequiredWarning())) {
			Assert.assertEquals(loginPage.GetUsernameRequiredWarning(), dataProp.getProperty("RequiredWarningText"),
					"User has displayed a Username and Password is Required Warning.");
		} else {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void loginWithoutProvidingUsername() throws Exception {
		loginPage.login("", dataProp.getProperty("Password"));
		Assert.assertEquals(loginPage.GetUsernameRequiredWarning(), dataProp.getProperty("RequiredWarningText"),
				"User has displayed a Username is Required Warning.");

	}

	@Test
	public void loginWithoutProvidingPassword() throws Exception {
		loginPage.login(dataProp.getProperty("Username"), "");
		Assert.assertEquals(loginPage.GetPasswordRequiredWarning(), dataProp.getProperty("RequiredWarningText"),
				"User has displayed a Username and Password is Required Warning.");
	}
}
