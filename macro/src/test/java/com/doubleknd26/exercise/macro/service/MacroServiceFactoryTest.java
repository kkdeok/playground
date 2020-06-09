package com.doubleknd26.exercise.macro.service;

import com.doubleknd26.exercise.macro.MacroConfig;
import com.doubleknd26.exercise.macro.service.mask.CoupangMaskMacroService;
import com.doubleknd26.exercise.macro.util.MacroType;
import com.doubleknd26.exercise.macro.util.ServiceName;
import org.junit.Test;

import static org.junit.Assert.*;

public class MacroServiceFactoryTest {
	
	@Test
	public void create() {
		MacroConfig.ServiceConfig config = MacroConfig.ServiceConfig.builder()
				.type(MacroType.MASK)
				.name(ServiceName.COUPANG)
				.isHeadless(true)
				.build();
		
		MacroService macroService = MacroServiceFactory.create(config);
		assertEquals(CoupangMaskMacroService.class.getSimpleName(), macroService.getName());
	}
	
	@Test(expected = RuntimeException.class)
	public void createWithUnknownType() {
		MacroConfig.ServiceConfig config = MacroConfig.ServiceConfig.builder()
				.type(null) // unknown type
				.build();
		
		MacroServiceFactory.create(config);
	}

	@Test(expected = RuntimeException.class)
	public void createUnknownServiceName() {
		MacroConfig.ServiceConfig config = MacroConfig.ServiceConfig.builder()
				.type(MacroType.MASK)
				.name(null) // unknown service name
				.build();
		
		MacroServiceFactory.create(config);
	}
}