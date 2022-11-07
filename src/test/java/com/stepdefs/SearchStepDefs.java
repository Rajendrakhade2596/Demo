package com.stepdefs;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import com.base.BaseClass;
import com.base.BasePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchStepDefs {
	WebDriver driver = null;
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}


	@Given("^user on home page$")
	public void user_on_home_page() throws Throwable {

		driver = BaseClass.initialization(); 
		
	    driver.findElement(By.id("loginform-username")).sendKeys("samrat");

		driver.findElement(By.id("loginform-password")).sendKeys("s@m123");

		WebElement button = driver.findElement(By.id("savebutton"));
		
		button.click();
		
		BasePage.webdriverWaitForElementInvisible(driver, 20, button);

	}

	@When("^user search existing brand name$")
	public void user_search_existing_brand_name() throws Throwable {

		WebElement searchbox = driver.findElement(By.xpath("//input[@type='text'and @placeholder ='Search']"));

		searchbox.sendKeys("Apple");

		WebElement searchbutton = driver.findElement(By.xpath("//button[@type='submit' and @name ='navsearch']"));

		searchbutton.click();
	}

	@Then("^user should be visible related products$")
	public void user_should_be_visible_related_products() throws Throwable {

		ArrayList<String> actlist = new ArrayList<String>();

		ArrayList<String> explist = BasePage.readExcelSheetDataUsingArraylist("WACartData.xlsx", "testdata01");

		WebElement showsize = driver.findElement(By.xpath("//select[@id='showItemsPerPage']"));

		Select select = new Select(showsize);

		select.selectByVisibleText("18");

		Thread.sleep(2000);

		List<WebElement> productname = driver.findElements(By.xpath("//div[@class='caption' ]//h4"));

		for (WebElement product : productname) {

			actlist.add(product.getText());
		}

		System.out.println(actlist);
		System.out.println(explist);
		Assert.assertEquals(explist, actlist);
	}

	@Given("^user on search result page$")
	public void user_on_search_result_page() throws Throwable {

		driver = BaseClass.initialization();
	
		
	    driver.findElement(By.id("loginform-username")).sendKeys("samrat");

		driver.findElement(By.id("loginform-password")).sendKeys("s@m123");

	    WebElement button = driver.findElement(By.id("savebutton"));
		
		button.click();
		
		BasePage.webdriverWaitForElementInvisible(driver, 20, button);

		WebElement searchbutton = driver.findElement(By.xpath("//button[@type='submit' and @name ='navsearch']"));

		searchbutton.click();

	}

	@When("^user search from search criteria field$")
	public void user_search_from_search_criteria_field() throws Throwable {

		WebElement keywordbox = driver.findElement(By.xpath("//input[@id='searchform-keyword' and @type ='text']"));

		keywordbox.sendKeys("Canon EOS");

		WebElement selectcategory = driver.findElement(By.xpath("//select[@id='searchform-categoryid']"));

		Select select = new Select(selectcategory);

		select.selectByVisibleText("Camera");

		WebElement searchbutton = driver.findElement(By.xpath("//button[@id='save']"));

		searchbutton.click();

	}

	@Then("^user should be displayed search products$")
	public void user_should_be_displayed_search_products() throws Throwable {

		ArrayList<String> actlist = new ArrayList<String>();

		ArrayList<String> explist = BasePage.readExcelSheetDataUsingArraylist("WACartData.xlsx", "testdata02");

		Thread.sleep(2000);

		WebElement showsize1 = driver
				.findElement(By.xpath("//select[@id='showItemsPerPage' and @name='showItemsPerPage']"));

		Select select1 = new Select(showsize1);

		select1.selectByIndex(3);

		Thread.sleep(2000);

		List<WebElement> productname = driver.findElements(By.xpath("//div[@class='caption' ]//h4"));

		for (WebElement product : productname) {

			actlist.add(product.getText());
		}

		System.out.println(actlist);
		System.out.println(explist);
		Assert.assertEquals(explist, actlist);
	}

	
}
