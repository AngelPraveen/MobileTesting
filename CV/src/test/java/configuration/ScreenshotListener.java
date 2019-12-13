package configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import io.appium.java_client.android.AndroidDriver;



public class ScreenshotListener extends TestListenerAdapter {
	
	
	/*  @Override 
	  public void onTestStart(ITestResult result){
		  System.out.println("On Test start method...");
	  WebDriver driver;
	  AndroidDriver androidDriver = null;
	  
		
		  Properties prop = new Properties(); 
		  InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources.properties");
		  try {
			  prop.load(inputStream);
		  } catch (IOException ie) {
			  System.out.println(ie.getMessage());
		  }
		 
	  
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
	  try { 
	  driver = new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
	  androidDriver = (AndroidDriver) driver;
	  result.setAttribute("webDriver", driver);
	  result.setAttribute("properties", prop);
	  System.out.println("Attributes set at Listener at test start");
	  } catch (MalformedURLException e) {
	  
	  e.printStackTrace(); }
	  
	  }
	  */
	 
	
    @Override
    public void onTestFailure(ITestResult result) {

    	//Taking screeenshot on failure !
    	if(!result.isSuccess()){

    		WebDriver driver = (WebDriver)result.getTestContext().getAttribute("webDriver");

    		try {
    			TakesScreenshot screenshot=(TakesScreenshot)driver;

    			File src=screenshot.getScreenshotAs(OutputType.FILE);

    			Calendar cal = Calendar.getInstance();
    			SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_HH_mm");
    			String dateStr = dateFormat.format(cal.getTime());
    			String filename = result.getName()+"_"+dateStr+".jpg";
    			System.out.println("filename :: "+filename);
    			String screen_shot_path = System.getProperty("user.dir")+"\\screen_shot\\"+filename;
    			System.out.println("user.dir :: "+System.getProperty("user.dir"));
    			FileUtils.copyFile(src, new File(screen_shot_path));

    			
    			Reporter.log("<td><a href='" + screen_shot_path + "'>"
    					+ "<img src='"+ screen_shot_path 
    					+"' height='100' width='100'></a></td>");


    			System.out.println("Successfully captured a screenshot at file named :: "+screen_shot_path);

    		} catch (Exception e) {
    			e.printStackTrace();
    		}

    	}
    }
	
	

}
