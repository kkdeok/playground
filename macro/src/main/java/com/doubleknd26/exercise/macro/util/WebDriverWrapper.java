package com.doubleknd26.exercise.macro.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * consider only chrome for now.
 */
public class WebDriverWrapper {
	private static final int RETRY_CNT = 2;
	public WebDriver driver;

	public WebDriverWrapper(ChromeOptions options) {
		this.driver = new ChromeDriver(options);
		initDriver();
	}

	private void initDriver() {
		this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().deleteAllCookies();
	}

	public void quit() {
		driver.quit();
	}

	public void get(String url) {
		Consumer<String> task = driver::get;
		retry(task, url);
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public WebElement findElement(By by) {
		Function<By, WebElement> task = driver::findElement;
		return retry(task, by);
	}

	public WebElement findElement(By by, int retryCnt) {
		Function<By, WebElement> task = driver::findElement;
		return retry(task, by, retryCnt);
	}

	public WebElement findElement(WebElement element, By by) {
		return findElement(element, by, RETRY_CNT);
	}

	public WebElement findElement(WebElement element, By by, int retryCnt) {
		Function<By, WebElement> task = element::findElement;
		return retry(task, by, retryCnt);
	}

	public List<WebElement> findElements(By by) {
		Function<By, List<WebElement>> task = driver::findElements;
		return retry(task, by);
	}

	public void sendKeyToElement(By by, String key) {
		WebElement element = findElement(by, 10);
		Consumer<String> task = element::sendKeys;
		retry(task, key);
	}

	public void clickElement(By by) {
		WebElement element = findElement(by);
		Consumer<WebElement> task = WebElement::click;
		retry(task, element);
	}

	public void clickElement(WebElement element) {
		Consumer<WebElement> task = WebElement::click;
		retry(task, element);
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

	private <T, R> R retry(Function<T, R> task, T param) {
		return retry(task, param, RETRY_CNT);
	}

	private <T, R> R retry(Function<T, R> task, T param, int retryCnt) {
		R response = null;
		for (int i = 0; i < retryCnt; i++) {
			try {
				response = task.apply(param);
				break;
			} catch (Exception e) {
			}
		}
		return response;
	}
}
