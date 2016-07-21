package com.generic.interfaceerror.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;


//import java.util.*;

/**
 * Singleton class managing error files when importing a csv file in the hot folder
 * 
 * @author Capgemini
 */
public enum InterfaceerrorErrorManager
{
	/**
	 * singleton pattern design
	 */
	SINGLETON;
	/**
	 * map of file name to store theirs errors
	 */
	private final Map<String, Map<Integer, InterfaceerrorError>> impexErrors;

	/**
	 * encoding used to read csv files and resolve original lines
	 */
	private String encoding;
	/**
	 * line to skip used to read csv files and resolve original lines
	 */
	private int linesToSkip;
	/**
	 * field separator used to read the csv resolve original lines
	 */
	private char fieldSeparator;

	/**
	 * self singleton constructor
	 */
	private InterfaceerrorErrorManager()
	{
		impexErrors = new ConcurrentHashMap<>();
	}

	/**
	 * add an error to the file map of errors with the source line
	 * 
	 * @param file
	 *           the file to add error
	 * @param message
	 *           the message to explain the error
	 * @param lineNumber
	 *           the line where the error is located
	 * @param sourceLine
	 *           the source line
	 */
	public void addErrorLine(final String file, final String message, final int lineNumber, final String sourceLine)
	{
		final Map<Integer, InterfaceerrorError> errorLines = getOrCreateErrorLines(file);
		errorLines.put(Integer.valueOf(lineNumber), new InterfaceerrorError(message, sourceLine));
	}

	/**
	 * add an error to the file map of errors without the source line
	 * 
	 * @param file
	 *           the file to add error
	 * @param message
	 *           the message to explain the error
	 * @param lineNumber
	 *           the line where the error is located
	 */
	public void addErrorLine(final String file, final String message, final int lineNumber)
	{
		final Map<Integer, InterfaceerrorError> errorLines = getOrCreateErrorLines(file);
		errorLines.put(Integer.valueOf(lineNumber), new InterfaceerrorError(message));
	}

	/**
	 * get errors lines of the given filename
	 * 
	 * @param file
	 *           the file to get errors
	 * @return the map of error
	 */
	public synchronized Map<Integer, InterfaceerrorError> getErrorLines(final String file)
	{
		if (impexErrors.containsKey(file))
		{
			return impexErrors.get(file);
		}
		else
		{
			return Collections.<Integer, InterfaceerrorError> emptyMap();
		}
	}

	/**
	 * clear impexErrors of the given file
	 * 
	 * @param file
	 *           the file to clear errors
	 */
	public synchronized void clearErrors(final String file)
	{
		impexErrors.remove(file);
	}

	/**
	 * parse the source file to get original csv lines for each ImpexError.
	 * 
	 * @param file
	 *           the file name to resolve errors
	 * @throws UnsupportedEncodingException
	 *            in case of a incorrect encoding option
	 * @throws FileNotFoundException
	 *            in case file doesn't exists
	 * @see com.generic.interfaceerror.logger.InterfaceerrorTransformerTask#createCsvReader(java.io.File)
	 */
	public void resolveOrigLines(final File file) throws UnsupportedEncodingException, FileNotFoundException
	{
		final Map<Integer, InterfaceerrorError> errorLines = getErrorLines(file.getName());
		final InterfaceerrorCSVReader csvReader = new InterfaceerrorCSVReader(file, encoding, linesToSkip, fieldSeparator);

		final SortedSet<Integer> keys = new TreeSet<>(errorLines.keySet());
		for (final Integer lineNb : keys)
		{
			final InterfaceerrorError impexError = errorLines.get(lineNb);
			while (impexError.getOrigLine() == null && !csvReader.finished())
			{
				try
				{
					csvReader.readNextLineWithIncrement();
					if (lineNb.intValue() == csvReader.getCurrentLineNumber())
					{
						impexError.setOrigLine(csvReader.getSourceLine());
					}
				}
				catch (final IllegalStateException exc)
				{
					//This occurs when a file contains unexpected char (malformed lines)
					//print and swallow. By the way, this will be logged in the log file
					exc.printStackTrace();
				}
			}
		}
		try
		{
			csvReader.close();
		}
		catch (final IOException e)
		{
			csvReader.closeQuietly();
		}
	}

	/**
	 * store parse configuration to get source line with resolveOrigLines
	 * 
	 * @param encode
	 *           csv encoding
	 * @param skippedLines
	 *           csv line to skimp
	 * @param fieldSep
	 *           csv field separator
	 * @see com.generic.interfaceerror.logger.InterfaceerrorTransformerTask#createCsvReader(java.io.File)
	 */
	public void setFileParsingConfig(final String encode, final int skippedLines, final char fieldSep)
	{
		this.encoding = encode;
		this.linesToSkip = skippedLines;
		this.fieldSeparator = fieldSep;
	}

	/**
	 * get or create a Map<Integer, ImpexError> of a file error in the Map<String, Map<Integer,ImpexError>> impexErrors
	 * 
	 * @param fileName
	 *           the file name
	 * @return the new or existing Map of error lines
	 */
	private Map<Integer, InterfaceerrorError> getOrCreateErrorLines(final String fileName)
	{

		Map<Integer, InterfaceerrorError> errorLines = impexErrors.get(fileName);
		if (null == errorLines)
		{
			errorLines = new HashMap<>();
			impexErrors.put(fileName, errorLines);
		}
		return errorLines;
	}

}
