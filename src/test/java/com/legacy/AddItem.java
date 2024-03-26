package com.legacy;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddItem {

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
	void createItem() {
		this.driver.get("http://localhost:3000/");

		WebElement items = this.driver.findElement(By.cssSelector("#root > div > nav > ul > li:nth-child(3) > a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", items);
		items.click();

		WebElement name = this.driver.findElement(By.cssSelector("#name"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", name);
		name.sendKeys("Apple iPhone");

		WebElement price = this.driver.findElement(By.cssSelector("#price"));
		price.sendKeys("599");

		WebElement quantity = this.driver.findElement(By.cssSelector("#quantity"));
		quantity.sendKeys("10");

		WebElement image = this.driver.findElement(By.cssSelector("#image"));
		image.sendKeys(
				"https://images.samsung.com/is/image/samsung/assets/uk/smartphones/galaxy-s23-fe/buy/05-colour-selections/basic-colour/S23-FE_Color-Selection_Mint_mo.jpg?imwidth=480");

		WebElement submit = this.driver.findElement(By.cssSelector("#submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
		submit.click();

		WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#q")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
		Assertions.assertEquals(true, item.getText().contains("Quantity"));

	}

}
