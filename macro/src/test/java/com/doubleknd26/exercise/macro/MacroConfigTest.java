package com.doubleknd26.exercise.macro;

import com.doubleknd26.exercise.macro.util.MacroType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MacroConfigTest {

	@Test
	public void readMacroConfig() throws Exception {
		String configPath = "config/prod.yml";
		MacroConfig config = new MacroConfig(configPath, MacroType.MASK);
		List<MacroConfig.ServiceConfig> serviceConfigs = config.getServiceConfigs();
		assertEquals(1, serviceConfigs.size());
	}
}