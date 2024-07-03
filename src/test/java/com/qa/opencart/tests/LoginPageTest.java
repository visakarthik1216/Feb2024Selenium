package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {
	
	@Description("verifying login page Title test cahanges")
	@Owner("visa karthik")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actpagetitle=lp.getloginPageTitle();
		Assert.assertEquals(actpagetitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	@Description("verifying login page URL")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void loginPageURLTest()
	{
		String actpageURL=lp.getloginPageURL();
		Assert.assertTrue(actpageURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
	}
	
	@Description("verifying forgot pwdlink exist")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void loginForgotpwdlinkExistTest()
	{
		Assert.assertTrue(lp.checkForgotPwdLinkExist(),AppError.ELEMENT_NOT_FOUND);
		
	}
	
	@Description("verifying acctpage title is found or not")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void loginTest()
	{
		acctpage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(acctpage.getAcctPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}

}
