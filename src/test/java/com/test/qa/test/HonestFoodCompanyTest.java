package com.test.qa.test;

//Author :- Anand Mane

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import com.test.qa.base.TestBase;

public class HonestFoodCompanyTest extends TestBase {

	TestBase testbase = new TestBase();

	@BeforeMethod
	public void setUp() {
		testbase.initialization();
	}

	@Test(priority = 1)
	public static void titleVerifaction() {
		String title = driver.getTitle();
		System.out.println("ARR :" + title);
		Assert.assertEquals(title, "ARR", "Found worng title");
	}
	
	@Test(priority = 2)
	public static void verifySerachBox() {
		String serach = driver.findElement(By.xpath("//div[@class='form']/input")).getAttribute("id");
	    String button =driver.findElement(By.xpath("//div[@class='form']/button")).getAttribute("id");

	    Assert.assertEquals(serach, "search-input");
	    Assert.assertEquals(button, "search-button");
	  
	}
	
	@Test(priority = 3)
	public static void verifyEmptyQuery() {
		driver.findElement(By.xpath("//div[@class='form']/input[@id='search-input']")).sendKeys("");
		driver.findElement(By.xpath("//div[@class='form']/button[@id='search-button']")).click();
		String str = driver.findElement(By.xpath("//div[@id='output-container']/ul/div[@id='error-empty-query']")).getText();
		Assert.assertEquals(str, "Provide some query");
	}
	
	@Test(priority = 4)
	public static void verifyResult() {
		driver.findElement(By.xpath("//div[@class='form']/input[@id='search-input']")).sendKeys("isla");
		driver.findElement(By.xpath("//div[@class='form']/button[@id='search-button']")).click();
		List<WebElement> list = driver
				.findElements(By.xpath("//div[@id='output-container']/ul/li"));
		Assert.assertTrue(list.size()>0);
		
	}
	
	
	@Test(priority = 5)
	public static void verifyNoResult() {
		driver.findElement(By.xpath("//div[@class='form']/input[@id='search-input']")).sendKeys("test");
		driver.findElement(By.xpath("//div[@class='form']/button[@id='search-button']")).click();
		String str = driver.findElement(By.xpath("//div[@id='output-container']/ul/div[@id='error-no-results']")).getText();
		Assert.assertEquals(str, "No results");
	}
	
	@Test(priority = 6)
	public static void verifySingleResult() {
		driver.findElement(By.xpath("//div[@class='form']/input[@id='search-input']")).sendKeys("Port Royal");
		driver.findElement(By.xpath("//div[@class='form']/button[@id='search-button']")).click();
		
		List<WebElement> list = driver
				.findElements(By.xpath("//div[@id='output-container']/ul/li")); 
		Assert.assertTrue(list.size()==1);
		
		String str = driver.findElement(By.xpath("//div[@id='output-container']/ul[@id='search-results']/li")).getText();
		Assert.assertEquals(str, "Port Royal");
	}

	@AfterMethod
	public static void closeBrowser() {
		driver.quit();
	}

}
