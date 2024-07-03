package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accSetUP()
	{
		acctpage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test
	public void accPageTitleTest()
	{
		Assert.assertEquals(acctpage.getAcctPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	
	public void accPageURLTest()
	{
		Assert.assertTrue(acctpage.getAcctPageURL().contains(AppConstants.ACCOUNT_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
		
	}
	
	
	@Test
	
	public void accPageHeadersTest()
	{
		List<String> Headerslist=acctpage.getAccpageHeaders();
		Assert.assertEquals(Headerslist, AppConstants.ACCOUNT_PAGE_HEADERS,AppError.HEADERS_LIST_NOTMATCHED);
	}
	
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2},
			{"airtel",0}
		};
		
		}
	

	
	@Test(dataProvider="getSearchData")
	public void searchTest(String searchkey,int resultscount)
	{
		srchrespage=acctpage.doSearch(searchkey);
		Assert.assertEquals(srchrespage.getSearchResultsCount(),resultscount,AppError.RESULT_COUNT_NOT_MATCHED);
	}
	
	
	
	

}
