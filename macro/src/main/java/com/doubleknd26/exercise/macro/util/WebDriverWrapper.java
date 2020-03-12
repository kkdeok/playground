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
	private static final int RETRY = 2;
	private WebDriver driver;

	public WebDriverWrapper(ChromeOptions options) {
		this.driver = new ChromeDriver(options);
		initDriver();
	}

	private void initDriver() {
		this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
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

	public WebElement findElement(WebElement element, By by) {
		Function<By, WebElement> task = element::findElement;
		return retry(task, by);
	}

	public List<WebElement> findElements(By by) {
		Function<By, List<WebElement>> task = driver::findElements;
		return retry(task, by);
	}

	public void sendKeyToElement(By by, String key) {
		WebElement element = findElement(by);
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

	private <T> void retry(Consumer<T> task, T param) {
		for (int i=0 ; i<RETRY ; i++) {
			try {
				task.accept(param);
				break;
			} catch (Exception e) {
			}
		}
	}

	private <T, R> R retry(Function<T, R> task, T param) {
		R response = null;
		for (int i=0 ; i<RETRY ; i++) {
			try {
				response = task.apply(param);
				break;
			} catch (Exception e) {
			}
		}
		return response;
	}
}
