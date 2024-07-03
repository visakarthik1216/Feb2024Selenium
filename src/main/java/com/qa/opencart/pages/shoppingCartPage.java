package com.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;



public class shoppingCartPage
{
			
	private WebDriver driver;
	private ElementUtil eltUtil;

	
	
	By productname=By.xpath("(//div[@class='table-responsive']//tr)[2]/td[@class='text-left']/a");
	private By Qtyvalue=By.xpath("//input[contains(@name,'quantity')]");
	private By unitPrice=By.xpath("((//div[@class='table-responsive']//tr)[2]/td[@class='text-right'])[1]");
	private By Totalprice=By.xpath("((//div[@class='table-responsive']//tr)[2]/td[@class='text-right'])[2]");
   
  
    
	public shoppingCartPage(WebDriver driver)
	{
		this.driver=driver;
		eltUtil =new ElementUtil(driver);
	}
	
   public String getShoppinngCartTitle()
   {
	  String Shopping_cart_title= eltUtil.waitForTitleTobe(AppConstants.SHOPPING_PAGE_TITLE,TimeUtil.DEFAULT_MEDIUM_TIME);
	  System.out.println("Shopping cart title is"+Shopping_cart_title);
	   return Shopping_cart_title;
	   
   }
   

   
   public String Verifyproductname()
   {
	   String cartproduct=eltUtil.doGetText(productname);
	  System.out.println(" The product name in the cart is " +cartproduct);
	  return cartproduct;
	
	 
   }
   
 

   
   public boolean cartTotal()
   {
	   
	 String quantity=eltUtil.getElement(Qtyvalue).getAttribute("value");
	 System.out.println(" the cart quantity is " +quantity);
	 quantity=quantity.replace("$", "").replace(",","");
	 double cartqty=Double.parseDouble(quantity);
	 
	 String cartUnit=eltUtil.getElement(unitPrice).getText();
	 cartUnit=cartUnit.replace("$", "").replace(",","");
	 double cartUnitPrice=Double.parseDouble(cartUnit);
	 
	 String total1=eltUtil.getElement(Totalprice).getText();
	 total1=total1.replace("$", "").replace(",","");
	 double Expectedtotal=Double.parseDouble(total1);
	 
	 
	 
	 double actualtotalprice= cartqty*cartUnitPrice;
	 System.out.println("the actual total price in cart is" + actualtotalprice);
	
	 
	 if (actualtotalprice==Expectedtotal)
	 {
		 return true;
		 
	 }
	 else
	 {
		 return false;
	 }
	
   }
	 

   

}

