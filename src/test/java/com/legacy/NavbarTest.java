package com.legacy;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavbarTest {

	private RemoteWebDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@Test
	@Order(4)
	void navbar() {
		this.driver.get("http://localhost:3000/");

		WebElement signIn = this.driver
				.findElement(By.cssSelector("#root > div > nav > ul > a:nth-child(1) > div > span"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signIn);
		signIn.click();

		WebElement baskets = this.driver
				.findElement(By.cssSelector("#root > div > nav > ul > a:nth-child(2) > div > span"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", baskets);
		baskets.click();

		WebElement cart = this.driver
				.findElement(By.cssSelector("#root > div > nav > ul > li:nth-child(3) > a > div > svg"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cart);
		cart.click();

		WebElement items = this.driver.findElement(By.cssSelector("#root > div > nav > ul > li:nth-child(4) > a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", items);
		items.click();

		WebElement darkMode = this.driver.findElement(By.cssSelector("#root > div > nav > ul > div"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", darkMode);
		darkMode.click();

		WebElement lightMode = this.driver.findElement(By.cssSelector("#root > div > nav > ul > div"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lightMode);
		lightMode.click();

	}

	@AfterEach
	void closePage() {
		this.driver.quit();
	}
}