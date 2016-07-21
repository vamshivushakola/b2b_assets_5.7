package com.generic.interfaceerror.logger;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;


/**
 * Bean called at the end of the hot-folder process to clean source file and move it to the archive/error folder This
 * custom implementation gather source line that failed during the process using the InterfaceerrorErrorManager
 * Singleton
 * 
 * @author Capgemini
 * @see de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupHelper
 */
public class InterfaceerrorCleanupHelper extends CleanupHelper
{

	/**
	 * Edit the local|project.properties to change logging behavior (properties 'log4j.*').
	 */
	private static final Logger LOG = Logger.getLogger(InterfaceerrorCleanupHelper.class);

	/**
	 * rearrange the error boolean corresponding to the InterfaceerrorErrorManager.SINGLETON and get sourcelines thanks
	 * to InterfaceerrorErrorManager {@inheritDoc}
	 */
	@Override
	public final void cleanup(final BatchHeader header, boolean error)
	{
		error = InterfaceerrorErrorManager.SINGLETON.getErrorLines(header.getFile().getName()).size() > 0;
		final File file = header.getFile();

		if (error)
		{
			try
			{
				InterfaceerrorErrorManager.SINGLETON.resolveOrigLines(file);
			}
			catch (UnsupportedEncodingException | FileNotFoundException e)
			{
				e.printStackTrace();
			}
			LOG.info(file.getName());
		}

		super.cleanup(header, error);

		InterfaceerrorErrorManager.SINGLETON.clearErrors(header.getFile().getName());
	}

}
