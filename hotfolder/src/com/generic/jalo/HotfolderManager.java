package com.generic.jalo;

import com.generic.constants.HotfolderConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class HotfolderManager extends GeneratedHotfolderManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( HotfolderManager.class.getName() );
	
	public static final HotfolderManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (HotfolderManager) em.getExtension(HotfolderConstants.EXTENSIONNAME);
	}
	
}
