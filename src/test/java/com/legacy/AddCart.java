package com.legacy;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCart {

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
	@Order(3)
	void createCart() {
		this.driver.get("http://localhost:3000/");

		WebElement baskets = this.driver
				.findElement(By.cssSelector("#root > div > nav > ul > a:nth-child(2) > div > span"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", baskets);
		baskets.click();

		WebElement name = this.driver.findElement(By.cssSelector("#Name"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", name);
		name.sendKeys("New Basket Test");

		WebElement submit = this.driver.findElement(By.cssSelector("#submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
		submit.click();

		WebElement newCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#cartName")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newCart);
		Assertions.assertEquals(true, newCart.getText().contains("New Basket Test"));

	}

	@AfterEach
	void closePage() {
		this.driver.quit();
	}
}