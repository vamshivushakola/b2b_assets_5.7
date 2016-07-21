package com.capgemini.quickorder.jalo;

import com.capgemini.quickorder.constants.QuickorderConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class QuickorderManager extends GeneratedQuickorderManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( QuickorderManager.class.getName() );
	
	public static final QuickorderManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (QuickorderManager) em.getExtension(QuickorderConstants.EXTENSIONNAME);
	}
	
}
