package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class Demopage {
	
	By demo=By.id("demo");
	By change=By.id("changed");
	
	public void getDemo()
	{
		System.out.println("printing demo");
	}
	
	public void change()
	{
		System.out.println("changes done");
	}

}
