package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.searchResultPage;
import com.qa.opencart.pages.shoppingCartPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected LoginPage lp;
	protected Properties prop;
	protected AccountsPage acctpage;
	protected searchResultPage srchrespage;
	protected ProductInfoPage prdinfopage;
	protected shoppingCartPage shpCartPg;
	protected SoftAssert softAssert;
	protected RegisterPage regPage;
	
	
	@Parameters({"browser"})
	@BeforeTest()
	public void setUp(@ Optional String browsername) //optional is given if we dont want to run from xml, and if we want to run from particular test class, in that case default browser will run
	{
		df=new DriverFactory();
		prop=df.initProp();
		
		if(browsername!=null)
		{
			prop.setProperty("browser",browsername);
			
		}
		
		driver=df.initDriver(prop);
		lp=new LoginPage(driver);
		softAssert=new SoftAssert();
		
	}
	
	@AfterTest()
	public void tearDown()
	{
		driver.quit();
	}
	
	

}
