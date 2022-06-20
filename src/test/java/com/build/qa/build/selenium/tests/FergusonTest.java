package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.BathroomPage.BathFaucetPage;
import com.build.qa.build.selenium.pageobjects.BathroomPage.BathroomPlumbingPage;
import com.build.qa.build.selenium.pageobjects.BathroomPage.HandShowersPage;
import com.build.qa.build.selenium.pageobjects.BathroomPage.SingleHandleFaucet;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import com.build.qa.build.selenium.pageobjects.productPage.ProductPage;
import com.build.qa.build.selenium.pageobjects.shoppingCart.ShoppingCart;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class FergusonTest extends BaseFramework {
    private static final Logger LOG = LoggerFactory.getLogger(FergusonTest.class);
    HomePage homePage;
    ProductPage productPage;
    BathFaucetPage faucentPage;
    SingleHandleFaucet singleHandleFaucent;
    ShoppingCart shoppingCart;
    BathFaucetPage bathFaucetPage;
    BathroomPlumbingPage bathroomPlumbingPage;
    HandShowersPage handShowersPage;

    /**
     * Extremely basic test that outlines some basic
     * functionality and page objects as well as assertJ
     */
    @Test
    public void Stage1_navigateToHomePage() {
        driver.get(getConfiguration("HOMEPAGE"));
        homePage = new HomePage(driver, wait);

        softly.assertTrue(homePage.onHomePage(),"The website should load up with the Build.com desktop theme.");


    }

    /**
     * Search for the Moen m6702bn from the search bar
     *
     * @assert: That the product page we land on is what is expected by checking the product brand and product id
     * @difficulty Easy
     */
    @Test
    public void Stage2_searchForProductLandsOnCorrectProduct() {
        driver.get(getConfiguration("HOMEPAGE"));
        homePage = new HomePage(driver, wait);
        productPage = new ProductPage(driver, wait);

        flash(homePage.getSearchText(),driver);
        homePage.getSearchText().sendKeys("Moen m6702bn" + Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@itemprop='name']")));
        String expectedProductBrand = "Moen";
        String actualProductBrand = productPage.getProductBrand().getText();
        System.out.println("actualProductBrand = " + actualProductBrand);
        softly.assertEquals(actualProductBrand,expectedProductBrand);
        String expectedProductID = "Part #M6702BN";
        String actualProductID = productPage.getProductID().getText();
        System.out.println("actualProductID = " + actualProductID);
        softly.assertEquals(actualProductID,expectedProductID);

    }

    /**
     * Go to the Bathroom Sinks category directly
     * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
     * click the second product(single handle category) on the opened page
     * and add the second product on the search results (Category Drop) page to the cart.
     *
     * @assert: the product that is added to the cart is what is expected
     * @difficulty Easy-Medium
     */
    @Test
    public void Stage3_addProductToCartFromCategoryDrop() {
        driver.get("https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3");
        homePage = new HomePage(driver, wait);
        faucentPage = new BathFaucetPage(driver, wait);
        singleHandleFaucent = new SingleHandleFaucet(driver, wait);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(300, 300)");
        waitForVisibility(faucentPage.getSingleHandleFaucet(),3);
        flash(faucentPage.getSingleHandleFaucet(),driver);
        faucentPage.getSingleHandleFaucet().click();
        js.executeScript("window.scrollBy(200, 200)");
        String expectedProductPartNumber = singleHandleFaucent.getSecondProductPartNumber().getText();
        waitFor(2);
        flash(singleHandleFaucent.getAddToCartButton(),driver);
        singleHandleFaucent.getAddToCartButton().click();
        waitFor(2);
        flash(singleHandleFaucent.getSecondAddToCartButton(),driver);
        singleHandleFaucent.getSecondAddToCartButton().click();
        waitForClickability(homePage.getCartModule(),5);
        flash(homePage.getCartModule(),driver);
        homePage.getCartModule().click();
        waitForPageToLoad(3);
        js.executeScript("window.scrollBy(100, 100)");
        shoppingCart = new ShoppingCart(driver, wait);
        waitFor(5);
        String actualProductPartNumber = shoppingCart.getSecondProductPartNumber().getText();
        softly.assertEquals(actualProductPartNumber,expectedProductPartNumber);
        waitFor(2);
    }

    /**
     * Add two different finishes of a product (such as Moen m6702bn) to cart,
     * change the quantity of each finish on the cart page
     *
     * @assert that the product and cart total update as expected when the quantity is changed
     * @difficulty Medium-Hard
     */
    @Test
    public void Stage4_addMultipleCartItemsAndChangeQuantity() throws InterruptedException {
        driver.get(getConfiguration("HOMEPAGE"));
        homePage = new HomePage(driver, wait);
        productPage = new ProductPage(driver, wait);
        shoppingCart = new ShoppingCart(driver, wait);

        flash(homePage.getSearchText(),driver);
        homePage.getSearchText().sendKeys("Moen m6702bn" + Keys.ENTER);
        waitFor(5);

        try {
            Thread.sleep(1000);
            productPage.getMatteBlackProduct().click();
        } catch (WebDriverException e) {
            productPage.getMatteBlackProduct().click();
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        flash(productPage.getMatteBlackAddToCart(),driver);
        productPage.getMatteBlackAddToCart().click();
        String matteSinglePrice = productPage.getMatteProductPrice().getText();
        flash(productPage.getChromeProduct(),driver);
        productPage.getChromeProduct().click();
        waitForPageToLoad(6);
        String chromeSinglePrice = productPage.getChromeEachPrice().getText();
        flash(productPage.getChromeAddToCart(),driver);
        productPage.getChromeAddToCart().click();
        flash(homePage.getCartModule(),driver);
        homePage.getCartModule().click();
        waitFor(5);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(100, 100)");
        Actions actions = new Actions(getDriver());
        WebElement chromeQuantity = driver.findElement(By.xpath("(//div[@class='quantityBtn'])[1]"));
        flash(chromeQuantity,driver);
        actions.moveToElement(chromeQuantity).perform();


        int chromeProductCount = 4;
        double chromePrice = Double.parseDouble(chromeSinglePrice);
        double expectedResult = chromePrice * chromeProductCount;
        LOG.info("expectedResult = " + expectedResult);

        waitFor(5);
        for (int i = 1; i < chromeProductCount; i++) { // add 4 chrome finish
         waitFor(1);
          flash(shoppingCart.getChromeIncreaseQuantityButton(),driver);
            shoppingCart.getChromeIncreaseQuantityButton().click();
        }
        waitFor(5);

        String fourChromePrice = shoppingCart.getChromeTotalPrice().getText();
        fourChromePrice = fourChromePrice.replace("$", "");
        double actualResult = Double.parseDouble(fourChromePrice);
        LOG.info("actualResult = " + actualResult);
        softly.assertEquals(actualResult,expectedResult);

        LOG.info("Scrolling down to matte faucet.");
        js.executeScript("window.scrollBy(75, 75)");
        waitFor(5);
        WebElement matteQuantity = driver.findElement(By.xpath("(//div[@class='quantityBtn'])[2]"));
        flash(matteQuantity,driver);
        actions.moveToElement(matteQuantity).perform();

        int matteProductCount = 3;
        double mattePrice = Double.parseDouble(matteSinglePrice);
        double expectedResult2 = mattePrice * matteProductCount;
        LOG.info("expectedResult2 = " + expectedResult2);
        waitFor(5);
        for (int i = 1; i < matteProductCount; i++) { // add 3 chrome finish
            waitFor(1);
            flash(shoppingCart.getMatteIncreaseQuantityButton(),driver);
            shoppingCart.getMatteIncreaseQuantityButton().click();
        }

        waitFor(5);
        String threeChromePrice = shoppingCart.getMatteTotalPrice().getText();
        threeChromePrice = threeChromePrice.replace("$", "");
        double actualResult2 = Double.parseDouble(threeChromePrice);
        LOG.info("actualResult2 = " + actualResult2);

        softly.assertEquals(actualResult,expectedResult);

        double expectedTotalPrice = actualResult + actualResult2;
        LOG.info("ExpectedTotalPrice is: " + expectedTotalPrice);

        flash(shoppingCart.getSubTotalPrice(),driver);
        String totalPrice = shoppingCart.getSubTotalPrice().getText();
        waitFor(2);
        System.out.println("totalPrice = " + totalPrice);
        totalPrice = totalPrice.replace("$", "");
        totalPrice = totalPrice.replace(",", "");
        System.out.println("totalPrice = " + totalPrice);
        double actualTotalPrice = Double.parseDouble(totalPrice);
        LOG.info("ActualTotalPrice: " + actualTotalPrice);
        softly.assertEquals(actualTotalPrice,expectedTotalPrice);
        waitFor(5);
    }


    /**
     * Go to a category drop page (such as Bathroom Faucets) and narrow by
     * at least two filters (facets), e.g: Finish=Chromes and Brand=Brizo
     *
     * @assert that the correct filters are being narrowed, and the result count
     * is correct, such that each facet selection is narrowing the product count.
     * @difficulty Hard
     */
    @Test
    public void Stage5_facetNarrowBysResultInCorrectProductCounts() {
        driver.get("https://www.ferguson.com/category/bathroom-plumbing/_/N-zbq2v9?sr=everywhere");
        homePage = new HomePage(driver, wait);
        bathFaucetPage = new BathFaucetPage(driver, wait);
        bathroomPlumbingPage = new BathroomPlumbingPage(driver, wait);
        handShowersPage = new HandShowersPage(driver, wait);

        waitFor(2);
        flash(bathroomPlumbingPage.getBathroomFaucetsCategory(),driver);
        bathroomPlumbingPage.getBathroomFaucetsCategory().click();
        waitFor(2);
        flash(bathFaucetPage.getHandShowersCategory(),driver);
        bathFaucetPage.getHandShowersCategory().click();
        waitFor(5);

        handShowersPage.getBrandList();
        waitFor(2);
        WebElement result = handShowersPage.getTotalCountHandShowers();
        System.out.println("result.getText()1 = " + result.getText());
        String actualTotalCount = result.getText().substring(0, result.getText().indexOf(" "));
        System.out.println("actualTotalCount = " + actualTotalCount);
        waitFor(3);
        try {
            for (WebElement brand : handShowersPage.getBrandList()) {
                if (brand.getText().contains("Delta")) {
                    flash(brand,driver);
                    brand.click();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitFor(5);
        flash(handShowersPage.getHandShowersCountResultText(),driver);
        result = handShowersPage.getHandShowersCountResultText();
        System.out.println("result.getText()2 = " + result.getText());
        String actualTotalCountAfterFilter = result.getText().substring(0, result.getText().indexOf(" "));

        waitFor(2);
        WebElement deltaCount = handShowersPage.getDeltaCount();

        System.out.println("deltaCount = " + deltaCount.getText());
        String expectedTotalDelta = deltaCount.getText().substring(deltaCount.getText().indexOf("(") + 1, deltaCount.getText().indexOf(")"));
        System.out.println("Integer.parseInt(expectedTotalDelta) = " + Integer.parseInt(expectedTotalDelta));
        System.out.println("Integer.parseInt(actualTotalCountAfterFilter) = " + Integer.parseInt(actualTotalCountAfterFilter));
        softly.assertTrue(Integer.parseInt(expectedTotalDelta) < Integer.parseInt(actualTotalCountAfterFilter));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(500, 500)");
        waitFor(5);
        handShowersPage.getCategoryList();
        try {
            for (WebElement eachFinish : handShowersPage.getCategoryList()) {
                if (eachFinish.getText().contains("Gold")) {
                    System.out.println("eachFinish = " + eachFinish.getText());
                    flash(eachFinish,driver);
                    eachFinish.click();

                }
                waitFor(3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        flash(handShowersPage.getTotalCountHandShowers(),driver);
        waitFor(2);
        result = handShowersPage.getTotalCountHandShowers();
        System.out.println("result.getText()3 = " + result.getText());
        String actualTotalCountAfterSecondFilter = result.getText().substring(0, result.getText().indexOf(" "));
        System.out.println("actualTotalCountAfterSecondFilter = " + actualTotalCountAfterSecondFilter);
    }
}
