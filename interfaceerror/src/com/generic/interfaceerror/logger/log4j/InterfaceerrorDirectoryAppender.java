package com.generic.interfaceerror.logger.log4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Category;
import org.apache.log4j.FileAppender;
import org.apache.log4j.spi.LoggingEvent;


/**
 * log4j FileAppender that compute filename at runtime and put the file in the directory defined by the "directory"
 * properties i.e. : log4j.appender.IMPEXREJECT.directory=${IMPEX_LOG_DIR}/reject
 * <p/>
 * It also release default log4j file locking
 * 
 * @author capgemini
 */
public abstract class InterfaceerrorDirectoryAppender extends FileAppender
{
	/**
	 * directory to log inside
	 */
	private String directory;

	/**
	 * Get directory
	 * 
	 * @return the directory
	 */
	public final String getDirectory()
	{
		return directory;
	}

	/**
	 * set the directory to log in. done automatically by log4j
	 * 
	 * @param directory
	 *           the directory
	 * @throws IOException
	 *            in case directory cannot be created
	 */
	public final void setDirectory(final String directory) throws IOException
	{
		this.directory = directory;
		Files.createDirectories(Paths.get(directory));
	}

	/**
	 * avoid letting log4j keeping the file opened
	 * 
	 * @param event
	 *           the msg to be written
	 */
	@Override
	public void append(final LoggingEvent event)
	{
		try
		{
			super.setFile(fileName, fileAppend, bufferedIO, bufferSize);
			super.append(event);
			closeWriter();
		}
		catch (final IOException e)
		{
			System.out.println("There was an error writing in " + fileName);
		}
	}

	/**
	 * avoid letting log4j keeping the file opened
	 * 
	 * @param file
	 *           the file to write into
	 */
	@Override
	public final void setFile(final String file)
	{
		super.setFile(file);
		closeWriter();
	}

	/**
	 * avoid log4j to warn for a not defined file
	 */
	@Override
	public final void activateOptions()
	{
		if (fileName != null)
		{
			try
			{
				setFile(fileName, fileAppend, bufferedIO, bufferSize);
			}
			catch (final IOException e)
			{
				super.errorHandler.error("setFile(" + fileName + "," + fileAppend + ") call failed.", e, 4);
			}
		}
	}

	@Override
	public synchronized void setFile(final String fileName, final boolean append, final boolean bufferedIO, final int bufferSize)
			throws IOException
	{
		super.setFile(fileName, append, bufferedIO, bufferSize);
		closeWriter();
	}

	/**
	 * modify the msg of a loggingEvent
	 * 
	 * @param loggingEvent
	 *           the original event to be modified
	 * @param msg
	 *           the message to set
	 * @param appenderClassName
	 *           required by log4j : classname of the appender
	 * @return the LoggingEvent with the modified event
	 */
	protected final LoggingEvent modifyMsg(final LoggingEvent loggingEvent, final String msg, final String appenderClassName)
	{
		return new LoggingEvent(loggingEvent.fqnOfCategoryClass, Category.getInstance(appenderClassName), loggingEvent.timeStamp,
				loggingEvent.getLevel(), msg, null);
	}

	/**
	 * put a suffix into a filename before the '.' if it's not already.
	 * 
	 * @param fileName
	 *           the filename to suffix
	 * @param suffix
	 *           the suffix to add
	 * @return the suffixed filename
	 */
	protected final String suffixFileName(final String fileName, final String suffix)
	{
		final StringBuilder sb = new StringBuilder();
		final int pos = fileName.lastIndexOf('.');

		sb.append(directory);
		sb.append(File.separator);
		sb.append(fileName.substring(0, pos));
		if (fileName.indexOf(suffix) == -1)
		{
			sb.append(suffix);
		}
		sb.append(fileName.substring(pos));
		return sb.toString();
	}

	/**
	 * replace slashes with backSlashes
	 * 
	 * @param stringWithSlashes
	 *           the string with slashes
	 * @return the slashes with backSlashes
	 */
	public String replaceSlashes(final String stringWithSlashes)
	{
		return stringWithSlashes.replaceAll("/", "\\\\");
	}
}
