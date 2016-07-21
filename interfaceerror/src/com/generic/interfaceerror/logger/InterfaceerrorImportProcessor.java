package com.generic.interfaceerror.logger;

import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.impex.jalo.imp.MultiThreadedImportProcessor;
import de.hybris.platform.impex.jalo.imp.ValueLine;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;

import com.generic.interfaceerror.constants.InterfaceerrorConstants;


/**
 * Customized import processor used to log the unresolved lines of the original csv files in a specialized log file.
 * 
 * @author Capgemini
 * @see de.hybris.platform.impex.jalo.imp.ImportProcessor
 */

public class InterfaceerrorImportProcessor extends MultiThreadedImportProcessor
{


	/**
	 * Processes a line read in the ImpEx file and converts it to {@link de.hybris.platform.jalo.Item} object to be
	 * persisted in the database.
	 * 
	 * @param valueLine
	 *           The line read in the ImpEx file.
	 * @return The resulting {@link de.hybris.platform.jalo.Item} object.
	 */

	@Override
	public final Item processItemData(final ValueLine valueLine)
	{
		Item result = null;
		try
		{
			result = super.processItemData(valueLine);
			if (valueLine.isUnresolved())
			{
				logUnresolvedLine(valueLine);
			}
		}
		catch (final ImpExException e)
		{
			logImpExException(valueLine, e);
		}
		catch (final JaloInvalidParameterException e)
		{
			logErrorLine(valueLine, e.getMessage());
		}
		return result;
	}

	/**
	 * log unresolved line in the error manager singleton
	 * 
	 * @param valueLine
	 *           the impex line
	 */
	private void logUnresolvedLine(final ValueLine valueLine)
	{
		logErrorLine(valueLine, valueLine.getUnresolvedReason());
	}

	/**
	 * log impexexception in the error manager singleton
	 * 
	 * @param valueLine
	 *           the impex line
	 * @param e
	 *           the Impex exception
	 */
	private void logImpExException(final ValueLine valueLine, final ImpExException e)
	{
		logErrorLine(valueLine, e.getMessage());
	}

	/**
	 * get the error line and the file name in the impexRow nb : the line number is sometimes prefixed with "<ignore>"
	 * 
	 * @param valueLine
	 *           the impex row
	 * @param errorMessage
	 *           the error message
	 */
	private void logErrorLine(final ValueLine valueLine, final String errorMessage)
	{
		final String lineNb = valueLine.getValueEntry(InterfaceerrorConstants.Impex.IMPEX_LINE_NUMBER_IND).getCellValue();
		InterfaceerrorErrorManager.SINGLETON.addErrorLine(
				valueLine.getValueEntry(InterfaceerrorConstants.Impex.IMPEX_FILE_NAME_IND).getCellValue()
						.replaceFirst(InterfaceerrorConstants.Impex.IGNORE_TAG, ""), errorMessage,
				Integer.parseInt(lineNb.replaceFirst(InterfaceerrorConstants.Impex.IGNORE_TAG, "")));

	}
}
