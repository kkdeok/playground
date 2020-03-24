package com.doubleknd26.exercise.macro.mask.search;

import com.doubleknd26.exercise.macro.util.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Searcher {
	protected final Logger logger = LogManager.getLogger();

	protected WebDriverWrapper driver;
	protected ExecutorService executor;
	protected String mainUrl;
	protected String id;
	protected String pw;
	protected long count;

	Searcher(String userAgent, boolean isHeadless, String mainUrl, String id, String pw) {
		this.driver = new WebDriverWrapper(userAgent, isHeadless);
		this.executor = Executors.newFixedThreadPool(1);
		this.mainUrl = mainUrl;
		this.id = id;
		this.pw = pw;
		this.count = 0L;
	}

	public void start() throws Exception {
		visitMainPage();
		driver.wait(3);
		login();
		driver.wait(3);
		search();
	}

	public void stop() {
		driver.quit();
	}

	void visitMainPage() {
		driver.get(mainUrl);
		logger.info("visit main page: " + driver.getCurrentUrl());
	}

	abstract void login();

	abstract void search();
}
