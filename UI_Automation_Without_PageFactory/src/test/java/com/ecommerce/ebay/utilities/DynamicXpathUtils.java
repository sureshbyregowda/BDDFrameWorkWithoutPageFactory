package com.ecommerce.ebay.utilities;

public class DynamicXpathUtils {
	
	private DynamicXpathUtils()
	{
		
	}
	
	public static String getXpath(String xpath, String value)
	{
		return xpath.replace("%replaceable%",value);
	}

}
