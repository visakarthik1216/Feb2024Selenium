package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {
	
	private WebDriver driver;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		jsUtil=new JavaScriptUtil(driver);
	}
	
	private void nullcheck(String Value)
	{
		if (Value==null)
		 {
			 throw new ElementException(" value is null" +Value);
		 }
		
	}
	
	@Step("entering the value in text field:{1} using locator :{0}")
	 public  void doSendkeys(By locator, String Value) //without wait
	   {
		nullcheck(Value);
		getElement(locator).clear();
		 getElement(locator).sendKeys(Value);
		   
	   }
	 
	 @Step("entering the value in text field:{1} using locator :{0} after waiting for element for {2} secs" )
	 public  void doSendkeys(By locator, String Value, int time) //with wait
	   {
		nullcheck(Value);
		webElementWaitVisible(locator, time).clear();
		webElementWaitVisible(locator, time).sendKeys(Value);
		
		   
	   }
	 
	 @Step("checking the state of locator {0} is displayed or not")
	 
	 public boolean doIsDisplayed(By Locator)
	 {
		 try
		 {
		 boolean flag=driver.findElement(Locator).isDisplayed();
		 System.out.println("Elemant is displayed" + Locator);
		 return flag;
		 }
		 catch(NoSuchElementException e)
		 {
			 System.out.println("Element with locator" +Locator+ "not displayed");
			 return false;
		 }
		 
		 
		
	 }
	 
	 private void highlightElement(WebElement element)
	 {
		 if(Boolean.parseBoolean(DriverFactory.highlight))
		   {
			   jsUtil.flash(element);
		   }
	 }
		

	   @Step("finding the element using locator : {0}")
	   public  WebElement getElement(By locator)
	   {
		  try {
		   WebElement element= driver.findElement(locator);
		   highlightElement(element);
		   return element;}
		  catch(NoSuchElementException e)
		  {
			  System.out.println(" element not present in the page" +locator);
			  e.printStackTrace();
			  return null;
			 
			  
		  }
		  
		  
	   }
	   
	   
	   public  List<WebElement> getElements(By locator)
	   {
		  try {
		   List<WebElement> element= driver.findElements(locator);
		   return element;}
		  catch(NoSuchElementException e)
		  {
			  System.out.println(" element not present in the page" +locator);
			  e.printStackTrace();
			  return null;
			 
			  
		  }
		  
		  
	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   @Step("clicking the element using locator :{0}")
	   
	   public void doClick(By locator) //withoutwait
		{
			getElement(locator).click();
		}
	   
	   @Step("clicking the element using locator :{0} after waiting for {1} secs")
	   public void doClick(By Locator, int time)  //with wait
		{
			webElementWaitVisible(Locator, time).click();
		}
	   
	   
	   public String doGetText(By Locator)
	   {
		   return getElement(Locator).getText();
	   }

	   public String doGetAttr(By Locator, String attribute)
	   {
		   return getElement(Locator).getAttribute(attribute);
	   }
	   
	//   *************************************************************************
	    
	  // action util
	   
	   
//	   public static void onesubmenu(By parentloc, By childloc) throws InterruptedException
//		{
//			Actions act=new Actions(driver);
//			act.moveToElement(getElement(parentloc)).perform();
//			Thread.sleep(5000);
//			doClick(childloc);
//			
//		}
	   
	   public  void DragandDrop(By sourceLoc, By targetLoc)
		{
			
			Actions act=new Actions(driver);
			act.dragAndDrop(getElement(sourceLoc), getElement(targetLoc)).perform();
		}
	   
	   public void DoActionsSendkeys(By locator, String keys)
		{
			Actions act=new Actions(driver);
			act.sendKeys(getElement(locator), keys).perform();
		}
		
		public  void DoActionsClick(By locator)
		{
			Actions act=new Actions(driver);
			act.click(getElement(locator)).perform();
		}
	   
		
		public  void DoActionprintletter(By Locator, String keys, long pausetime)
		{
			Actions act=new Actions(driver);
			char[] ch=keys.toCharArray();
			for(char c:ch)
			{
				
				act.sendKeys(getElement(Locator),String.valueOf(c)).pause(pausetime).perform();
			}
		}

		/**
		 * 
		 * @param Locator
		 * @param keys
		 */
		
		public  void DoActionprintletter(By Locator, String keys) //default pass time is 500
		{
			Actions act=new Actions(driver);
			char[] ch=keys.toCharArray();
			for(char c:ch)
			{
				
				act.sendKeys(getElement(Locator),String.valueOf(c)).pause(500).perform();
			}
		}
		
		public  void Dolevel4subelement(By level1, String level2,String level3,String level4) throws InterruptedException
		{
			doClick(level1);
			Thread.sleep(2000);
			Actions act1=new Actions(driver);
			act1.moveToElement(getElement(By.linkText(level2))).perform();
			Thread.sleep(2000);
			act1.moveToElement(getElement(By.linkText(level3))).perform();
			Thread.sleep(2000);
			doClick(By.linkText(level4));
		}
		
		//***********wait util******************************************
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 *  This does not necessarily mean that the element is visible.
		 * @param locator
		 * @param time
		 * @return
		 */
		public  WebElement webElementWait(By locator, int time)
		{
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(time));
			WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			highlightElement(element);
			return element;
		}
		
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible.
		 * Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
		 * @param locator
		 * @param time
		 * @return
		 */
		public  WebElement webElementWaitVisible(By locator, int time)
		{
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(time));
			WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			highlightElement(element);
			return element;
		}
		
		public  String waitForTitleContains(String partial,int time)
		{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
		
		try {
		if (wait.until(ExpectedConditions.titleContains(partial)))
		{
			return driver.getTitle();
					
		}
		}catch(Exception e1)
		{
			System.out.println("title not found");
		}
		return driver.getTitle();
		}
		
		@Step("waiting for the title and capturing it and displayed")
		public String waitForTitleTobe(String titlevalue,int time)
		{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
		
		try {
		if (wait.until(ExpectedConditions.titleIs(titlevalue)))
		{
			return driver.getTitle();
					
		}
		}catch(Exception e1)
		{
			System.out.println("title not found");
		}
		return driver.getTitle();
		}
		
		
		public  String waitForURLContains(String partial,int time)
		{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
		
		try {
		if (wait.until(ExpectedConditions.urlContains(partial)))
		{
			return driver.getCurrentUrl();
					
		}
		}catch(Exception e1)
		{
			System.out.println("URL not found");
		}
		return driver.getCurrentUrl();
		}
		
		public  String waitForURLtobe(String fullvalue,int time)
		{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
		
		try {
		if (wait.until(ExpectedConditions.urlToBe(fullvalue)))
		{
			return driver.getCurrentUrl();
					
		}
		}catch(Exception e1)
		{
			System.out.println("URL not found");
		}
		return driver.getCurrentUrl();
		}
		
		public  Alert waitForJSAlert(int time)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
			return wait.until(ExpectedConditions.alertIsPresent()); //checks whether alert is present as well it will switch to that alert.
			
		}
		
		public  String getAlertText(int time)
		{
			Alert alert=waitForJSAlert(time);
			String text=alert.getText();
			alert.accept();
			return text;
		
		}
		
		public  void acceptalert(int time)
		{
			waitForJSAlert(time).accept();
		}
		
		public void dismissalert(int time)
		{
			waitForJSAlert(time).dismiss();
		}
		
		public  void alertSendKeys(int time, String value)
		{
			Alert alert=waitForJSAlert(time);
			alert.sendKeys(value);
			alert.accept();
		}
		
		public  void waitForFramebyloc(By locator,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
			
		}
		
		public void waitForFramebyind(int index,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
			
		}
		
		public  void waitForFramebyidname(String idorname,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idorname));
			
		}
		
		public void waitForFramebyely(WebElement frameelt,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameelt));
			
		}
		
		/**
		 * wait for the browserwindow
		 * @param numberofwindows
		 * @param timeout
		 * @return
		 */
		
		public  Boolean waitForBrowser(int numberofwindows, int timeout)
		{
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			
		}
		
		// this says atlaest one element is visible in the web page (when it comes to list, there is no check on DOM)
		
		public  List<WebElement> waitPresenceOfAllElet(By locator, int duration)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(duration));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
	 
		//this says that ALL elments are present on the page that match the locator are visible
		//visibilty means height and width od webelt is greater than 0
		
		public List<WebElement> waitVisibiltiyOfAllElet(By locator, int duration)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(duration));
			try {
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));	
			}catch(Exception e)
			{
				return List.of();
			}
			
			
		}
		
		public WebElement waitVisibiltiyOfElet(By locator, int duration)
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(duration));
		
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
		}	
			
			
			
			
			
		}


		
		
		
		
	   
	   


