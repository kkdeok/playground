package com.doubleknd26.exercise.macro.util;

import com.doubleknd26.exercise.macro.MacroConfig;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;


public class WebDriverWrapperTest {
	private static WebDriverWrapper driver;

	@BeforeClass
	public static void setUpClass() throws Exception {
		final String configPath = "config/template.yml";
		MacroConfig macroConfig = new MacroConfig(configPath, MacroType.MASK);
		MacroConfig.ServiceConfig config = macroConfig.getServiceConfigs().get(0);
		driver = new WebDriverWrapper(config.getUserAgent(), config.isHeadless());
	}

	@AfterClass
	public static void tearDown() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getOption() {
		ChromeOptions options = driver.getOptions();
		for (String str : options.getCapabilityNames()) {
			System.out.println(str);
		}
		
		
		for (Map.Entry<String, Object> entry : options.asMap().entrySet()) {
			String key = entry.getKey();
			String value = String.valueOf(entry.getValue());
			System.out.println(key + " : " + value);
		}
	}
//
//	@Test
//	public void findElement() {
//		driver.get(config.getMainPageUrl());
//		WebElement element = driver.findElement(By.className("au_tit"));
//		assertEquals("Creators", element.getText());
//	}
//
//	@Test
//	public void findVisibleElement() {
//		driver.get(url);
//		WebElement element = driver.findVisibleElement(By.id("query"));
//		assertTrue(element.isDisplayed());
//	}
}