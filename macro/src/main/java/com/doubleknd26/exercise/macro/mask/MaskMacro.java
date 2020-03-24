package com.doubleknd26.exercise.macro.mask;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.doubleknd26.exercise.macro.mask.search.CoupangSearcher;
import com.doubleknd26.exercise.macro.mask.search.Searcher;
import com.doubleknd26.exercise.macro.util.MacroConfig;
import com.doubleknd26.exercise.macro.util.MessageService;

import java.util.Map;

public class MaskMacro {
	@Parameter(names={"--config-path"}, required = false)
	private String configPath = "macro/config/prod.yml";

	@Parameter(names={"--isHeadless"}, required = false)
	private boolean isHeadless = true;

	private static final String MASK = "MASK";
	private Searcher searcher;


	private void start() throws Exception {
		MacroConfig config = new MacroConfig(configPath);
		String webHookUrl = config.getSlackWebHookUrl();
		String channel = config.getServiceSlackChannel(MASK);

		// init MessageService
		MessageService.createInstance(webHookUrl, channel);
		MessageService messageService = MessageService.getInstance();
		messageService.noti(this.getClass().getSimpleName() + " is started.");

		// init Searcher
		Map<String, String> coupangConfig = config.getServiceTarget(MASK).get("coupang");
		searcher = new CoupangSearcher(config.getUserAgent(), isHeadless,
				coupangConfig.get("main_url"),
				coupangConfig.get("id"),
				coupangConfig.get("pw"));

		try {
			searcher.start();
			messageService.noti(this.getClass().getSimpleName() + " is terminated.");
		} catch (Exception e) {
			searcher.stop();
			e.printStackTrace();
			messageService.noti(this.getClass().getSimpleName() + " got issue!");
		}

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			// Use stderr here since the logger may have been reset by its JVM shutdown hook.
			MaskMacro.this.stop();
			System.err.println(this.getClass().getSimpleName() + " shutdown");
		}));
	}

	private void stop() {
		if (searcher != null) {
			searcher.stop();
		}
	}

	public static void main(String[] args) throws Exception {
		MaskMacro maskMacro = new MaskMacro();
		new JCommander(maskMacro, args);
		maskMacro.start();
	}
}
