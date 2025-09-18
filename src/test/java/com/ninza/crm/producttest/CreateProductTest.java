package com.ninza.crm.producttest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninza.crm.baseclass.BaseClass;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.AddProductsPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;
import com.ninza.crm.objectrepository.ProductPage;

@Listeners(com.ninza.crm.listenerUtility.ListenerImplementation.class)

public class CreateProductTest extends BaseClass{
  @Test(groups = "Regression")
  public void createProductWithMandatoryFieldTest() throws IOException, Throwable {
	int ranNum = ju.getRandomNumber();
	String productName = ex.toReadTheDataFromExcel("Product", 1, 1)+ranNum;
	String qty = ex.toReadTheDataFromExcel("Product", 1,2);
	String price = ex.toReadTheDataFromExcel("Product",1, 3);

    //Click on product link 
    HomePage hp=new HomePage(driver);
    hp.getProductslink().click();

    //click add product button 
	ProductPage pp = new ProductPage(driver);
	pp.getAddProductLink().click();

 
    //Create product 	
	AddProductsPage app=new AddProductsPage(driver);
	app.getProductName().sendKeys(productName);
	WebElement quantity =app.getQuantity();
	quantity.clear();
	quantity.sendKeys(qty);
	WebElement pricepp = app.getPrice();
	pricepp.clear();
	pricepp.sendKeys(price);
	
	app.selectCategory();
	app.selectVendorId();
	app.getSubmitBtn().click();
	
	// Verify the succesfull message
	WebElement toastMsg = hp.getToastMsg();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(toastMsg));

	String msg = toastMsg.getText();

	if (msg.contains("Successfully Added")) {
		System.out.println("Product created successfully");
	} else {
		System.out.println("Product is not created");
	}
	hp.getCloseToastMsgBtn().click();
	hp.logout();
	driver.quit();
	
//WebElement successMsg = driver.findElement(By.xpath("//div[text()='Product "+productName+" Successfully Added']")); 
//String msg = successMsg.getText(); 
//Thread.sleep(2000);
//System.out.println(msg);
//WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
//wlib.ClickOnWebElement(driver, icon);
//driver.findElement(By.xpath("//div[text()='Logout ']")).click();
//driver.quit();
  }
}
