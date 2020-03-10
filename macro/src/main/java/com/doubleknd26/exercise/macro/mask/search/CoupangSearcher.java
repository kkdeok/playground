package com.doubleknd26.exercise.macro.mask.search;

import com.doubleknd26.exercise.macro.mask.TargetInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.TimeoutException;

public class CoupangSearcher extends Searcher {
	private static final String WISH_LIST_URL =
			"https://wish-web.coupang.com/wishInitView.pang?useTopBanner=true&isHttps=true&pcid=13242881933738580458302";

	public CoupangSearcher(TargetInfo targetInfo, boolean isHeadless) {
		super(targetInfo, isHeadless);
	}

	@Override
	void login() {
		WebElement elementLogIn = driver.findElement(By.id("login"));
		elementLogIn.click();
		WebElement elementId = driver.findElement(By.className("_loginIdInput"));
		elementId.sendKeys(targetInfo.getId());
		WebElement elementPw = driver.findElement(By.className("_loginPasswordInput"));
		elementPw.sendKeys(targetInfo.getPw());
		WebElement submit = driver.findElement(By.className("login__button--submit"));
		submit.click();
	}

	@Override
	void search() throws Exception {
		long tryCount = 0;
		while (true) {
			System.out.println(++tryCount + ": ----------------------");
			goWishListPage();
			int addedCount = 0;
			// TODO: find next page
//			do {
			addedCount += addWishListToCart();
//			} while (hasNextPage());
			if (addedCount > 0) {
				pay();
			}
		}
	}

	// TODO: fix here. first try always failed so that I added retry.
	private void goWishListPage() throws Exception {
		int retry = 3;
		while (retry-- > 0) {
			try {
				Runnable goToWishListPage = () -> driver.get(WISH_LIST_URL);
				executor.submit(goToWishListPage).get(2, TimeUnit.SECONDS);
				break;
			} catch (TimeoutException e) {
			}
		}
	}

	private void goCartPage() throws Exception {
		int retry = 3;
		while (retry-- > 0) {
			try {
				Runnable goToWishListPage = () -> driver.get("https://cart.coupang.com/cartView.pang");
				executor.submit(goToWishListPage).get(2, TimeUnit.SECONDS);
				break;
			} catch (TimeoutException e) {
			}
		}
	}

	private int addWishListToCart() {
		int addedCount = 0;
		List<WebElement> wishList = driver.findElements(By.className("wish-item"));
		for (WebElement item : wishList) {
			try {
				int retry = 2;
				while (retry-- > 0) {
					try {
						String itemName = item.findElement(By.className("item-name")).getText();
						System.out.println("try to add " + itemName);
						Callable<WebElement> m = () -> item.findElement(By.className("add-to-cart__btn"));
						Future<WebElement> future = executor.submit(m);
						WebElement addToCartBtn = future.get(100, TimeUnit.MILLISECONDS);
						addToCartBtn.click();
						System.out.println(itemName + " is added.");
						addedCount++;
						// TODO: slack noti
						break;
					} catch (StaleElementReferenceException e) {
					}
				}
			} catch (NoSuchElementException | InterruptedException | ExecutionException
					| TimeoutException e) {
				// do nothing
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
		goCartPage();
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
		System.out.println("구매 완료!");
	}
}
