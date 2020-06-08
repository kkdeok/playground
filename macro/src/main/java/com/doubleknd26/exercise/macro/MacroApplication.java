package com.doubleknd26.exercise.macro;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.doubleknd26.exercise.macro.service.MacroService;
import com.doubleknd26.exercise.macro.service.MacroServiceFactory;
import com.doubleknd26.exercise.macro.util.MacroType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MacroApplication {
	@Parameter(names={"--config-path"}, required = false)
	private static String configPath = "macro/config/prod.yml";
	
	@Parameter(names={"--macro-type"}, required = true, converter = MacroTypeConverter.class)
	private static MacroType macroType;
	
	private static final Logger logger = LogManager.getLogger();
	
	private ExecutorService executorService;
	private MacroConfig config;
	private int numThreads;
	
	
	private void init() throws FileNotFoundException{
		config = new MacroConfig(configPath, macroType);
		numThreads = config.getServiceConfigs().size();
		logger.info("numThreads: " + numThreads);

		executorService = Executors.newFixedThreadPool(numThreads);
		Runtime.getRuntime().addShutdownHook(new Thread(this::onStop));
	}
	
	private void start() {
		List<Future> futures = new ArrayList<>();
		for (MacroConfig.ServiceConfig serviceConfig : config.getServiceConfigs()) {
			MacroService service = MacroServiceFactory.create(serviceConfig);
			futures.add(executorService.submit(service::start));
		}
		executorService.shutdown();
	}
	
	private void onStop() {
		if (executorService != null) {
			try {
				if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
					executorService.shutdownNow();
				}
			} catch (InterruptedException e) {
				System.err.println(e);
				executorService.shutdownNow();
			}
		}
		System.out.println("MacroApplication is finished.");
	}
	
	public static void main(String[] args) throws Exception {
		MacroApplication macroApp = new MacroApplication();
		new JCommander(macroApp, args);
		macroApp.init();
		macroApp.start();
	}
}
