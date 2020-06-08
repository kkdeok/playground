package com.doubleknd26.exercise.macro.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;


public class WebDriverWrapper {
	private static final Logger logger = LogManager.getLogger();
	private static final int RETRY_CNT = 2;

	private WebDriverWait waitDriver;
	private ChromeDriver driver;


	public WebDriverWrapper(String userAgent, boolean isHeadless) {
		this.driver = new ChromeDriver(getChromeOptions(userAgent, isHeadless));
		this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		this.driver.manage().deleteAllCookies();
		this.waitDriver = new WebDriverWait(driver, 1);
	}

	private ChromeOptions getChromeOptions(String userAgent, boolean isHeadless) {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(isHeadless);
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Arrays.asList(
				"enable-automation",
				"disable-gpu",
				"start-maximized"));
		if (isHeadless) {
			// override user-agent to avoid some access denied issue. Some
			// web site block the user-agent with HeadlessChrome.
			// https://stackoverflow.com/questions/54432980
			options.addArguments(String.format("user-agent=%s", userAgent));
		}
		return options;
	}

	public void quit() {
		driver.quit();
	}

	public void get(String url) {
		Consumer<String> task = driver::get;
		retry(task, url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public WebElement findElement(By by) {
		WebElement element = retry(ExpectedConditions.presenceOfElementLocated(by));
		if (element == null) {
			throw new RuntimeException("failed to find element: " + by);
		}
		return element;
	}

	public WebElement findElement(WebElement element, By by) {
		WebElement element1 = retry(ExpectedConditions.presenceOfNestedElementLocatedBy(element, by));
		if (element1 == null) {
			throw new RuntimeException("failed to find element:" + by);
		}
		return element1;
	}

	public WebElement findElement(WebElement element, By by, int retryCnt) {
		WebElement element1 = retry(ExpectedConditions.presenceOfNestedElementLocatedBy(element, by), retryCnt);
		if (element1 == null) {
			throw new RuntimeException("failed to find element:" + by);
		}
		return element1;
	}

	public List<WebElement> findElements(By by) {
		List<WebElement> elements = retry(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		if (elements == null) {
			throw new RuntimeException("failed to find elements: " + by);
		}
		return elements;
	}

	public WebElement findVisibleElement(By by) {
		WebElement element = retry(ExpectedConditions.visibilityOfElementLocated(by));
		if (element == null) {
			throw new RuntimeException("failed to find visible element: " + by);
		}
		return element;
	}

	public void sendKeyToElement(By by, String key) {
		WebElement element = retry(ExpectedConditions.visibilityOfElementLocated(by));
		Consumer<String> task = element::sendKeys;
		retry(task, key);
	}

	public WebElement findClickableElement(By by) {
		WebElement element = retry(ExpectedConditions.elementToBeClickable(by));
		if (element == null) {
			throw new RuntimeException("failed to find clickable element: " + by);
		}
		return element;
	}

	public WebElement findClickableElement(WebElement element, By by, int retryCnt) {
		WebElement clickElement = findElement(element, by, retryCnt);
		if (clickElement == null) {
			throw new RuntimeException("failed to find clickable element: " + by);
		}
		WebElement clickableElement = retry(ExpectedConditions.elementToBeClickable(clickElement), retryCnt);
		if (clickableElement == null) {
			throw new RuntimeException("failed to find clickable element: " + by);
		}
		return clickableElement;
	}

	/**
	 * use it when click makes new page load.
	 * should be clickable.
	 * @param element
	 */
	public void clickAndWait(WebElement element) {
		clickAndWait(element, 2);
	}

	public void clickAndWait(WebElement element, int seconds) {
		element.click();
		wait(seconds);
	}

	/**
	 * https://stackoverflow.com/questions/6521270/webdriver-check-if-an-element-exists
	 * @param element
	 * @param by
	 * @return
	 */
	public boolean isWebElementExists(WebElement element, By by) {
		return !element.findElements(by).isEmpty();
	}

	// https://stackoverflow.com/questions/12967541/how-to-avoid-staleelementreferenceexception-in-selenium
	// It is used to prevent StaleElementReferenceException.
	// TODO: Remove after making service stable without it.
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private <T> void retry(Consumer<T> task, T param) {
		for (int i = 0; i< RETRY_CNT; i++) {
			try {
				task.accept(param);
				break;
			} catch (Exception e) {
			}
		}
	}

	private <T, R> R retry(ExpectedCondition<R> condition) {
		return retry(condition, RETRY_CNT);
	}

	private <T, R> R retry(ExpectedCondition<R> condition, int retryCnt) {
		R response = null;
		for (int i = 0; i < retryCnt; i++) {
			try {
				response = waitDriver.until(condition);
				break;
			} catch (Exception e) {
			}
		}
		return response;
	}
}
