package com.generic.interfaceerror.logger;

import de.hybris.platform.util.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.generic.interfaceerror.constants.InterfaceerrorConstants;


/**
 * Customized csv reader. Compared to the default csv reader provided by hybris, it adds two entries to the Map
 * representing the parsed line: the original file name and the line number in the original file.
 * 
 * @author Capgemini
 * @see de.hybris.platform.util.CSVReader
 */
public class InterfaceerrorCSVReader extends CSVReader
{
	/**
	 * Name of the original csv file to be read.
	 */
	private final String filename;

	private int currentLineNumber;

	/**
	 * Default constructor.
	 * 
	 * @param file
	 *           File to read.
	 * @param encoding
	 *           Encoding used for reading the file.
	 * @param linesToSkip
	 *           Number of lines to skip at the beginning of the file.
	 * @param fieldSeparator
	 *           Field separator used in the file.
	 * @throws UnsupportedEncodingException
	 *            If the specified encoding is not supported.
	 * @throws FileNotFoundException
	 *            If the specified file is inaccessible.
	 * @see {@link de.hybris.platform.util.CSVReader#CSVReader(String, String)}
	 */
	public InterfaceerrorCSVReader(final File file, final String encoding, final int linesToSkip, final char fieldSeparator)
			throws UnsupportedEncodingException, FileNotFoundException
	{
		super(file, encoding);
		filename = file.getName();
		setLinesToSkip(linesToSkip);
		setFieldSeparator(new char[]
		{ fieldSeparator });
	}

	/**
	 * call super.readNextLine() and make sure the currentLineNumber is incremented (not done when an exception is
	 * thrown)
	 * 
	 * @return boolean
	 */
	public boolean readNextLineWithIncrement()
	{
		currentLineNumber++;
		return super.readNextLine();
	}

	@Override
	public int getCurrentLineNumber()
	{
		return this.currentLineNumber;
	}

	/**
	 * Add two column at the beginning of the row giving the filename and the lineNumber {@inheritDoc}
	 */
	@Override
	protected Map<Integer, String> parseLine(final String line)
	{
		final Map<Integer, String> resultTmp = super.parseLine(line);
		if (null != resultTmp)
		{
			resultTmp.put(Integer.valueOf(InterfaceerrorConstants.Impex.CSV_FILE_IND), filename);
			resultTmp.put(Integer.valueOf(InterfaceerrorConstants.Impex.CSV_LINE_NUMBER),
					String.valueOf(super.getCurrentLineNumber()));
		}
		return resultTmp;
	}
}
