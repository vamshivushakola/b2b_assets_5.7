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
package com.capgemini.quickorder.controllers;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;



/**
 * Class with constants for controllers.
 * 
 * @author svc-in-ecommusr
 * 
 */
public interface QuickorderControllerConstants
{
	// implement here controller constants used by this extension
	String _extname = "quickorder";
	String _prefix = "addon:/" + _extname + "/" + AbstractAddOnPageController.PAGE_ROOT;

	/**
	 * Class with view name constants
	 */
	interface Views
	{
		/**
		 * Cms related constants
		 * 
		 */
		interface Cms
		{
			//
		}

		/**
		 * Pages related constants
		 * 
		 */
		interface Pages
		{
			String QuickOrderCsv = _prefix + "quickOrderCsv";
		}

		/**
		 * Fragments related constants
		 * 
		 */
		interface Fragments
		{
			//
		}
	}

	/**
	 * Messages related constants
	 * 
	 */
	interface Messages
	{
		//
	}
}
