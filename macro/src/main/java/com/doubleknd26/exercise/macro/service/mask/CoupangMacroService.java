package com.doubleknd26.exercise.macro.service.mask;

import com.doubleknd26.exercise.macro.MacroConfig;
import com.doubleknd26.exercise.macro.service.MacroService;
import com.doubleknd26.exercise.macro.util.MessageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CoupangMacroService extends MacroService {

	public CoupangMacroService(MacroConfig.ServiceConfig config) {
		super(config);
	}

	@Override
	protected void login() {
//		driver.get(config.getUrl());
		WebElement loginBtn = driver.findClickableElement(By.id("login"));
		driver.clickAndWait(loginBtn);
		driver.sendKeyToElement(By.className("_loginIdInput"), config.getId());
		driver.sendKeyToElement(By.className("_loginPasswordInput"), config.getPw());
		WebElement submitBtn = driver.findClickableElement(By.className("login__button--submit"));
		driver.clickAndWait(submitBtn);
	}

	@Override
	protected void runMacro() {
		driver.get(WISH_LIST_URL);
		printTryCount();
		while (true) {
			tryAddWishItemToCart();
			visitNextWishListPage();
		}
	}

	private static final String WISH_LIST_URL =
			"https://wish-web.coupang.com/wishInitView.pang?useTopBanner=true&isHttps=true";

	private void printTryCount() {
		logger.info("TRY: " + ++count + " --------------------------------");
	}

	private void tryAddWishItemToCart() {
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
			printTryCount();
		}
	}
	
//
//	private void pay() {
//		try {
//			driver.get("https://cart.coupang.com/cartView.pang");
//			WebElement allDealSelectBtn = driver.findClickableElement(By.className("all-deal-select"));
//			if (!allDealSelectBtn.isSelected()) {
//				driver.clickAndWait(allDealSelectBtn, 1);
//			}
//
//			// go to pay page.
//			WebElement payBtn = driver.findClickableElement(By.id("btnPay"));
//			driver.clickAndWait(payBtn, 1);
//
//			// Because I already put cash into coupay accounts, It can buy through one click.
//			WebElement paymentBtn = driver.findClickableElement(By.id("paymentBtn"));
//			driver.clickAndWait(paymentBtn, 3);
//			MessageService.getInstance().noti("구매 완료!", "channel");
//		} catch (Exception e) {
//			e.printStackTrace();
//			MessageService.getInstance().noti("구매 실패! 직접 장바구니를 확인하세요.", "channel");
//		}
//	}
}
