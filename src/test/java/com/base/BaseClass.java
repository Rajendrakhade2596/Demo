package com.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClass extends BasePage {
	
	public static WebDriver driver = null;
	
	public static Properties prop = new Properties();

	public static WebDriver initialization() throws Exception {

		FileInputStream fis = new FileInputStream("C:/Users/rajen/eclipse-workspace 21/WACartBDDFramework/src/main/resources/config.properties");
		
		prop.load(fis);
		
		String browser = prop.getProperty("browser");

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"D:/chromedriver.exe");
			driver = new ChromeDriver();
			
		}

		if (browser.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			

		}
		launchApplication();
		return driver;
	}

	public static void launchApplication() {
		
		driver.manage().window().maximize();
		
		driver.get(prop.getProperty("url"));
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

    public void WebdriverWaitForElement(WebElement element , int time ) {
		
		WebDriverWait wait = new WebDriverWait(driver, time);
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
