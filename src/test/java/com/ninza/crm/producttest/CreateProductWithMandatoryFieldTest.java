package com.ninza.crm.producttest;

//import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

public class CreateProductWithMandatoryFieldTest {
	public static void main(String[] args) throws Throwable {
		
		// TODO Auto-generated method stub
				ChromeOptions settings = new ChromeOptions();
				
				Map<String, Object> prefs = new HashMap<>();
					    prefs.put("profile.password_manager_leak_detection", false);
					    settings.setExperimentalOption("prefs", prefs);
						//Launch the browser
						//WebDriver driver = new ChromeDriver(settings);
	
						PropertyFileUtility pf=new PropertyFileUtility();
					    //get the values
						String BROWSER = pf.toGetDataFromPropertiesFile("browser");
		                String URL = pf.toGetDataFromPropertiesFile("url");
						String USERNAME = pf.toGetDataFromPropertiesFile("username");
						String PASSWORD = pf.toGetDataFromPropertiesFile("password");
						
						ExcelFileUtility ex=new ExcelFileUtility();
						JavaUtility ju = new JavaUtility();
						int ranNum = ju.getRandomNumber();
						String productName = ex.toReadTheDataFromExcel("Product", 1, 1)+ranNum;
						String qty = ex.toReadTheDataFromExcel("Product", 1,2);
						String price = ex.toReadTheDataFromExcel("Product",1, 3);
						
						WebDriverUtility wlib=new WebDriverUtility();
						
		WebDriver driver = null;
		//launch the browser
		if (BROWSER.equals("edge"))
		{
			driver = new EdgeDriver(); 
		} else if (BROWSER.equals("chrome")) 
		{ 
			driver = new ChromeDriver(settings);
		} 
		else if (BROWSER.equals("firefox")) 
		{ 
			driver = new FirefoxDriver(); 
		}
		
		//Maximize the window 
		driver.manage().window().maximize(); 
		wlib.waitforpageToload(driver);
		driver.get(URL); 
		
		LoginPage lp=new LoginPage(driver);
		lp.LoginIntoApp(URL,USERNAME,PASSWORD);
		
		//Click on product link 
		HomePage hp=new HomePage(driver);
		hp.getProductslink().click();
		
		//click add product button 
		driver.findElement(By.xpath("//span[text()='Add Product']")).click(); 
		//Create product 
		driver.findElement(By.name("productName")).sendKeys(productName); 
		WebElement quantity = driver.findElement(By.name("quantity")); 
		Thread.sleep(2000);
		quantity.clear(); 
		quantity.sendKeys(qty); 
		WebElement pricepp = driver.findElement(By.name("price")); 
		Thread.sleep(2000);
		pricepp.clear(); 
		pricepp.sendKeys(price);
		
		WebElement category = driver.findElement(By.name("productCategory")); 
		Select sel1= new Select(category); 
		sel1.selectByIndex(3); 
		WebElement vendor = driver.findElement(By.name("vendorId")); 
		Select sel2 = new Select(vendor); 
		sel2.selectByIndex(2); 
		driver.findElement(By.xpath("//button[@type='submit']")).click(); 
		Thread.sleep(800); 
		WebElement successMsg = driver.findElement(By.xpath("//div[text()='Product "+productName+" Successfully Added']")); 
		String msg = successMsg.getText(); 
        Thread.sleep(2000);
		System.out.println(msg);
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		wlib.ClickOnWebElement(driver, icon);
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		driver.quit();
	}

}
