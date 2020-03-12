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
		driver.clickElement(By.id("login"));
		driver.wait(3);
		driver.sendKeyToElement(By.className("_loginIdInput"), targetInfo.getId());
		driver.sendKeyToElement(By.className("_loginPasswordInput"), targetInfo.getPw());
		driver.clickElement(By.className("login__button--submit"));
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
			String itemName = driver.findElement(item, By.className("item-name")).getText();
			logger.info(itemName);
			WebElement addToCartBtn = driver.findElement(item, By.className("add-to-cart__btn"), 1);
			if (addToCartBtn != null) {
				driver.clickElement(addToCartBtn);
				messageService.noti(itemName + " 상품이 장바구니에 추가됐습니다.", "channel");
				addedCount++;
			}
		}
		return addedCount;
	}

	private void visitNextWishListPage() {
		WebElement nextPage = driver.findElement(By.className("next-page"));
		// can visit next wish list page
		if (nextPage.getAttribute("disabled") == null) {
			driver.clickElement(nextPage);
			// Without it, wishItem.item in addToCart()
			// throw StaleElementReferenceException.
			driver.wait(3);
		} else {
			driver.refresh();
			printTryCount();
		}
	}

	private void pay() {
		driver.get("https://cart.coupang.com/cartView.pang");
		WebElement elementAllDealSelect = driver.findElement(By.className("all-deal-select"));
		if (!elementAllDealSelect.isSelected()) {
			elementAllDealSelect.click();
		}
		driver.wait(1);
		WebElement payBtn = driver.findElement(By.id("btnPay"));
		driver.clickElement(payBtn);
		driver.wait(1);
		WebElement toggle = driver.findElement(By.className("insert-cash-toggle"));
		driver.clickElement(toggle);
		driver.wait(1);
		WebElement cashAllUsing = driver.findElement(By.id("cashAllUsing"));
		driver.clickElement(cashAllUsing);
		driver.wait(1);
		WebElement active = driver.findElement(By.className("active"));
		driver.clickElement(active);
		WebElement paymentBtn = driver.findElement(By.id("paymentBtn"));
		driver.clickElement(paymentBtn);
		driver.wait(5);
		messageService.noti("구매 완료!", "channel");
	}
}
