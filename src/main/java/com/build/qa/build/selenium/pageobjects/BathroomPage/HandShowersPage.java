package com.build.qa.build.selenium.pageobjects.BathroomPage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class HandShowersPage extends BasePage {

    @FindBy(xpath = "(//ul[@class='ri-nav-ul'])[1]/li")
    private List<WebElement> brandList;

    @FindBy(xpath = "//div[@class='word total-record']")
    private WebElement totalCountHandShowers;

    @FindBy(xpath = "//div[@class='word total-record']")
    private WebElement handShowersCountResultText;

    @FindBy(xpath = "//div[@class='ri-nav-ul-li-content']")
    private WebElement deltaCount;

    @FindBy(xpath = "(//ul[@class='ri-nav-ul'])[2]/li")
    private List<WebElement> categoryList;

    public List<WebElement> getCategoryList() {
        return categoryList;
    }

    public WebElement getDeltaCount() {
        return deltaCount;
    }

    public WebElement getHandShowersCountResultText() {
        return handShowersCountResultText;
    }

    public List<WebElement> getBrandList() {return brandList;}

    public WebElement getTotalCountHandShowers() {
        return totalCountHandShowers;
    }

    public HandShowersPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
}
