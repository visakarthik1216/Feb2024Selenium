package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eltutil;
	
	//1.page objects: creat private by locator 
	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By forgotpwdlink=By.linkText("Forgotten Password");
	private By loginbtn=By.xpath("//input[@value='Login']");
	private By registerLink=By.linkText("Register");
	
	
	//2. public constructor of the page
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eltutil=new ElementUtil(driver);
		
	}
	
			
			
	//3 public page action/methods
	
	@Step("get login page title")
	public String getloginPageTitle()
	{
		
		String title=eltutil.waitForTitleTobe(AppConstants.LOGIN_PAGE_TITLE,TimeUtil.DEFAULT_TIME);
		System.out.println("the title of the page is" +title);
		return title;
	}
	
	@Step("get login page URL")
	public String getloginPageURL()
	{
		
		String URL=eltutil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL,TimeUtil.DEFAULT_TIME);
		System.out.println("the URL of the page is" +URL);
		return URL;
	}
	
	@Step("checking the state of forgot paswd link")
	public boolean checkForgotPwdLinkExist()
	{
		return eltutil.doIsDisplayed(forgotpwdlink);
		
	}
	
	
	@Step("login to application with username: {0} and password:{1}")
	public AccountsPage doLogin(String username, String pswd)
	{
		eltutil.doSendkeys(emailId,username ,TimeUtil.DEFAULT_MEDIUM_TIME);
		eltutil.doSendkeys(password, pswd);
		eltutil.doClick(loginbtn);
		return new AccountsPage(driver);

	}
	
	@Step("navigation to user regisitration page")
	
	public RegisterPage navigatetoRegister()
	{
		eltutil.doClick(registerLink, TimeUtil.DEFAULT_TIME);
		return new RegisterPage(driver);
	}
	
	
	
	

}
