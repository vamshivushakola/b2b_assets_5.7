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
package com.generic.ordersearch.controllers;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;

/**
 */
public interface OrdersearchControllerConstants
{
   String _extname = "ordersearch";
   String _prefix = "addon:/" + _extname + "/" + AbstractAddOnPageController.PAGE_ROOT;

   /**
    * Class with view name constants
    */
   interface Views
   {
       interface Cms
       {
           //
       }

       interface Pages
       {
           String OrderSearchPage = _prefix + "orderSearch";
       }

       interface Fragments
       {
           //
       }
   }

   interface Urls
   {
   	String RootUrl = "/orderSearch";
   	String SearchOrder = "/search";
   }
   interface Messages
   {

   }

   interface Fields
   {
   	String fields = "ordersearch.search.fields";
   }
}
