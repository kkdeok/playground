package com.doubleknd26.exercise.macro.mask;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.doubleknd26.exercise.macro.mask.search.CoupangSearcher;
import com.doubleknd26.exercise.macro.mask.search.Searcher;
import com.doubleknd26.exercise.macro.util.SlackMessageService;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class MaskMacro {
	@Parameter(names={"--config-path"}, required = false)
	private String configPath = "macro/config/prod.yml";

	private void start() throws Exception {
		Map conf = readConfig(configPath);
		SlackMessageService messageService = new SlackMessageService.Builder()
				.setWebHookUrl(String.valueOf(conf.get("slack_webhook_url")))
				.setChannel("mask_noti")
				.setInitMessage("마스크 매크로를 시작합니다.")
				.build();
		Searcher searcher = new CoupangSearcher(TargetInfo.COUPANG, false, messageService);
		searcher.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			// Use stderr here since the logger may have been reset by its JVM shutdown hook.
			searcher.stop();
			System.err.println("MaskMacro shutdown");
		}));
	}

	private Map readConfig(String configPath) throws FileNotFoundException {
		Yaml yaml = new Yaml();
		InputStream inputStream = new FileInputStream(configPath);
		Map map = (Map) yaml.load(inputStream);
		return map;
	}

	public static void main(String[] args) throws Exception {
		MaskMacro maskMacro = new MaskMacro();
		new JCommander(maskMacro, args);
		maskMacro.start();
	}
}
