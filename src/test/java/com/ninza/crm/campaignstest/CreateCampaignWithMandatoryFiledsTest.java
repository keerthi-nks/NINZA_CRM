package com.ninza.crm.campaignstest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;


public class CreateCampaignWithMandatoryFiledsTest {
	public static void main(String[] args) throws IOException, Throwable {
		// TODO Auto-generated method stub
				ChromeOptions settings = new ChromeOptions();
				
				Map<String, Object> prefs = new HashMap<>();
					    prefs.put("profile.password_manager_leak_detection", false);
					    settings.setExperimentalOption("prefs", prefs);
					    
					    PropertyFileUtility pf=new PropertyFileUtility();
					    //get the values
						String BROWSER = pf.toGetDataFromPropertiesFile("browser");
		                String URL = pf.toGetDataFromPropertiesFile("url");
						String USERNAME = pf.toGetDataFromPropertiesFile("username");
						String PASSWORD = pf.toGetDataFromPropertiesFile("password");
						
						//excel file
						ExcelFileUtility ex=new ExcelFileUtility();
						String campaignName = ex.toReadTheDataFromExcel("camp", 1, 1);
						String targetsize = ex.toReadTheDataFromExcel("camp", 1,2);			
			
						WebDriver driver=null;
						//System.setProperty("webdriver.edge.driver", "C:\\Users\\sonur\\Downloads\\edgedriver_win64");
						if (BROWSER.equals("chrome")) {
							driver=new ChromeDriver(settings);
							
						} else if (BROWSER.equals("edge")) {
							driver=new EdgeDriver();
										
						}else if(BROWSER.equals("firefox"))
						{
							driver=new FirefoxDriver();
						}
						WebDriverUtility wlib=new WebDriverUtility();
						 driver.manage().window().maximize();
						
						wlib.waitforpageToload(driver);
						driver.get(URL);
						//Login into the Ninza crm
						
						/*driver.findElement(By.id("username")).sendKeys(USERNAME);
						driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
						driver.findElement(By.xpath("//button[@type='submit']")).click();*/
						
						LoginPage lp=new LoginPage(driver);
						lp.LoginIntoApp(URL,USERNAME,PASSWORD);
						
						Thread.sleep(2000);
						//click on campaign link
						
						HomePage hp=new HomePage(driver);
						hp.getCampaignsLink().click();
						
						/*driver.findElement(By.linkText("Campaigns")).click();*/
						
						//Click on Create Campaign button
						driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
						Thread.sleep(2000);
						driver.findElement(By.name("campaignName")).sendKeys(campaignName);
						WebElement targSize = driver.findElement(By.name("targetSize"));
						targSize.clear();
						targSize.sendKeys(targetsize);
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[@type='submit']")).click();
						//Verify the successful message
						WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
						
						wlib.waitForVisibilityOfElement(driver, toastMsg);
						
						String msg = toastMsg.getText();
						if(msg.contains(campaignName)) {
							System.out.println("Campaign created successfully");
						} else {
							System.out.println("Campaign not created");
						}
						WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
						wlib.ClickOnWebElement(driver, icon);
						driver.findElement(By.xpath("//div[text()='Logout ']")).click();
						driver.quit();
						
					    
	 
	}
	public void createCampaignWithExpectedDateTest() throws IOException, ParseException, Throwable{
		// TODO Auto-generated method stub
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.password_manager_leak_detection", false);
				settings.setExperimentalOption("prefs", prefs);

							    
				//Read the data from json File
						//Step 1.Create a java representation of physical file
						
								FileReader fr = new FileReader("./src/test/resources/commondata.json");
								
								//Step 2. create the object of JSONparser and use parse method to pass the object of physical file
							     JSONParser jp = new JSONParser();
							     Object javaObject = jp.parse(fr);
							     
							     //Step 3. Convert java object to Json by Downcasting
							    JSONObject obj = (JSONObject)javaObject;
							    
							    //Step http://4.Read data using get()
							   String BROWSER = obj.get("browser").toString();
							   String URL = obj.get("url").toString();
							   String USERNAME = obj.get("username").toString();
							   String PASSWORD = obj.get("password").toString();
							   System.out.println(URL);
							   WebDriverUtility wlib=new WebDriverUtility();
						
								
								WebDriver driver=null;
								//System.setProperty("webdriver.edge.driver", "C:\\Users\\sonur\\Downloads\\edgedriver_win64");
								if (BROWSER.equals("chrome")) {
									driver=new ChromeDriver(settings);
									
								} else if (BROWSER.equals("edge")) {
									driver=new EdgeDriver();
												
								}else if(BROWSER.equals("firefox"))
								{
									driver=new FirefoxDriver();
								}
						/*driver.manage().window().maximize();
						wlib.waitforpageToload(driver);
						driver.get(URL);
						driver.findElement(By.id("username")).sendKeys(USERNAME);
						driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
						driver.findElement(By.xpath("//button[@type='submit']")).click();*/
								
						//Navigate to login page
						LoginPage lp=new LoginPage(driver);
						lp.LoginIntoApp(URL,USERNAME,PASSWORD);
						
						//Click on campaign link
						
						HomePage hp=new HomePage(driver);
						hp.getCampaignsLink().click();
						
						/*driver.findElement(By.linkText("Campaigns")).click();*/						
							
					   //Create campaign with Mandatory fields
					   driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
					   driver.findElement(By.name("campaignName")).sendKeys("keerthi31");
						WebElement targSize = driver.findElement(By.name("targetSize"));
						targSize.clear();
						targSize.sendKeys("13323");
						
						//Get the date after 30 days
						//Create the object of date to get the todays date
								Date date = new Date();//import from java.util package
								
								//Format Date 
								SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
								sim.format(date);
								Calendar cal = sim.getCalendar();
								
								cal.add(Calendar.DAY_OF_MONTH, 30);
								String expectedDate = sim.format(cal.getTime());
								
								driver.findElement(By.name("expectedCloseDate")).sendKeys(expectedDate);
								Thread.sleep(2000);
								
								driver.findElement(By.xpath("//button[@type='submit']")).click();
								Thread.sleep(2000);
								
								//Verify the succesfull message
								WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
								Thread.sleep(2000);
								wlib.waitForVisibilityOfElement(driver, toastMsg);				
								  String msg = toastMsg.getText();
							      System.out.println(msg);
							      WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
									wlib.ClickOnWebElement(driver, icon);
									driver.findElement(By.xpath("//div[text()='Logout ']")).click();
									driver.quit();
	}

}
