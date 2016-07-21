package com.generic.multicarts.helper;


import com.generic.multicarts.data.CartLogData;


/**
 * LogFormat
 * 
 * @author Capgemini
 */
public final class LogFormat
{

	/**
	 * LogFormat constructor
	 */
	private LogFormat()
	{
		//empty to avoid instantiating this helper class
	}

	/**
	 * format Log
	 * 
	 * @param log
	 *           the log
	 * @param message
	 *           the message
	 * @return formated string
	 */
	public static String formatLog(final CartLogData log, final String message)
	{
		String ret = message.replace("%user", "<b>" + log.getUserName() + "</b>");
		ret = ret.replace("%product", (log.getProductName() != null) ? "<b>" + log.getProductName() + "</b>" : "");
		ret = ret.replace("%value", (log.getNewValue() != null) ? "<b>" + log.getNewValue() + "</b>" : "");
		return ret;
	}
}
