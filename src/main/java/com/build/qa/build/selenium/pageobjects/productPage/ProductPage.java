package com.build.qa.build.selenium.pageobjects.productPage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@class='price__display clearfix']/span[2]")
    private WebElement chromeEachPrice;

    @FindBy(xpath = "//div[@class='price__display clearfix']/span[2]")
    private WebElement matteProductPrice;

    @FindBy(xpath = "//a/img[@title='Matte Black']")
    private WebElement matteBlackProduct;

    @FindBy(xpath = "//input[@data-productid='7797863']")
    private WebElement matteBlackAddToCart;

    @FindBy(xpath = "//img[@title='Chrome']")
    private WebElement chromeProduct;

    @FindBy(xpath = "//input[@data-productid='7203481']")
    private WebElement chromeAddToCart;

    @FindBy(xpath = "//h2[@itemprop='name']")
    private WebElement productBrand;

    @FindBy(xpath = "//span[@itemprop='productID']")
    private WebElement productID;

    public WebElement getChromeEachPrice() {
        return chromeEachPrice;
    }

    public WebElement getMatteProductPrice() {
        return matteProductPrice;
    }

    public WebElement getMatteBlackAddToCart() {
        return matteBlackAddToCart;
    }

    public WebElement getChromeAddToCart() {
        return chromeAddToCart;
    }

    public WebElement getMatteBlackProduct() {
        return matteBlackProduct;
    }

    public WebElement getChromeProduct() {
        return chromeProduct;
    }

    public WebElement getProductBrand() {
        return productBrand;
    }

    public WebElement getProductID() {
        return productID;
    }

    public ProductPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }
}
