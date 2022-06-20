package com.build.qa.build.selenium.pageobjects.BathroomPage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

public class BathFaucetPage extends BasePage {

    @FindBy(xpath = "(//div[@class='ri-nav-ul-li-content']/p)[11]")
    private WebElement handShowersCategory;

    @FindBy(xpath = "(//div[@class='grid-image'])[2]")
    private WebElement singleHandleFaucet;

    public WebElement getHandShowersCategory() {
        return handShowersCategory;
    }

    public WebElement getSingleHandleFaucet() {
        return singleHandleFaucet;
    }

    public BathFaucetPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
}
