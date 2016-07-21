package com.generic.interfaceerror.logger;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.converter.ImpexConverter;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.ImpexTransformerTask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.io.IOUtils;


//import java.io.*;


/**
 * Transformer that retrieves a CSV file and transforms it to an impex file.
 * 
 * @author Capgemini
 * @see de.hybris.platform.acceleratorservices.dataimport.batch.task.ImpexTransformerTask
 */
public class InterfaceerrorTransformerTask extends ImpexTransformerTask
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean convertFile(final BatchHeader header, final File file, final File impexFile, final ImpexConverter converter)
			throws UnsupportedEncodingException, FileNotFoundException
	{
		boolean result = false;
		InterfaceerrorCSVReader csvReader = null;
		PrintWriter writer = null;
		try
		{
			csvReader = createCsvReader(file);
			writer = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(impexFile), super.getEncoding())));
			writer.println(getReplacedHeader(header, converter));
			while (!csvReader.finished())
			{
				try
				{
					//throw IllegalStateException exec when malformed line
					csvReader.readNextLineWithIncrement();
					final Map<Integer, String> row = csvReader.getLine();
					if (converter.filter(row))
					{
						try
						{
							/*
							 * //check columns number int nbCsvColumn =
							 * CharMatcher.is(InterfaceerrorConstants.Impex.CSV_FIELD_SEPARATOR
							 * ).countIn(converter.getHeader()); if(nbCsvColumn!=row.size()+1){ throw new
							 * IllegalArgumentException
							 * ("Incorrect column count: expected "+nbCsvColumn+", actual "+(row.size()+1)); }
							 */
							//throw IllegalArgumentException something bad occurred during the conversion
							writer.println(converter.convert(row, header.getSequenceId()));
							result = true;
						}
						catch (final IllegalArgumentException exc)
						{
							if (!csvReader.isFinished())
							{
								InterfaceerrorErrorManager.SINGLETON.addErrorLine(file.getName(), exc.getMessage(),
										csvReader.getCurrentLineNumber(), csvReader.getSourceLine());
							}
						}
					}
				}
				catch (final IllegalStateException exc)
				{
					InterfaceerrorErrorManager.SINGLETON.addErrorLine(file.getName(), exc.getMessage(),
							csvReader.getCurrentLineNumber(), csvReader.getSourceLine());
				}
			}
		}
		catch (final IOException exc)
		{
			InterfaceerrorErrorManager.SINGLETON.addErrorLine(file.getName(), "Fatal error: " + exc.getMessage(),
					csvReader.getCurrentLineNumber(), csvReader.getSourceLine());
		}
		finally
		{
			IOUtils.closeQuietly(writer);
			closeQuietly(csvReader);
		}
		return result;
	}

	/**
	 * Creates a specific {@link com.generic.interfaceerror.logger.InterfaceerrorCSVReader} reader used to parse the csv
	 * files.
	 * 
	 * @param file
	 *           the file to read
	 * @return the csvReader
	 * @throws UnsupportedEncodingException
	 *            in case of not supported encoding
	 * @throws FileNotFoundException
	 *            in case of file not found
	 * @see de.hybris.platform.acceleratorservices.dataimport.batch.task.ImpexTransformerTask#createCsvReader(java.io.File)
	 */
	@Override
	protected final InterfaceerrorCSVReader createCsvReader(final File file) throws UnsupportedEncodingException,
			FileNotFoundException
	{
		final String encoding = super.getEncoding();
		final int linesToSkip = super.getLinesToSkip();
		final char fieldSeparator = super.getFieldSeparator();

		InterfaceerrorErrorManager.SINGLETON.setFileParsingConfig(encoding, linesToSkip, fieldSeparator);
		return new InterfaceerrorCSVReader(file, encoding, linesToSkip, fieldSeparator);
	}
}
