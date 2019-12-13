package config;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class AppConfig {
	
	WebDriver driver;
	AndroidDriver Adriver;
	
	MobileBy by1;

  @BeforeClass
  public void setUP() throws MalformedURLException{
	  DesiredCapabilities capabilities = new DesiredCapabilities();
	  
	  capabilities.setCapability("deviceName", "R52K10XNSAH");
	  capabilities.setCapability("automationName", "UiAutomator2");
	  capabilities.setCapability("platformName","Android");
	  capabilities.setCapability("platformVersion", "9");
	  capabilities.setCapability("appActivity", "view.activity.SplashActivity");
	  capabilities.setCapability("appPackage", "com.oneviewhealthcare.carevisit");
	  capabilities.setCapability("autoGrantPermissions", "true");
	  
	  Adriver =  new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
	  
  }
  
  @Test
  public void testConnection() throws Exception {
	  
	  WebElement qr = Adriver.findElement(by1.id("activity_qrscan_qa_button"));
	  WebDriverWait wait = new WebDriverWait(Adriver,30);
	 
	  wait.until(ExpectedConditions.elementToBeClickable(qr));
	  qr.click();
		/*
		 * driver.findElement(By.id("login_username")).sendKeys("ResidentTablet123");
		 * driver.findElement(By.id("login_password")).sendKeys("Pa$$word1234");
		 * driver.findElement(By.id("login_button")).click();
		 */
  }

  @AfterClass
  public void tearDown() {
	  Adriver.quit();
  }

}
