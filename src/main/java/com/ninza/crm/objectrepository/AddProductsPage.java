package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddProductsPage {

	WebDriver driver;
	public AddProductsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);// initelements() is used to create object or initialize the driver or to get the current address of the webelement 
	}
	
	@FindBy(name = "productName")
	private WebElement productName;
	
	@FindBy(name = "quantity")
	private WebElement quantity;
	
	public WebElement getProductName() {
		return productName;
	}
	public WebElement getQuantity() {
		return quantity;
	}
	public WebElement getPrice() {
		return price;
	}

	@FindBy(name = "price")
	private WebElement price;
	
	@FindBy(name ="productCategory" )
	private WebElement productCategory;
	
	public WebElement getProductCategory() {
		return productCategory;
	}
	public WebElement getVendorId() {
		return vendorId;
	}

	@FindBy(name = "vendorId")
	private WebElement vendorId;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;
	
	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	public void selectCategory()
	{
		Select sel1= new Select(productCategory); 
		sel1.selectByIndex(3);
	}
	public void selectVendorId()
	{
		Select sel2 = new Select(vendorId); 
		sel2.selectByIndex(2);
	}

	
	
}
