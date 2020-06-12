package com.doubleknd26.exercise.macro.service;

import com.doubleknd26.exercise.macro.MacroConfig;
import com.doubleknd26.exercise.macro.util.MessageService;
import com.doubleknd26.exercise.macro.util.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class MacroService {
	protected static final Logger logger = LogManager.getLogger();
	
	protected MacroConfig.ServiceConfig config;
	protected WebDriverWrapper driver;
	protected long count; // # of try.
	
	public MacroService(MacroConfig.ServiceConfig config) {
		this.config = config;
		this.driver = new WebDriverWrapper(config.getUserAgent(), config.isHeadless());
		this.count = 0L;
	}

	protected abstract void login();

	protected abstract void run();
	
	protected abstract String getName();
	
	public void start() {
		MessageService messageService = MessageService.getInstance();
		try {
			messageService.noti(getName() + " is started.");
			login();
			run();
		} catch (Exception e) {
			String errMsg = String.format("%s has exception: %s", getName(), e);
			logger.error(errMsg);
			messageService.noti(errMsg, "channel");
		} finally {
			stop();
		}
	}

	private void stop() {
		driver.quit();
	}
}
