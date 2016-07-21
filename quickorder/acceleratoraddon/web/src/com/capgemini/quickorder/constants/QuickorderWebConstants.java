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
package com.capgemini.quickorder.constants;

/**
 * Global class for all Quickorder web constants. You can add global constants for your extension into this class.
 */
public final class QuickorderWebConstants
{
	private static final String PREFIX = QuickorderConstants.EXTENSIONNAME + "/";

	/**
	 * URI is inner class which contains web constants
	 * 
	 */
	public final class URI
	{
		public static final String SEARCH = PREFIX + "search";
		public static final String SOLRSEARCH = "search/autocomplete/SearchBox";
		public static final String ADD_TO_CART = PREFIX + "addToCart";
		public static final String UPLOAD = PREFIX + "uploadCsv";
		public static final String GetCSV = PREFIX + "getCsvExample";

		/**
		 * URI constructor
		 */
		private URI()
		{
		}
	}

	/**
	 * QuickorderWebConstants constructor
	 */
	private QuickorderWebConstants()
	{
		//empty to avoid instantiating this constant class
	}
}
