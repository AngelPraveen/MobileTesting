package configuration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class LoginTest extends AppConfig {

	@Test (description = "Login To Care Visits Android App", priority=1)
	public void loginTest(ITestContext context)throws Exception {

		System.out.println("Into Login Test Method");
		
	/*	  WebDriver driver = (WebDriver)context.getAttribute("webDriver");
		  System.out.println("1: "+driver);
		  Properties prop = (Properties)context.getAttribute("prop");
		  System.out.println("2: "+prop);
		 
		 */ 

		WebDriverWait wait = new WebDriverWait(driver,90);
		

		By login = By.id("login_username");
		wait.until(ExpectedConditions.visibilityOfElementLocated(login));
		System.out.println("Username field found");

		WebElement el = driver.findElement(login);


		Reporter.log("Launched Care Visit Android app");
		System.out.println("Launching app");
		el.sendKeys("ResidentTablet123");
		driver.findElement(By.id("login_password")).sendKeys("Pa$$word1234");
		driver.findElement(By.id("login_button")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		By resList = By.id("listResidents");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resList));
		assertTrue(driver.findElement(resList).isDisplayed());
		//wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.id("title")), "text", "Oneview Facility-Other"));
		//wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("database_status_icon"))));

		Reporter.log(" | User Logged in to Care Visit Android App");

	}

	

	@Test (description = "Find a Resident from the Resident List", dependsOnMethods = "loginTest")
	public void findResident() {
		System.out.println("Inside Find resident test");
		String residentName = "Tom Jerry";
		WebDriverWait wait = new WebDriverWait(driver,30);
		Boolean residentFound =searchItemByElementID(residentName, "resident_name");
		
		if(residentFound) {
			Reporter.log("Resident Found");
			System.out.println("Found Resident !!");
		wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.id("title")), "text", residentName))
		assertEquals(driver.findElement(By.id("title")).getAttribute("text"), residentName);
		}else {
			Reporter.log("Resident Not Found In List !!!");
			assertTrue(residentFound, "Resident Not Found In List !!!");
		}
	} 

}
