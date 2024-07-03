package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class ShoppingCartpageTest extends BaseTest{
	
	@BeforeClass
	
	public void ShoppingCartPgSetUp()
	{
		acctpage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@DataProvider()
	public Object[][] getProductData()
	{
		return new Object[][]
				{
			{"macbook", "MacBook Pro", "1"}
			
				};
	}
	
	
@Test(dataProvider="getProductData")
	
	public void shoppinngCartTitleTest(String searchkey, String productname,String value)
	{
		srchrespage=acctpage.doSearch(searchkey);
		prdinfopage=srchrespage.Selectproduct(productname);
		shpCartPg=prdinfopage.getaddToCart(value);
		Assert.assertEquals(shpCartPg.getShoppinngCartTitle(), AppConstants.SHOPPING_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
		Assert.assertEquals(shpCartPg.Verifyproductname(), productname,AppError.PRODUCT_NAME_MISMATCH);
		Assert.assertEquals(shpCartPg.cartTotal(), true,AppError.CART_TOTAL_MISMATCH);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
