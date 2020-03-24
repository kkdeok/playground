package com.doubleknd26.exercise.macro.util;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class MacroConfig {
	private Map<String, String> defaultConfig;
	private Map<String, Map> serviceConfig;

	@SuppressWarnings("unchecked")
	public MacroConfig(String configPath) throws FileNotFoundException {
		Yaml yaml = new Yaml();
		InputStream inputStream = new FileInputStream(configPath);
		Map map = (Map) yaml.load(inputStream);

		this.defaultConfig = (Map<String, String>) map.get("DEFAULT");
		this.serviceConfig = (Map<String, Map>) map.get("SERVICE");
	}

	public String getSlackWebHookUrl() {
		return defaultConfig.get("web_hook_url");
	}

	public String getUserAgent() {
		return defaultConfig.get("user_agent");
	}

	public String getServiceSlackChannel(String service) {
		return String.valueOf(serviceConfig.get(service).get("slack_channel"));
	}

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> getServiceTarget(String service) {
		return (Map<String, Map<String, String>>) serviceConfig.get(service).get("target");
	}
}
