package com.selenium.project.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage{
	WebDriver driver;
	WebDriverWait wait;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//h6[text()=\"Dashboard\"]")
	WebElement bashboard;
	
	public boolean IsUserNavigatedToDashboard() {
		return bashboard.isDisplayed();
		
	}

}
