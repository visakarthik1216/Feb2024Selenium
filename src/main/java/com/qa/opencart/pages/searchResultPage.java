package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class searchResultPage {
	
private WebDriver driver;
private ElementUtil eltUtil;

private By searchResult=By.cssSelector("div.product-thumb");
	
	
	public searchResultPage(WebDriver driver)
	{
		this.driver=driver;
		eltUtil =new ElementUtil(driver);
	}
	
	
	
	public int getSearchResultsCount()
	{
		List<WebElement> resultlist=eltUtil.waitVisibiltiyOfAllElet(searchResult,TimeUtil.DEFAULT_MEDIUM_TIME);
		int resultcount= resultlist.size();
		System.out.println("The product search result count" +resultcount);
		return resultcount;
	}
	
	public ProductInfoPage Selectproduct(String productname)
	{
		//eltUtil.doClick(By.linkText("productname"));
		eltUtil.doClick(By.linkText(productname),TimeUtil.DEFAULT_TIME);
		return new ProductInfoPage(driver);
	}

	
}
