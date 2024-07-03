package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserExceptions;
import com.qa.opencart.exceptions.FrameException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager op;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	/** this is used to init the driver using the browser name
	 * 
	 * @param prop
	 */
	
	
	public WebDriver initDriver(Properties prop) //write cross browser logic
	
	{
		String browsername=prop.getProperty("browser");
		System.out.println("browser name is "+browsername);
		highlight=prop.getProperty("highlight");
		op=new OptionsManager(prop);
		
		switch(browsername.toLowerCase().trim()) {
		case "chrome":
			//driver=new ChromeDriver();
			tlDriver.set(new ChromeDriver(op.getChromeOptions()));
			break;
			
		case "edge":
			//driver=new EdgeDriver();
			tlDriver.set(new EdgeDriver(op.getEdgeOptions()));
			break;
		default:
			System.out.println("please pass the right browser name" +browsername);
			throw new BrowserExceptions(AppError.BROWSER_NOT_FOUND);
	
		
	}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
		
		
	}
	
	/**
	 * get the local copy of the driver
	 * @return
	 */
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
		
	
	public Properties initProp()
	{
		prop=new Properties();
		FileInputStream ip =null;
		
		// mvn clean install -Denv="qa"
		//mvn clean install
		String envName=System.getProperty("env");
		System.out.println("running the test suite on the env----->"+envName);
		if (envName==null)
		{
			System.out.println("env is null so running in qa env");
			try {
				ip=new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			}
		}
		else
		{
		
		
		try {
		switch(envName.trim().toLowerCase())
		{
		case "qa":
			ip=new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			break;
		case "dev":
			ip=new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
			break;
		case "uat":
			 ip=new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
			break;
		case "stage":
			 ip=new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
			break;
		case "prod":
			ip=new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			break;	
			
		default:
			System.out.println("please pass the right environment" +envName);
			throw new FrameException("wrong env passed");
		
		
		
		}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		}
		
		try {
				prop.load(ip);
			} catch (IOException e) {
			e.printStackTrace();
			}
		
		return prop;
	}
	
	public static String getScreenshot(String methodName)
	{
		File srcfile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); //this file wil be stored in temp location in the memory, but we need to store in some accesible location under screenshot folder
		String path=System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileHandler.copy(srcfile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		
	}

	
	
	}
	
	


