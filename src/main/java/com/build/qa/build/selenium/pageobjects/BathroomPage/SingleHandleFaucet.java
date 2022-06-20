package com.build.qa.build.selenium.pageobjects.BathroomPage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

public class SingleHandleFaucet extends BasePage {

    @FindBy(xpath = "//a[@data-fastcode='Part #PLG1420600']/span")
    private WebElement secondProductPartNumber;

    @FindBy(xpath = "//p[@data-productid=',7289400']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[@data-productid='7289400']")
    private WebElement secondAddToCartButton;

    public WebElement getSecondAddToCartButton() {
        return secondAddToCartButton;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public WebElement getSecondProductPartNumber() {
        return secondProductPartNumber;
    }

    public SingleHandleFaucet(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
}
