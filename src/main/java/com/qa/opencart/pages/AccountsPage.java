package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	
	private WebDriver driver;
	private ElementUtil eltutil;
	
	
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eltutil=new ElementUtil(driver);
	}
	
	
	By logoutlink=By.linkText("Logout");
	By headers=By.cssSelector("div#content h2");
	By search=By.name("search");
	By SearchBtn=By.cssSelector("div#search button");
	
	
	
	
	public String getAcctPageTitle()
	{
		
		String title=eltutil.waitForTitleTobe(AppConstants.ACCOUNT_PAGE_TITLE,TimeUtil.DEFAULT_TIME);
		System.out.println("the title of the page is" +title);
		return title;
	}
	
	public String getAcctPageURL()
	{
		String URL=eltutil.waitForURLContains(AppConstants.ACCOUNT_PAGE_FRACTION_URL,TimeUtil.DEFAULT_TIME);
		System.out.println("the URL of the page is" +URL);
		return URL;
	}
	
	public boolean isLogoutLinkExist()
	
	{
		return eltutil.doIsDisplayed(logoutlink);
		
	}
	
	public List<String> getAccpageHeaders()
	{
		List<WebElement> headerslist=eltutil.waitVisibiltiyOfAllElet(headers,TimeUtil.DEFAULT_MEDIUM_TIME);
		List<String> headersvallist=new ArrayList<String>();
		
		for(WebElement e:headerslist )
		{
			String text=e.getText();
			 headersvallist.add(text);
		}
		return headersvallist;
		
	}
	
	public boolean doSearchLinkExist()
	{
		return eltutil.doIsDisplayed(search);
		
	}
	
	public searchResultPage doSearch(String Searchkey)
	{
		if (doSearchLinkExist())
		{
		 eltutil.doSendkeys(search, Searchkey);
		 eltutil.doClick(SearchBtn);
		 System.out.println(" The product serach is " +Searchkey);
		return new searchResultPage(driver);
		}
		else
		{
			System.out.println("Search text field is not displayed");
			return null;
		}
	}
	
	
	
	
	
	
}
