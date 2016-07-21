package com.generic.interfaceerror.logger;

import de.hybris.platform.acceleratorservices.dataimport.batch.converter.impl.DefaultImpexConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.generic.interfaceerror.constants.InterfaceerrorConstants;


/**
 * Impex converter that adds two fields at the beginning of the row containing fileName and lineNumber to manage error
 * 
 * @author Capgemini
 */
public class InterfaceerrorImpexConverter extends DefaultImpexConverter
{

	/**
	 * logger in case of a not found impex mode
	 */
	private static final Logger logger = Logger.getLogger(InterfaceerrorImpexConverter.class);
	/**
	 * impex modes
	 * 
	 * @see <a
	 *      href="https://wiki.hybris.com/display/release5/ImpEx+Syntax">https://wiki.hybris.com/display/release5/ImpEx+Syntax</a>
	 */
	private static final String[] IMPEX_MODES =
	{ "INSERT", "UPDATE", "INSERT_UPDATE", "REMOVE" };


	private String impexRow;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public final void setHeader(final String header)
	{
		final StringBuilder sb = new StringBuilder();
		int pos = getImpexKeywordPos(header);
		if (pos != -1)
		{
			pos = header.indexOf(";", pos) + 1;
			sb.append(header.substring(0, pos));
			sb.append(";;");
			sb.append(header.substring(pos));
			super.setHeader(sb.toString());
		}
		else
		{
			logger.warn(" No mode found in header : " + header);
		}

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public final void setImpexRow(final String impexRow)
	{
		final StringBuilder sb = new StringBuilder();
		final int pos = impexRow.indexOf(";") + 1;
		sb.append(impexRow.substring(0, pos));
		sb.append(String.format("{%1$s};{%2$s};", Integer.valueOf(InterfaceerrorConstants.Impex.CSV_FILE_IND),
				Integer.valueOf(InterfaceerrorConstants.Impex.CSV_LINE_NUMBER)));
		sb.append(impexRow.substring(pos));
		this.impexRow = sb.toString();
		super.setImpexRow(sb.toString());
	}

	/**
	 * look for a impex mode in a header and return its position in the string or -1 if not found check if there's not a
	 * mistyping in INSERT_UPDATE that can results in a found update or insert
	 * 
	 * @param header
	 *           the header to get the mode position
	 * @return the position in the string of the fist found mode
	 */
	private int getImpexKeywordPos(final String header)
	{
		for (final String impexMode : IMPEX_MODES)
		{
			if (header.contains(impexMode))
			{
				return header.indexOf(impexMode);
			}
		}
		return -1;
	}

	/**
	 * split the impex row on ';' and then escape values inside those ';'
	 * 
	 * @param row
	 * @param sequenceId
	 * @return
	 */
	@Override
	public String convert(final Map<Integer, String> row, final Long sequenceId)
	{
		//
		final String[] splitedImpexRow = StringUtils.splitPreserveAllTokens(impexRow, ';');
		final List<String> tmp = new ArrayList<String>();
		for (final String string : splitedImpexRow)
		{
			if (StringUtils.isEmpty(string) || StringUtils.isBlank(string))
			{
				tmp.add(string);
			}
			else
			{
				//set the sub impex row as the current impex row and call super convert (so it will convert only the subpart).
				super.setImpexRow(string);
				final String escaped = escapeCSV(super.convert(row, sequenceId));
				tmp.add(escaped);
			}

		}
		return StringUtils.join(tmp, ';');

	}

	@Override
	protected String escapeQuotes(final String input)
	{
		//do nothing (already escaped)
		return input;

	}

	/**
	 * Escape CSV
	 * 
	 * @param input
	 *           the input
	 * @return the input
	 */
	protected String escapeCSV(final String input)
	{
		if (input.contains("\"") || input.contains(";") || input.contains("\n"))
		{
			return "\"" + input.replaceAll("\"", "\"\"") + "\"";
		}
		return input;
	}

}
