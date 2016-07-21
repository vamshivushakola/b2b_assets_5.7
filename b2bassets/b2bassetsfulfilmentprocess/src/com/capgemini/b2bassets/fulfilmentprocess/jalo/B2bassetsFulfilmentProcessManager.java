/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.capgemini.b2bassets.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.capgemini.b2bassets.fulfilmentprocess.constants.B2bassetsFulfilmentProcessConstants;

import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class B2bassetsFulfilmentProcessManager extends GeneratedB2bassetsFulfilmentProcessManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( B2bassetsFulfilmentProcessManager.class.getName() );
	
	public static final B2bassetsFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (B2bassetsFulfilmentProcessManager) em.getExtension(B2bassetsFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
