/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.generic.multicarts.constants;

/**
 * Global class for all Multicarts constants. You can add global constants for your extension into this class.
 * 
 * @author Capgemini
 */
public final class MulticartsConstants extends GeneratedMulticartsConstants
{
	public static final String EXTENSIONNAME = "multicarts";

	/**
	 * MulticartsConstants constructor
	 */
	private MulticartsConstants()
	{
		//empty to avoid instantiating this constant class
	}

	/**
	 * implement here constants used by this extension
	 * 
	 */
	public static interface History
	{
		String CREATECART = "text.multicarts.log.createcart";
		String ADDPRODUCT = "text.multicarts.log.addproduct";
		String SETQUANTITY = "text.multicarts.log.setquantity";
		String REMOVEPRODUCT = "text.multicarts.log.removeproduct";
		String SETCARTNAME = "text.multicarts.log.setcartname";
	}

}
