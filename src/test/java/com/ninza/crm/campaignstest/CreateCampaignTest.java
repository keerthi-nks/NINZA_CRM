package com.ninza.crm.campaignstest;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninza.crm.baseclass.BaseClass;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.CampaignsPage;
import com.ninza.crm.objectrepository.CreateCampaignsPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;
@Listeners(com.ninza.crm.listenerUtility.ListenerImplementation.class)

public class CreateCampaignTest extends BaseClass  {
	@Test(groups = {"smoke","Regression"})
	public void createCampaignWithMandatoryFieldsTest() throws IOException, Throwable{
		
		String campaignName = ex.toReadTheDataFromExcel("camp", 1, 1);
		String targetsize = ex.toReadTheDataFromExcel("camp", 1,2);			
	
		//click on campaign link in home page
		HomePage hp=new HomePage(driver);
		hp.getCampaignsLink().click();

		// Click on create campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaignbtn().click();
		
		// Create campaign with Mandatory fields
		CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetsize);
		ccp.getCreateButton().click();
		
		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));  //	wlib.waitForVisibilityOfElement(driver, toastMsg);
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		String msg = toastMsg.getText();
//		if(msg.contains(campaignName)) {
//			System.out.println("Campaign created successfully");
//		} else {
//			System.out.println("Campaign not created");
//		}
		Assert.assertTrue(msg.contains("Successfully Added"));
		hp.getCloseToastMsgBtn().click();
}
	
	@Test(groups = "Regression")
	
	public void CreateCampaignWithExpectedDateTest() throws Throwable {
		
		String campaignName = ex.toReadTheDataFromExcel("camp", 1, 1);
		String targetsize = ex.toReadTheDataFromExcel("camp", 1,2);	
			
		//Click on campaign link		
		HomePage hp=new HomePage(driver);
		hp.getCampaignsLink().click();	
		
		// Click on create campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaignbtn().click();
							
		//Create campaign with Mandatory fields
		CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetsize);
		//ccp.getCreateButton().click();

						
		// Get the date after 30 days
		JavaUtility ju = new JavaUtility();
		String expectedDate = ju.getRequireDate(30);
		ccp.getExpectedCloseDate().sendKeys(expectedDate);
		ccp.getCreateButton().click();
		
		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		String msg = toastMsg.getText();
//		if (msg.contains("Successfully Added")) {
//			System.out.println("Campaign created successfully");
//		} else {
//			System.out.println("Campaign is not created");
//		}
		Assert.assertTrue(msg.contains("Successfully Added"));
		hp.getCloseToastMsgBtn().click();
		hp.logout();
		driver.quit();
	}
}
