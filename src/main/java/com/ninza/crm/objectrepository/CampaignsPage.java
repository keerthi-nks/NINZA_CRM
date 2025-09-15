package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	WebDriver driver;
	public CampaignsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);// initelements() is used to create object or initialize the driver or to get the current address of the webelement 
	}
	
	@FindBy(xpath = "//span[text()='Create Campaign']")
	private WebElement createCampaignbtn;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCreateCampaignbtn() {
		return createCampaignbtn;
	}
	
	

}
