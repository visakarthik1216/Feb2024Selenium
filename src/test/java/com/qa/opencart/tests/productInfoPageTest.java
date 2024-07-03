
package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class productInfoPageTest extends BaseTest {
	
	@BeforeClass
	
	public void productInfoPgSetUp()
	{
		acctpage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac","iMac"},
			{"canon", "Canon EOS 5D"}
			
			
		};
	}	
	
	
	@Test(dataProvider="getProductData")
	
	public void productHeaderTest(String searchKey, String productName)
	{
		srchrespage=acctpage.doSearch(searchKey);
		prdinfopage=srchrespage.Selectproduct(productName);
		Assert.assertEquals(prdinfopage.getProductHeader(),productName, AppError.HEADER_NOT_FOUND);
	}
	
	@DataProvider
	public Object[][] getImageCountData()
	{
		return new Object[][] {
			{"macbook", "MacBook Pro",4},
			{"imac","iMac",3},
			{"canon", "Canon EOS 5D",3}
			
			
		};
	}	
	
	@Test(dataProvider="getImageCountData")
	
	public void ProductimgCountTest(String searchKey, String productName, int imageCount)
	{
		srchrespage=acctpage.doSearch(searchKey);
		prdinfopage=srchrespage.Selectproduct(productName);
		Assert.assertEquals(prdinfopage.getProductimageCount(),imageCount,AppError.IMAGES_COUNT_MISMATCHED);
	}
	
	
	

	
//	public void productMetaDataTest()
//	{
//		srchrespage=acctpage.doSearch("macbook");
//		prdinfopage=srchrespage.Selectproduct("MacBook Pro");
//		System.out.println(prdinfopage.getProductMetaData());
//	}
	
	@Test
	public void ProductMapInfoTest()
	{
		srchrespage=acctpage.doSearch("macbook");
		prdinfopage=srchrespage.Selectproduct("MacBook Pro");
		Map<String,String> productInfoMap=prdinfopage.getProductMapInfo();
		System.out.println("====product information map=====");
		System.out.println(productInfoMap);
		
		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"), "$2,000.00");
		
		softAssert.assertAll(); 
		
	}
	
     @Test
		public void addToCartTest()
	{
		srchrespage=acctpage.doSearch("macbook");
		prdinfopage=srchrespage.Selectproduct("MacBook Pro");
		shpCartPg=prdinfopage.getaddToCart("1");
		Assert.assertEquals(shpCartPg.getShoppinngCartTitle(),AppConstants.SHOPPING_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
		
	}
	

	//hardassert vs softassert
	//hard asser--single assertion
	//soft asset- multiple assertion
	//hard asser use Assert class -using class name we need to call Assertequals method
	//soft assert use Softassert class,using object we need to call asseertequals methods
	//hard assert stop executing the next line and stop terminating the prgrm if any of the assert fails
	//soft assert executes the entire program even if any of the assert fails and gives the  information for
    //the failed asserts using asserfall() function
	
	
	

}
