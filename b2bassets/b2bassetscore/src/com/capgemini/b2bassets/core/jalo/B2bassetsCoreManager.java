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
package com.capgemini.b2bassets.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.capgemini.b2bassets.core.constants.B2bassetsCoreConstants;
import com.capgemini.b2bassets.core.setup.CoreSystemSetup;


/**
 * Do not use, please use {@link CoreSystemSetup} instead.
 * 
 */
@SuppressWarnings("PMD")
public class B2bassetsCoreManager extends GeneratedB2bassetsCoreManager
{
	public static final B2bassetsCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (B2bassetsCoreManager) em.getExtension(B2bassetsCoreConstants.EXTENSIONNAME);
	}
}
