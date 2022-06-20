package com.build.qa.build.selenium.pageobjects.homepage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class HomePage extends BasePage {

	private By homePageWrapper;

	@FindBy(xpath = "//li[@class='cart i-cart']")
	private WebElement cartModule;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchText;

	public WebElement getCartModule() {return cartModule;}

	public WebElement getSearchText() {return searchText;}

	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		homePageWrapper = By.cssSelector("#wrapper.homepage");}
	
	public boolean onHomePage() {return wait.until(ExpectedConditions.presenceOfElementLocated(homePageWrapper)) != null;}
}
