package com.doubleknd26.exercise.macro.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.*;

import static com.doubleknd26.exercise.macro.util.RetryProcessor.*;

/**
 * consider only chrome for now.
 */
public class WebDriverManager {
	private static final int SCRIPT_TIMEOUT_SEC = 10;
	private static final int PAGE_LOAD_TIMEOUT_SEC = 10;
	private static final int IMPLICITLY_WAIT_TIMEOUT_SEC = 1;
	private WebDriver driver;
	private ExecutorService executor;

	public WebDriverManager(ChromeOptions options) {
		this.executor = Executors.newSingleThreadExecutor();
		this.driver = new ChromeDriver(options);
		initDriver();
	}

	private void initDriver() {
		this.driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT_SEC, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT_SEC, TimeUnit.SECONDS);
		this.driver.manage().timeouts().setScriptTimeout(SCRIPT_TIMEOUT_SEC, TimeUnit.SECONDS);
	}

	public void quit() {
		driver.quit();
	}

	public void get(String url) {
		driver.get(url);
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public boolean clickElement(By by) {
		final WebElement element = findElement(by);
		Callable<Boolean> task = () -> {
			element.click();
			return true;
		};
		return process(executor, task) != null;
	}

	// TODO: refactor here. no need to be here if driver is not used to it.
	public boolean clickElement(WebElement element, By by) {
		Callable<Boolean> task = () -> {
			element.findElement(by).click();
			return true;
		};
		return process(executor, task, 200, TimeUnit.MILLISECONDS) != null;
	}

	public void sendKeyToElement(By by, String key) {
		final WebElement element = findElement(by);
		Callable<Boolean> task = () -> {
			element.sendKeys(key);
			return true;
		};
		process(executor, task);
	}

	public WebElement findElement(By by) {
		Callable<WebElement> task = () -> driver.findElement(by);
		return process(executor, task);
	}

	public List<WebElement> findElements(By by) {
		Callable<List<WebElement>> task = () -> driver.findElements(by);
		return process(executor, task);
	}

	// TODO: refactor here. no need to be here if driver is not used to it.
	public String getElementText(WebElement element, By by) {
		Callable<String> task = () -> element.findElement(by).getText();
		return process(executor, task);
	}
}
