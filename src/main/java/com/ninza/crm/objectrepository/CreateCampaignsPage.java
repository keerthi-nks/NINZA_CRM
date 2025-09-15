package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignsPage {
	WebDriver driver;
	public CreateCampaignsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);// initelements() is used to create object or initialize the driver or to get the current address of the webelement 
	}
	
	@FindBy(name = "campaignName")
	private WebElement campaignName;
	
	@FindBy(name = "targetSize")
	private WebElement targetSize;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement CreateButton;
	
	@FindBy(name = "expectedCloseDate")
	private WebElement ExpectedCloseDate;
	
	public WebElement getExpectedCloseDate() {
		return ExpectedCloseDate;
	}

	public WebElement getCreateButton() {
		return CreateButton;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCampaignName() {
		return campaignName;
	}

	public WebElement getTargetSize() {
		return targetSize;
	}
	
	

}
