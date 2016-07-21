package com.generic.interfaceerror.delta;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.converter.ImpexConverter;
import de.hybris.platform.acceleratorservices.util.RegexParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import com.generic.interfaceerror.constants.InterfaceerrorConstants;
import com.generic.interfaceerror.logger.InterfaceerrorTransformerTask;


/**
 * ImpexTransformerTask that computes the delta between the today's price csv and the yesterday's one in order to import
 * only this delta.
 * 
 * @author capgemini on 27/03/14.
 */
public class DeltaBatchTransformerTask extends InterfaceerrorTransformerTask
{
	/**
	 * logger in case of a not computable delta
	 */
	private static final Logger logger = Logger.getLogger(DeltaBatchTransformerTask.class);
	/**
	 * header of logged messages
	 */
	private static final String LOG_HEADER = ("[Impex Delta] ");
	/**
	 * time divider to show delta computation duration from millis
	 */
	public static final int TIME_DIVIDER = 1000;
	/**
	 * time divider label to show delta computation duration
	 */
	public static final String TIME_DIVIDER_LABEL = "seconds";
	/**
	 * extract sale area parser
	 */
	private RegexParser parser;

	/**
	 * Replace original imported file by a computed delta.<br/>
	 * Steps are :<br/>
	 * 1) check if this is not a reject file. In this case, default behavior is preserved<br/>
	 * 2) delete the yesterday file<br/>
	 * 3) rename today to yesterday<br/>
	 * 4) overwrite today with the currently imported file<br/>
	 * 5) compute the delta between today and yesterday<br/>
	 * 6) replace the currently imported file by the delta<br/>
	 * <p/>
	 * if there was any missing file or a not computable delta the fallback is to restore the default behavior
	 * {@inheritDoc}
	 */
	@Override
	protected boolean convertFile(final BatchHeader header, final File file, final File impexFile, final ImpexConverter converter)
			throws UnsupportedEncodingException, FileNotFoundException
	{
		if (header.getFile().getName().indexOf(InterfaceerrorConstants.Delta.DELETE_PREFIX) != -1)
		{
			logger.info(LOG_HEADER + "Delta not computed because this is a removeFile.");
		}
		else if (header.getFile().getName().indexOf(InterfaceerrorConstants.Logger.REJECT_SUFFIX) != -1)
		{
			logger.info(LOG_HEADER + "Delta not computed because this is a rejectFile.");
		}
		else
		{
			try
			{
				final Path deltaDirPath = getDeltaDirPath(header);
				final Path backupTodayFilePath = deltaDirPath.resolve(InterfaceerrorConstants.Delta.TODAY_FILE_NAME);
				final Path backupYesterdayFilePath = deltaDirPath.resolve(InterfaceerrorConstants.Delta.YESTERDAY_FILE_NAME);

				DiffTool.DiffResult diff;
				if (Files.exists(backupYesterdayFilePath))
				{
					Files.delete(backupYesterdayFilePath);
				}
				if (Files.exists(backupTodayFilePath))
				{
					backupTodayFilePath.toFile().renameTo(backupYesterdayFilePath.toFile());
					Files.copy(file.toPath(), backupTodayFilePath);
					final long timer = System.currentTimeMillis();
					diff = DiffTool.diff(backupYesterdayFilePath.toString(), backupTodayFilePath.toString(), file.getAbsolutePath(),
							getDeleteFileName(header));
					final long duration = (System.currentTimeMillis() - timer) / TIME_DIVIDER;
					logger.info(LOG_HEADER + "Delta computed in " + duration + " " + TIME_DIVIDER_LABEL + " : " + diff.getAddedCount()
							+ " row(s) added, " + diff.getUpdateCount() + " row(s) updated, " + diff.getDeleteCount()
							+ " row(s) removed.");
				}
				else
				{
					Files.copy(file.toPath(), backupTodayFilePath);
					logger.info(LOG_HEADER + "Delta is not computed because the yesterday.csv file is missing : "
							+ backupYesterdayFilePath + ".");
				}
			}
			catch (final IOException e)
			{
				logger.debug(LOG_HEADER + "There was an error computing delta.");
			}
		}
		return super.convertFile(header, file, impexFile, converter);
	}

	/**
	 * get or create the delta dir path of a country from the processing directory
	 * 
	 * @param header
	 *           the batch header
	 * @return the path to the delta dir
	 * @throws IOException
	 *            in case directories cannot be created
	 */
	private Path getDeltaDirPath(final BatchHeader header) throws IOException
	{
		final Path deltaDirPath = Paths.get(header.getStoreBaseDirectory()).resolve(InterfaceerrorConstants.Delta.DELTA_DIR_NAME)
				.resolve(parser.parse(header.getFile().getName(), 1));
		if (!Files.exists(deltaDirPath))
		{
			Files.createDirectories(deltaDirPath);
		}
		return deltaDirPath;
	}

	/**
	 * Get delete file name
	 * 
	 * @param header
	 *           the header
	 * @return deleted directory path
	 */
	public final String getDeleteFileName(final BatchHeader header)
	{
		final String fileName = header.getFile().getName();
		final StringBuilder sb = new StringBuilder();
		final int comaPos = fileName.lastIndexOf('.');
		sb.append(InterfaceerrorConstants.Delta.DELETE_PREFIX);
		sb.append(fileName.substring(0, comaPos));
		sb.append(fileName.substring(comaPos));
		final Path deltaDirPath = Paths.get(header.getStoreBaseDirectory()).resolve(sb.toString());
		return deltaDirPath.toString();
	}

	/**
	 * Set parser
	 * 
	 * @param parser
	 *           set parser
	 */
	public final void setParser(final RegexParser parser)
	{
		this.parser = parser;
	}
}
