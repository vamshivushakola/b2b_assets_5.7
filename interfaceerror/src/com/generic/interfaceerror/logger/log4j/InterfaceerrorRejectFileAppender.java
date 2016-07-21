package com.generic.interfaceerror.logger.log4j;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.spi.LoggingEvent;

import com.generic.interfaceerror.constants.InterfaceerrorConstants;
import com.generic.interfaceerror.logger.InterfaceerrorError;
import com.generic.interfaceerror.logger.InterfaceerrorErrorManager;


/**
 * InterfaceerrorRejectFileAppender
 * 
 * @author Capgemini
 */
public class InterfaceerrorRejectFileAppender extends InterfaceerrorDirectoryAppender
{

	@Override
	public final void append(final LoggingEvent loggingEvent)
	{

		String origFileName;
		String rejectFilePath;
		final Map<Integer, InterfaceerrorError> errorLines;
		SortedSet<Integer> keys;
		int i;
		StringBuilder sb;

		origFileName = (String) loggingEvent.getMessage();
		rejectFilePath = super.suffixFileName(origFileName, InterfaceerrorConstants.Logger.REJECT_SUFFIX);
		errorLines = InterfaceerrorErrorManager.SINGLETON.getErrorLines(origFileName);
		keys = new TreeSet<>(errorLines.keySet());
		i = 1;

		for (final Integer lineNb : keys)
		{
			final InterfaceerrorError impexError = errorLines.get(lineNb);

			sb = new StringBuilder();
			impexError.setRejectLineNb(i);
			impexError.setRejectFileName(rejectFilePath);

			sb.append(impexError.getOrigLine());

			super.setFile(rejectFilePath);
			super.activateOptions();

			//building another logging Event make us able to modify the message
			final LoggingEvent modifiedEvent = modifyMsg(loggingEvent, sb.toString(), this.getClass().getName());

			super.append(modifiedEvent);
			i++;
		}
	}
}
