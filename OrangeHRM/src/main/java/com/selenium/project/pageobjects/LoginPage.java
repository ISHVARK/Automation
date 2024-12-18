package com.selenium.project.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button")
	WebElement loginBtn;

	@FindBy(xpath = "//div[contains(@class, 'oxd-alert-content oxd-alert-content--error')]")
	WebElement errormsg;

	@FindBy(xpath = "//p[contains(@class, 'oxd-alert-content-text')]")
	WebElement Warningtext;

	public void login(String usernme, String passwod) throws Exception {
		userName.sendKeys(usernme);
		password.sendKeys(passwod);
		loginBtn.click();		
	}

	public String GetErrorMessage() {
		return errormsg.getText();
	}

	public boolean isErrorMsgDisplyed() {
		
		return Warningtext.isDisplayed();
	}

}
