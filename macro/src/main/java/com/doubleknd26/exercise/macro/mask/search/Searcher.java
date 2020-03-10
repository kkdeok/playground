package com.doubleknd26.exercise.macro.mask.search;

import com.doubleknd26.exercise.macro.mask.TargetInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class Searcher {
	WebDriver driver;
	TargetInfo targetInfo;
	ExecutorService executor;

	Searcher(TargetInfo targetInfo, boolean isHeadless) {
		initDriver(isHeadless);
		this.targetInfo = targetInfo;
		this.executor = Executors.newFixedThreadPool(1);
	}

	private void initDriver(boolean isHeadless) {
		ChromeOptions options = new ChromeOptions();
		if (isHeadless) {
			options.setHeadless(true);
			options.addArguments("disable-gpu");
		}
		this.driver = new ChromeDriver(options);
		this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	}

	public void start() throws Exception {
		visitMainPage();
		login();
		search();
		stop();
	}

	private void stop() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	void visitMainPage() {
		driver.get(targetInfo.getMainPageUrl());
	}

	abstract void login();

	abstract void search() throws Exception;
}
