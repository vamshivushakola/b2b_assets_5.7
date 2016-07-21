package com.generic.interfaceerror.logger;

/**
 * POJO representing an Impex Error
 * 
 * @author capgemini
 */
public class InterfaceerrorError
{
	/**
	 * the msg explaining the error
	 */
	private String msg;
	/**
	 * the line in the original csv file
	 */
	private String origLine;
	/**
	 * the file name of the reject file
	 */
	private String rejectFileName;
	/**
	 * the line number in the reject file
	 */
	private int rejectLineNb;

	/**
	 * construct an Impex error with the source line
	 * 
	 * @param msg
	 *           the msg explaining the error
	 */
	public InterfaceerrorError(final String msg)
	{
		this.msg = msg;
	}

	/**
	 * construct an Impex error with the source line
	 * 
	 * @param msg
	 *           the msg explaining the error
	 * @param sourceLine
	 *           the line in the original csv file
	 */
	public InterfaceerrorError(final String msg, final String sourceLine)
	{
		this.msg = msg;
		this.origLine = sourceLine;
	}

	/**
	 * Get message
	 * 
	 * @return the message
	 */
	public final String getMsg()
	{
		return msg;
	}

	/**
	 * Set message
	 * 
	 * @param msg
	 *           set message
	 */
	public final void setMsg(final String msg)
	{
		this.msg = msg;
	}

	/**
	 * Get original line
	 * 
	 * @return the original line
	 */
	public final String getOrigLine()
	{
		return origLine;
	}

	/**
	 * Set original line
	 * 
	 * @param origLine
	 *           set original line
	 */
	public final void setOrigLine(final String origLine)
	{
		this.origLine = origLine;
	}

	/**
	 * Get reject line number
	 * 
	 * @return the reject line number
	 */
	public final int getRejectLineNb()
	{
		return rejectLineNb;
	}

	/**
	 * Set reject line number
	 * 
	 * @param rejectLineNb
	 *           set reject line number
	 */
	public final void setRejectLineNb(final int rejectLineNb)
	{
		this.rejectLineNb = rejectLineNb;
	}

	/**
	 * Get reject file name
	 * 
	 * @return reject file name
	 */
	public final String getRejectFileName()
	{
		return rejectFileName;
	}

	/**
	 * Set reject file name
	 * 
	 * @param rejectFileName
	 *           set reject file name
	 */
	public final void setRejectFileName(final String rejectFileName)
	{
		this.rejectFileName = rejectFileName;
	}
}
