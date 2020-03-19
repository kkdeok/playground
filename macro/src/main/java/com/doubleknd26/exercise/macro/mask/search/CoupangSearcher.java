package com.doubleknd26.exercise.macro.mask.search;

import com.doubleknd26.exercise.macro.mask.TargetInfo;
import com.doubleknd26.exercise.macro.util.SlackMessageService;
import org.openqa.selenium.*;

import java.util.List;

public class CoupangSearcher extends Searcher {
	private static final String WISH_LIST_URL =
			"https://wish-web.coupang.com/wishInitView.pang?useTopBanner=true&isHttps=true";
	private int tryCnt;

	public CoupangSearcher(TargetInfo targetInfo, boolean isHeadless, SlackMessageService messageService) {
		super(targetInfo, isHeadless, messageService);
		tryCnt = 0;
	}

	@Override
	void login() {
		logger.info("login: " + driver.driver.getCurrentUrl());
		logger.info(driver.driver.getPageSource());
		WebElement loginBtn = driver.findClickableElement(By.id("login"));
		driver.clickAndWait(loginBtn);
		driver.sendKeyToElement(By.className("_loginIdInput"), targetInfo.getId());
		driver.sendKeyToElement(By.className("_loginPasswordInput"), targetInfo.getPw());
		WebElement submitBtn = driver.findClickableElement(By.className("login__button--submit"));
		driver.clickAndWait(submitBtn);
	}

	@Override
	void search() {
		driver.get(WISH_LIST_URL);
		printTryCount();
		while (true) {
			int addedCount = 0;
			addedCount += addToCart();
			if (addedCount > 0) {
				pay();
				driver.get(WISH_LIST_URL);
			} else {
				visitNextWishListPage();
			}
		}
	}

	private void printTryCount() {
		logger.info("TRY: " + ++tryCnt + " --------------------------------");
	}

	private int addToCart() {
		int addedCount = 0;
		List<WebElement> wishList = driver.findElements(By.className("wish-item"));
		for (WebElement item : wishList) {
			WebElement element = driver.findElement(item, By.className("item-name"));
			String itemName = element.getText();
			logger.info(itemName);
			try {
				driver.findClickableElement(item, By.className("add-to-cart__btn"), 1).click();
				messageService.noti(itemName + " 상품이 장바구니에 추가됐습니다.", "channel");
				addedCount++;
				break; // 장바구니에 추가되면 바로 구매.
			} catch (RuntimeException e) {}
		}
		return addedCount;
	}

	private void visitNextWishListPage() {
		try {
			WebElement nextPageBtn = driver.findClickableElement(By.className("next-page"));
			driver.clickAndWait(nextPageBtn);
		} catch (RuntimeException e) {
			driver.refresh();
			printTryCount();
		}
	}

	private void pay() {
		try {
			driver.get("https://cart.coupang.com/cartView.pang");
			WebElement allDealSelectBtn = driver.findClickableElement(By.className("all-deal-select"));
			if (!allDealSelectBtn.isSelected()) {
				driver.clickAndWait(allDealSelectBtn, 1);
			}

			// go to pay page.
			WebElement payBtn = driver.findClickableElement(By.id("btnPay"));
			driver.clickAndWait(payBtn, 2);

			// for payment
			WebElement toggle = driver.findClickableElement(By.className("insert-cash-toggle"));
			driver.clickAndWait(toggle, 1);

			WebElement cashAllUsingBtn = driver.findClickableElement(By.id("cashAllUsing"));
			driver.clickAndWait(cashAllUsingBtn, 1);

			WebElement activeBtn = driver.findClickableElement(By.className("active"));
			driver.clickAndWait(activeBtn, 1);

			WebElement paymentBtn = driver.findClickableElement(By.id("paymentBtn"));
			driver.clickAndWait(paymentBtn, 3);
			messageService.noti("구매 완료!", "channel");
		} catch (Exception e) {
			e.printStackTrace();
			messageService.noti("구매 실패! 직접 장바구니를 확인하세요.", "channel");
		}
	}
}
