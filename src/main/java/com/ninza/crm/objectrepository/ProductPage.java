package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	WebDriver driver;
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);// initelements() is used to create object or initialize the driver or to get the current address of the webelement 
	}
	
	@FindBy(xpath = "//span[text()='Add Product']")
	private WebElement addProductLink;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getAddProductLink() {
		return addProductLink;
	}
	
	

}
