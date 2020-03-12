package com.doubleknd26.exercise.macro.mask.search;

import com.doubleknd26.exercise.macro.mask.TargetInfo;
import com.doubleknd26.exercise.macro.util.SlackMessageService;
import com.doubleknd26.exercise.macro.util.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Searcher {
	final Logger logger = LogManager.getLogger();
	WebDriverWrapper driver;
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
		this.driver = new WebDriverWrapper(options);
	}

	public void start() throws Exception {
		visitMainPage();
		driver.wait(1);
		login();
		driver.wait(1);
		search();
	}

	public void stop() {
		driver.quit();
	}

	void visitMainPage() {
		driver.get(targetInfo.getMainPageUrl());
		logger.info("visit main page: " + driver.driver.getCurrentUrl());
	}

	abstract void login();

	abstract void search();
}
