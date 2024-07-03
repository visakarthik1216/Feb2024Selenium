package com.qa.opencart.utils;

public class StringUtil {
	
	public static String randomEmail()
	{
		String emailid="userauto"+ System.currentTimeMillis()+"@opencart.com";
		return emailid;
	}

}
