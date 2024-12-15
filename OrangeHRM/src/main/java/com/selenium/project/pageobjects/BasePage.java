package com.selenium.project.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class BasePage {
	protected WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Explicit wait for an element to be visible
	public void waitForElementVisibility(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	
	public void waitForElementVisibility(By locator,int duation) {
		new WebDriverWait(driver, Duration.ofSeconds(duation)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Explicit wait for an element to be clickable
	public void waitForElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void handledropdown(WebElement webel, String... val) {
		Select s = new Select(webel);
		List<WebElement> options = s.getOptions();
		for (WebElement op : options) {
			if (op.getText().equals(val)) {
				op.click();
				break;
			}
		}
	}

	public static void handledropdown(WebElement webel, String value) {
		Select select = new Select(webel);
		select.selectByVisibleText(value);
	}

	public static void handlejqdropdown(WebDriver driver, By ele, String... value) {
		List<WebElement> choices = driver.findElements(ele);
		if (!value[0].equalsIgnoreCase("all")) {
			for (WebElement choice : choices) {
				String text = choice.getText();
				for (String val : value) {
					if (text.equals(val)) {
						choice.click();
						break;
					}
				}
			}
		} else {
			for (WebElement choice : choices) {
				choice.click();
			}
		}

	}

	public boolean SearchRecord(List<WebElement> list, String value) {
		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase(value)) {
				System.out.println("Record Match.");
				return true;
			} else {
				continue;
			}
		}
		return false;
	}
}
