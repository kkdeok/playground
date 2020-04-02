package com.doubleknd26.exercise.macro.mask.search;

import com.doubleknd26.exercise.macro.util.MessageService;
import org.openqa.selenium.*;

import java.util.List;

public class CoupangSearcher extends Searcher {
	private static final String WISH_LIST_URL =
			"https://wish-web.coupang.com/wishInitView.pang?useTopBanner=true&isHttps=true";


	public CoupangSearcher(String userAgent, boolean isHeadless, String mainUrl, String id, String pw) {
		super(userAgent, isHeadless, mainUrl, id, pw);
	}

	@Override
	void login() {
		WebElement loginBtn = driver.findClickableElement(By.id("login"));
		driver.clickAndWait(loginBtn);
		driver.sendKeyToElement(By.className("_loginIdInput"), id);
		driver.sendKeyToElement(By.className("_loginPasswordInput"), pw);
		WebElement submitBtn = driver.findClickableElement(By.className("login__button--submit"));
		driver.clickAndWait(submitBtn);
	}

	@Override
	void search() {
		driver.get(WISH_LIST_URL);
		printTryCount();
		while (true) {
			boolean succeed = tryAddWishItemToCart();
			if (succeed) {
				pay();
				driver.get(WISH_LIST_URL);
			} else {
				visitNextWishListPage();
			}
		}
	}

	private void printTryCount() {
		logger.info("TRY: " + ++count + " --------------------------------");
	}

	private boolean tryAddWishItemToCart() {
		try {
			List<WebElement> wishList = driver.findElements(By.className("wish-item"));
			for (WebElement item : wishList) {
				WebElement element = driver.findElement(item, By.className("item-name"), 1);
				String itemName = element.getText();
				logger.info(itemName);
				boolean isAddToCartBtnExists = driver.isWebElementExists(item, By.className("add-to-cart__btn"));
				if (isAddToCartBtnExists) {
					driver.findClickableElement(item, By.className("add-to-cart__btn"), 1).click();
					MessageService.getInstance().noti(itemName + " 상품이 장바구니에 추가됐습니다.", "channel");
					return true;
				}
			}
		} catch (RuntimeException e) {
			// do nothing.
			logger.error(e.getMessage());
		}
		return false;
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
			driver.clickAndWait(payBtn, 1);

			// Because I already put cash into coupay accounts, It can buy through one click.
			WebElement paymentBtn = driver.findClickableElement(By.id("paymentBtn"));
			driver.clickAndWait(paymentBtn, 3);
			MessageService.getInstance().noti("구매 완료!", "channel");
		} catch (Exception e) {
			e.printStackTrace();
			MessageService.getInstance().noti("구매 실패! 직접 장바구니를 확인하세요.", "channel");
		}
	}
}
