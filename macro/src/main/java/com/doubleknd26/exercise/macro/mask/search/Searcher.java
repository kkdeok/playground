package com.doubleknd26.exercise.macro.mask.search;

import com.doubleknd26.exercise.macro.mask.TargetInfo;
import com.doubleknd26.exercise.macro.util.SlackMessageService;
import com.doubleknd26.exercise.macro.util.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Searcher {
	WebDriverManager driver;
	TargetInfo targetInfo;
	ExecutorService executor;
	SlackMessageService messageService;

	Searcher(TargetInfo targetInfo, boolean isHeadless, SlackMessageService messageService) {
		initDriver(isHeadless);
		this.targetInfo = targetInfo;
		this.messageService = messageService;
		this.executor = Executors.newFixedThreadPool(1);
	}

	private void initDriver(boolean isHeadless) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-gpu");
		if (isHeadless) {
			options.setHeadless(true);
		}
		this.driver = new WebDriverManager(options);
	}

	public void start() throws Exception {
		visitMainPage();
		Thread.sleep(2000);
		login();
		Thread.sleep(2000);
		search();
	}

	public void stop() {
		driver.quit();
	}

	void visitMainPage() {
		driver.get(targetInfo.getMainPageUrl());
	}

	abstract void login();

	abstract void search() throws Exception;
}
