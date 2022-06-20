package com.build.qa.build.selenium.pageobjects.shoppingCart;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

public class ShoppingCart extends BasePage {

    @FindBy(xpath = "(//div[@class='os-total table']/span)[2]")
    private WebElement subTotalPrice;

    @FindBy(xpath = "(//span[@class='f-bold'])[3]")
    private WebElement matteTotalPrice;

    @FindBy(xpath = "//div[@class='total-price']/span")
    private WebElement chromeTotalPrice;

    @FindBy(xpath = "(//div[@class='quantity__up quantity__button jq-qty-up'])[1]")
    private WebElement chromeIncreaseQuantityButton;

    @FindBy(xpath = "(//div[@class='quantity__down quantity__button jq-qty-down disabled'])[1]")
    private WebElement chromeDecreaseQuantityButton;

    @FindBy(xpath = "(//div[@class='quantity__up quantity__button jq-qty-up'])[2]")
    private  WebElement matteIncreaseQuantityButton;

    @FindBy(xpath = "(//div[@class='quantity__down quantity__button jq-qty-down disabled'])[2]")
    private  WebElement matteDecreaseQuantityButton;

    @FindBy(xpath = "//*[@id=\"item-datas\"]/li[1]/div[3]/div[3]/div[2]/div[1]/div[1]")
    private WebElement chromeQuantityButton;

    @FindBy(xpath = "(//div[@class='quantityBtn'])[2]")
    private WebElement matteQuantityButton;

    @FindBy(xpath = "//div[@class='cl-summary']//div[2]/p")
    private WebElement secondProductPartNumber;

    public WebElement getSubTotalPrice() {
        return subTotalPrice;
    }

    public WebElement getMatteTotalPrice() {
        return matteTotalPrice;
    }

    public WebElement getChromeTotalPrice() {
        return chromeTotalPrice;
    }

    public WebElement getMatteIncreaseQuantityButton() {
        return matteIncreaseQuantityButton;
    }

    public WebElement getChromeIncreaseQuantityButton() {
        return chromeIncreaseQuantityButton;
    }

    public WebElement getSecondProductPartNumber() {
        return secondProductPartNumber;
    }

    public ShoppingCart(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
}
