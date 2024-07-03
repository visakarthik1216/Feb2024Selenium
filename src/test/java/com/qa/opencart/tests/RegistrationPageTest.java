package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.StringUtil;

public class RegistrationPageTest extends BaseTest {
	
	
	
	@BeforeTest
	
	public void regSetUp()
	{
		regPage=lp.navigatetoRegister();
	}

	
	@DataProvider
	public Object[][] getUserRegdata()
	{
		return new Object[][] {
			{"John","harry","320945678","John01","No"},
			{"diana","jack","84892211","diana01","No"},
			{"mike","peter","982345789","mike01","yes"}
			
			
			
		};
		}
	
	
	
	@Test(dataProvider="getUserRegdata")
	public void userRegTest(String firstname, String lastname, String telephone, String password, String subscribe)
	{
		Assert.assertTrue(regPage.userRegister(firstname, lastname,StringUtil.randomEmail(),telephone,password,subscribe), AppError.USER_REG_NOT_DONE);
		//regPage.userRegister(firstname, lastname,StringUtil.randomEmail(),telephone,password,subscribe);
		
	}
}
