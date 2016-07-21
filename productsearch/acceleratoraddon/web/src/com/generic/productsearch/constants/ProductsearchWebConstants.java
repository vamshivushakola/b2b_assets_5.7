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
package com.generic.productsearch.constants;



/**
 * Global class for all Productsearch web constants. You can add global constants for your extension into this class.
 */
public final class ProductsearchWebConstants
{
	//Dummy field to avoid pmd error - delete when you add the first real constant!
	private static final String PREFIX = ProductsearchConstants.EXTENSIONNAME + "/";

	/**
	 * URI is inner class which contains web constants
	 */
	public final class URI
	{
		public static final String SEARCH = PREFIX + "search";

		/**
		 * URI constructor
		 */
		private URI()
		{
		}
	}

	/**
	 * ProductsearchWebConstants constructor
	 */
	private ProductsearchWebConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension
}
