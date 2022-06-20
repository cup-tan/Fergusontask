package com.build.qa.build.selenium.pageobjects;

import com.build.qa.build.selenium.framework.BaseFramework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public abstract class BasePage extends BaseFramework {
	
	public BasePage(WebDriver driver, Wait<WebDriver> wait) { 
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
}
