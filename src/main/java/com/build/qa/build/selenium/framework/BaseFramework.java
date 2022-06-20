package com.build.qa.build.selenium.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseFramework {
	protected WebDriver driver;
	protected Wait<WebDriver> wait;
	private static final Logger LOG = LoggerFactory.getLogger(BaseFramework.class);
	private static final String CONFIG_FILE = "./conf/automation.properties";
	private static final String DRIVER_FIREFOX = "firefox";
	private static final String DRIVER_CHROME = "chrome";
	private static Properties configuration;


	public final SoftAssert softly = new SoftAssert();

	@BeforeClass
	public static void beforeClass() throws IOException {
		configuration = new Properties();
		FileInputStream input;

		LOG.info("Loading in configuration file.");
		input = new FileInputStream(new File(CONFIG_FILE));
		configuration.loadFromXML(input);
		input.close();
	}

	@BeforeMethod
	public void setUpBefore() {
		DesiredCapabilities capabilities;
		// Which driver to use?
		if (DRIVER_CHROME.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			capabilities = DesiredCapabilities.chrome();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(capabilities);
		} else if (DRIVER_FIREFOX.equalsIgnoreCase(configuration.getProperty("BROWSER"))) {
			capabilities = DesiredCapabilities.firefox();
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(capabilities);
		}
		driver.manage().window().maximize();
		// Define fluent wait
		wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected String getConfiguration(String config) {
		return configuration.getProperty(config);
	}

	@AfterMethod
	public void tearDownAfter() {
		LOG.info("Quitting driver.");
		driver.quit();
		driver = null;
	}

	public  void waitFor(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//===============Explicit Wait==============//
	public  WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeToWaitInSec);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public  WebElement waitForVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public  WebElement waitForClickability(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public  WebElement waitForClickability(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public  void  waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
		}
	}
	public String getScreenshot(String name) throws IOException {
		// naming the screenshot with the current date to avoid duplication
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		// TakesScreenshot is an interface of selenium that takes the screenshot
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		// full path to the screenshot location
		String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
		File finalDestination = new File(target);
		// save the screenshot to the path given
		FileUtils.copyFile(source, finalDestination);
		return target;
	}

	public void flash( WebElement element, WebDriver driver){
			changeColor(element,driver);
	}

	public void changeColor(WebElement element, WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: pink; border:2px solid blue;');",element);
		try{
			Thread.sleep(100);
		}catch (InterruptedException e){
		}
	}

}
