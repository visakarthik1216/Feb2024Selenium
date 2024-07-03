package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eltutil;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		eltutil=new ElementUtil(driver);
	}
	
	By firstName=By.id("input-firstname");
	By lastName=By.id("input-lastname");
	By email=By.id("input-email");
	By telephone=By.id("input-telephone");
	By password=By.id("input-password");
	By confirmpswd=By.id("input-confirm");
	
	By subscribeYes=By.xpath("(//div[@class='form-group']//label/input)[1]");
	By subscribeNo=By.xpath("(//div[@class='form-group']//label/input)[2]");
	
	By PrivacyCheck=By.xpath("//input[@name='agree']");
	By continueBtn=By.xpath("//input[@value='Continue']");
	
	By successMsg=By.xpath("//div[@id='content']/h1");
	By logout=By.linkText("Logout");
	By Register=By.linkText("Register");
	
	public boolean userRegister(String firstname, String lastname,String email, String telephone, String password,String subscribe)
	{
		eltutil.doSendkeys(this.firstName,firstname ,TimeUtil.DEFAULT_MEDIUM_TIME);
		eltutil.doSendkeys(this.lastName,lastname);
		eltutil.doSendkeys(this.email, email);
		eltutil.doSendkeys(this.telephone, telephone);
		eltutil.doSendkeys(this.password, password);
		eltutil.doSendkeys(this.confirmpswd, password);
		
		if(subscribe.equalsIgnoreCase("Yes"))
		{
			eltutil.doClick(subscribeYes);
		}
		else
		{
			eltutil.doClick(subscribeNo);
		}
		
		eltutil.doClick(PrivacyCheck);
		eltutil.doClick(continueBtn);
		
		
		String SuccessMesg=eltutil.waitVisibiltiyOfElet(successMsg, TimeUtil.DEFAULT_MEDIUM_TIME).getText();
		System.out.println(SuccessMesg);
		if(SuccessMesg.contains(AppConstants.REG_SUCCESS_MSG))
		{
			eltutil.doClick(logout);
			eltutil.doClick(Register);
			return true;
		}
		else
		{
			return false;
		}
		
		}
		
	}
	
	


