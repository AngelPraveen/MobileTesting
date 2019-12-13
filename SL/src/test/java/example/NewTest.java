package example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
	private WebDriver driver;

  @Test
  public void testURL() throws InterruptedException {
	  	
	  	driver.get("https://dashboard:Password1234@k8dashboard.dev.oneview-seniorliving.com/#!/pod?namespace=staging");
	  	driver.manage().window().maximize();
	  	
	  	WebDriverWait wait = new WebDriverWait(driver, 30);
	  	By locator = By.tagName("kd-middle-ellipsis");
	  	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	  	
	  	
	  	String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Pods - Kubernetes Dashboard")); 
		driver.findElement(By.xpath("/html/body/kd-chrome/md-content/kd-nav/md-sidenav/div[4]/kd-nav-item[6]/a/span")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		By nextButton = By.xpath("//kd-resource-card-list-footer/div/div[2]/kd-resource-card-list-pagination/div/dir-pagination-controls/div/div[2]/button[3]");
		By rowElement = By.tagName("kd-middle-ellipsis");
				// 
				Boolean lastPage = driver.findElement(nextButton).isEnabled();
				System.out.println(lastPage);
				By  e1 = By.xpath("//kd-resource-card-list-footer/div/div[2]/kd-resource-card-list-pagination/div/dir-pagination-controls/div/div[1]");
                                 
				String pageNo = driver.findElement(e1).getText();
				int count = 0;
				//
		try{
			do{
		
			
			wait.until(ExpectedConditions.presenceOfElementLocated(rowElement));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
			By rowVal = By.tagName("kd-middle-ellipsis");
			
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rowVal));
			
			List<WebElement> failList =  driver.findElements(rowVal);
			
			for(WebElement e : failList) {
				count = count + 1;
				
				System.out.println(e.getAttribute("display-string"));
										}
			
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			System.out.println("----------------------------------------------------------");
			
			driver.findElement(nextButton).click();
			wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.tagName("kd-middle-ellipsis"))));
			driver.navigate().forward();
			
			System.out.println(pageNo);
			
			
			
						}while(lastPage) ;
			
		}catch(StaleElementReferenceException se) {
			System.out.println("count of pods active is :: "+count);
			System.out.println(se.getMessage());
		}
			
  }
  
  @BeforeTest
  public void beforeTest() {	
		    driver = new ChromeDriver();  
  }
  

  @AfterTest
 public void afterTest() {
			driver.quit();	
  }
  

}


