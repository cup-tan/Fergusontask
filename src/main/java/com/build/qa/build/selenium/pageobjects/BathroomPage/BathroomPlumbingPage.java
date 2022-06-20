package com.build.qa.build.selenium.pageobjects.BathroomPage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

public class BathroomPlumbingPage extends BasePage {

    @FindBy(xpath = "//div[@class='ri-nav-ul-li-content']/p")
    private WebElement bathroomFaucetsCategory;

    public WebElement getBathroomFaucetsCategory() {
        return bathroomFaucetsCategory;
    }

    public BathroomPlumbingPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
}
