package com.ninza.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitforpageToload(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	}
	public void waitForVisibilityOfElement(WebDriver driver,WebElement element){
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToFrame(WebDriver driver,int index){
		driver.switchTo().frame(index);
		
	}
	public void switchToFrame(WebDriver driver,String nameorid){
		driver.switchTo().frame(nameorid);
	}
	public void switchToFrame(WebDriver driver,WebElement Frameelement){
		driver.switchTo().frame(Frameelement);
	}
    public void select(WebElement element, int index){
    	Select sel=new Select(element);
    	sel.selectByIndex(index);
    }
    public void select(WebElement element,String value){
    	Select sel=new Select(element);
    	sel.selectByValue(value);
    }
    public void select(String text,WebElement element){
    	Select sel=new Select(element);
    	sel.selectByContainsVisibleText(text);
    }
    public void switchToAlertAndAccept(WebDriver driver ){
    	driver.switchTo().alert().accept();
    }
    public void switchToAlertAndDismiss(WebDriver driver){
    	driver.switchTo().alert().dismiss();
    }
    public String switchToAlertAndGetText(WebDriver driver) {
    	String text=driver.switchTo().alert().getText();
    	return text;
    }
    public void switchToAlertAndSendKeys(WebDriver driver,String text) {
    	driver.switchTo().alert().sendKeys(text);
    }
    public void mouseHoverOnWebElement(WebDriver driver,WebElement element) {
    	Actions act=new Actions(driver);
    	act.moveToElement(element).perform();
    }
    public void ClickOnWebElement(WebDriver driver,WebElement element) {
    	Actions act=new Actions(driver);
    	act.moveToElement(element).click().perform();
    }
    public void switchToWindow(WebDriver driver) {
    	String parent = driver.getWindowHandle();
    	Set<String> allwindowId = driver.getWindowHandles();
    	allwindowId.remove(parent);
    	for(String id:allwindowId) {
    		driver.switchTo().window(id);
    	}
    }

}
