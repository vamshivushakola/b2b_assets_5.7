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
package com.generic.multicarts.controllers;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;


/**
 * Class with constants for controllers.
 * 
 * @author Capgemini
 */
public interface MulticartsControllerConstants
{
	String _extname = "multicarts";
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
			String CartsListPage = _prefix + "cartsList";
			String CartsHistoryPage = _prefix + "cartHistory";
			String CartsLogs = _prefix + "cartLogs";
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
	 * Urls related constants
	 * 
	 */
	interface Urls
	{
		String RootUrl = "/multicarts";
		String CreateCart = "/createCart";
		String History = "/history/{cartCode}";
		String SelectCart = "/selectCart/{cartCode}";
		String DeleteCart = "/deleteCart/{cartCode}";
		String Share = "/share/{cartCode}";
		String UnShare = "/unshare/{cartCode}";
		String SetCartName = "/setCartName/{cartCode}";
		String follow = "/follow/{cartCode}";
		String unfollow = "/unfollow/{cartCode}";
		String logs = "/logs/{cartCode}";
	}

	/**
	 * Fragments related constants
	 * 
	 */
	interface Messages
	{

	}

	/**
	 * Other constants
	 * 
	 */
	interface Options
	{
		String share = "multicarts.share.enable";
		String autoShare = "multicarts.share.auto";
		String notification = "multicarts.notification.enable";
		String history = "multicarts.history.enable";
		String delete = "multicarts.delete.enable";
	}
}
