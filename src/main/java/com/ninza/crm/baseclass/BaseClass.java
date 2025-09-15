package com.ninza.crm.baseclass;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	
	public PropertyFileUtility pf=new PropertyFileUtility();
	public ExcelFileUtility ex=new ExcelFileUtility();
	public JavaUtility ju = new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public 	WebDriver driver=null;
	public static WebDriver sdriver=null;

  @BeforeMethod(groups = {"Smoke","Regression"})
  public void beforeMethod() throws IOException {
	  System.out.println("login to the application");
	   String URL = pf.toGetDataFromPropertiesFile("url");
	   String USERNAME = pf.toGetDataFromPropertiesFile("username");
	   String PASSWORD = pf.toGetDataFromPropertiesFile("password");
	   LoginPage lp=new LoginPage(driver);
	   lp.LoginIntoApp(URL,USERNAME,PASSWORD);
  }

  @AfterMethod(groups = {"Smoke","Regression"})
  public void afterMethod() {
	  System.out.println("logout from the application");
  }
//@org.testng.annotations.Parameters("Browser")
  @BeforeClass(groups = {"Smoke","Regression"})
  public void beforeClass() throws IOException {
	  System.out.println("launch the browser");
	  String BROWSER = pf.toGetDataFromPropertiesFile("browser");
	  //String BROWSER = System.getProperty("browser");
	  
		// TODO Auto-generated method stub
		ChromeOptions settings = new ChromeOptions();
		//EdgeOptions
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
    	settings.setExperimentalOption("prefs", prefs);
  
	  //WebDriverManager.edgedriver().setup();
	  
		//Launch the browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver(settings);
		} else if (BROWSER.equalsIgnoreCase("edge")) {
		   	driver=new EdgeDriver();	
		}else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		sdriver=driver;
		
  }

  @AfterClass(groups = {"Smoke","Regression"})
  public void afterClass() {
	  System.out.println("close the browser");
	  driver.quit();
  }
  

  @BeforeTest
  public void beforeTest() {
	  System.out.println("pre-conditions for parallel execution");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("post-conditions for parallel execution");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("connect to database");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("disconnect to database");
  }

}
