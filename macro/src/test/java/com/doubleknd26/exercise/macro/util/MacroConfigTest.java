package com.doubleknd26.exercise.macro.util;

import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.*;

public class MacroConfigTest {

	@Test
	public void getServiceTarget() throws Exception {
		String configPath = "config/prod.yml";
		MacroConfig config = new MacroConfig(configPath);
		Map<String, Map<String, String>> maskTargetConfig = config.getServiceTarget("MASK");
		Map<String, String> coupangConfig = maskTargetConfig.get("coupang");
		assertEquals("https://www.coupang.com", coupangConfig.get("url"));
	}
}