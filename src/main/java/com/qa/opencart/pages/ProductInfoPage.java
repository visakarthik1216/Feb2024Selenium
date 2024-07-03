package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	
		private WebDriver driver;
		private ElementUtil eltUtil;
        private By header=By.cssSelector("div#content h1");
        private By productimagecount=By.cssSelector("a.thumbnail");
        private By quantity=By.xpath("//div[@class='form-group']/input[@name='quantity']");
        private By shoppingcartlink=By.linkText("shopping cart");
        private By addToCart=By.cssSelector("div.form-group button");
        private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
        private By productPrice=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
        
        private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver)
		{
			this.driver=driver;
			eltUtil =new ElementUtil(driver);
		}
	
	public String getProductHeader()
	{
		String headertext=eltUtil.doGetText(header);
		System.out.println("product header is"+headertext);
		return headertext;
	}
	
	public int getProductimageCount()
	{
		int imagescount=eltUtil.waitVisibiltiyOfAllElet(productimagecount,TimeUtil.DEFAULT_MEDIUM_TIME).size();
		System.out.println("total images" +imagescount);
		return imagescount;
	}
	
	public  shoppingCartPage getaddToCart(String qtyvalue)
	{
		
		eltUtil.doSendkeys(quantity,qtyvalue);
		eltUtil.doClick(addToCart, TimeUtil.DEFAULT_MEDIUM_TIME);
		eltUtil.doClick(shoppingcartlink, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println(" The quanity added is "+qtyvalue);
		return new shoppingCartPage(driver);
	}
	
//	public List<String> getProductMetaData()
//	{
//	 // List<WebElement> ProdMetaList=driver.findElements(productMetaData);
//		List<WebElement> ProdMetaList=eltUtil.getElements(productMetaData);
//	  List<String> ProdMetatextList=new ArrayList<String>();
//	  for(WebElement e:ProdMetaList)
//	  {
//		  String matatext=e.getText();
//		  ProdMetatextList.add(matatext);
//	  }
//	  return ProdMetatextList;
//	}
	
	
	public Map<String,String> getProductMapInfo()
	{
		productMap=new HashMap<String,String>();
		getProductMetaData();
		getProductPriceData();
		productMap.put("productname",getProductHeader());
		productMap.put("productimagescount",String.valueOf(getProductimageCount()));
				
		return productMap;
	}
	
	public void getProductMetaData()
	{

	List<WebElement> ProdMetaList=eltUtil.getElements(productMetaData);
	productMap=new HashMap<String,String>();
	  for(WebElement e:ProdMetaList)
	  {
		  String metaData=e.getText();
		 String meta[]= metaData.split(":");
		 String metaKey=meta[0].trim();
		 String metavalue=meta[1].trim();
		 productMap.put(metaKey, metavalue);
	  }
	}
	
	public void getProductPriceData()
	{
		List<WebElement> PriceList=eltUtil.getElements(productPrice);
		String productprice=PriceList.get(0).getText();
		String exTaxPrice=PriceList.get(1).getText().split(":")[1].trim();
		 productMap.put("productprice",productprice);
		 productMap.put("exTaxPrice",exTaxPrice);
	}
		  
	
		  	
	
}


