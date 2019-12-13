package configuration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;



@Listeners(ScreenshotListener.class)
public class AppConfig {

	WebDriver driver;
	AndroidDriver androidDriver = null;

	@BeforeTest
	public void setUP(ITestContext context) throws MalformedURLException {
		
	/* Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources.properties");
		try {
			prop.load(inputStream);
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
		}
		*/
		
		System.out.println("Inside setup method");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "R52K10XNSAH");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "9");
		capabilities.setCapability("appActivity", "view.activity.SplashActivity");
		capabilities.setCapability("appPackage", "com.oneviewhealthcare.carevisit");
		capabilities.setCapability("autoGrantPermissions", "true");

		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("fullRest", "false");


		// Initialize drivers before being used
		driver = new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
		androidDriver = (AndroidDriver)driver;
		context.setAttribute("webDriver", driver);
		//context.setAttribute("properties", prop);
		
		System.out.println("Drivers initialized at Appconfig before test");

	}



	public boolean searchItemByElementID(String textToFind, String elementID) {

		for (int count = 0; count < 5; count++) {
			List<WebElement> elementsList = driver.findElements(By.id(elementID));
			Iterator<WebElement> it = elementsList.iterator();

			while (it.hasNext()) {
				WebElement webelement = it.next();
				if ((webelement.getAttribute("text").contains(textToFind))) {
					webelement.click();
					return true;
				}
			}
			swipeUp();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public void swipeUp() {
		System.out.println("Inside swipe method ...");
		TouchAction swipe = new TouchAction(androidDriver)
				.press(PointOption.point(930,1350))
				.waitAction(waitOptions(ofMillis(500)))
				.moveTo(PointOption.point(930,500))
				.release();

		System.out.println("Perform swipe..");
		swipe.perform();

	}


	@AfterSuite
	public void tearDown() { 
		Reporter.log("Performing Tear Down");
		driver.quit();
	}

}
