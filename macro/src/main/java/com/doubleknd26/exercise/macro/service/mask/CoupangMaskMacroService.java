package com.doubleknd26.exercise.macro.service.mask;

import com.doubleknd26.exercise.macro.MacroConfig;
import com.doubleknd26.exercise.macro.service.MacroService;
import com.doubleknd26.exercise.macro.util.MessageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CoupangMaskMacroService extends MacroService {

	public CoupangMaskMacroService(MacroConfig.ServiceConfig config) {
		super(config);
	}

	@Override
	protected String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected void login() {
		driver.get(config.getLoginPageUrl());
		driver.sendKeyToElement(By.className("_loginIdInput"), config.getId());
		driver.sendKeyToElement(By.className("_loginPasswordInput"), config.getPw());
		WebElement submitBtn = driver.findClickableElement(By.className("login__button--submit"));
		driver.clickAndWait(submitBtn);
	}

	@Override
	protected void run() {
		driver.get(config.getMacroPageUrl());
		while (true) {
			addWishListItemToCart();
			visitNextWishListPage();
		}
	}

	private void addWishListItemToCart() {
		try {
			List<WebElement> wishList = driver.findElements(By.className("wish-item"));
			for (WebElement item : wishList) {
				WebElement element = driver.findElement(item, By.className("item-name"));
				String itemName = element.getText();
				logger.info(itemName);
				try {
					driver.findClickableElement(item, By.className("add-to-cart__btn"), 1).click();
					MessageService.getInstance().noti(itemName + " 상품이 장바구니에 추가됐습니다.", "channel");
				} catch (RuntimeException e) {
				}
			}
		} catch (RuntimeException e) {
			// do nothing.
			logger.error(e.getMessage());
		}
	}

	private void visitNextWishListPage() {
		try {
			WebElement nextPageBtn = driver.findClickableElement(By.className("next-page"));
			driver.clickAndWait(nextPageBtn, 3);
		} catch (RuntimeException e) {
			driver.refresh();
		}
	}
}
