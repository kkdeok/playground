package com.doubleknd26.exercise.macro.mask.search;

import com.doubleknd26.exercise.macro.mask.TargetInfo;
import com.doubleknd26.exercise.macro.util.SlackMessageService;
import org.openqa.selenium.*;

import java.util.List;

public class CoupangSearcher extends Searcher {
	private static final String WISH_LIST_URL =
			"https://wish-web.coupang.com/wishInitView.pang?useTopBanner=true&isHttps=true&pcid=13242881933738580458302";

	public CoupangSearcher(TargetInfo targetInfo, boolean isHeadless, SlackMessageService messageService) {
		super(targetInfo, isHeadless, messageService);
	}

	@Override
	void login() {
		driver.clickElement(By.id("login"));
		driver.sendKeyToElement(By.className("_loginIdInput"), targetInfo.getId());
		driver.sendKeyToElement(By.className("_loginPasswordInput"), targetInfo.getPw());
		driver.clickElement(By.className("login__button--submit"));
	}

	@Override
	void search() throws Exception {
		driver.get(WISH_LIST_URL);
		long cnt = 0;
		while (true) {
			System.out.println("TRY: " + ++cnt);
			int addedCount = 0;
			// TODO: find next page
			addedCount += addWishItemToCart();
			if (addedCount > 0) {
//				pay();
				driver.get(WISH_LIST_URL);
			} else {
				driver.refresh();
			}
		}
	}

	private int addWishItemToCart() {
		int addedCount = 0;
		List<WebElement> wishList = driver.findElements(By.className("wish-item"));
		for (WebElement item : wishList) {
			boolean success = driver.clickElement(item, By.className("add-to-cart__btn"));
			if (success) {
				String name = driver.getElementText(item, By.className("item-name"));
				messageService.noti(name + " 상품이 장바구니에 추가됐습니다.", "channel");
				addedCount++;
			}
		}
		return addedCount;
	}

	private boolean hasNextPage() {
		WebElement elementNextPage = driver.findElement(By.className("paging-area"))
				.findElement(By.className("next-page"));
		if (elementNextPage.getAttribute("disabled") == null) {
			elementNextPage.click();
			return true;
		}
		return false;
	}

	private void pay() throws Exception {
		driver.get("https://cart.coupang.com/cartView.pang");
		WebElement elementAllDealSelect = driver.findElement(By.className("all-deal-select"));
		if (!elementAllDealSelect.isSelected()) {
			elementAllDealSelect.click();
		}
		driver.findElement(By.id("btnPay")).click();
		driver.findElement(By.className("insert-cash-toggle")).click();
		driver.findElement(By.id("cashAllUsing")).click();
		driver.findElement(By.className("active")).click();
		driver.findElement(By.id("paymentBtn")).click();
		Thread.sleep(5000);
		messageService.noti("구매 완료!", "channel");
	}
}
