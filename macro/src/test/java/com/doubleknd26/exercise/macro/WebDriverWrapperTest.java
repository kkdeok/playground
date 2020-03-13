package com.doubleknd26.exercise.macro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

public class WebDriverWrapperTest {
	private final String url = "http://www.naver.com";
	private WebDriverWrapper driver;

	@Before
	public void setUp() throws Exception {
		driver = new WebDriverWrapper(true);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void findElement() {
		driver.get(url);
		WebElement element = driver.findElement(By.className("au_tit"));
		assertEquals("Creators", element.getText());
	}

	@Test
	public void findVisibleElement() {
		driver.get(url);
		WebElement element = driver.findVisibleElement(By.id("query"));
		assertTrue(element.isDisplayed());
	}
}