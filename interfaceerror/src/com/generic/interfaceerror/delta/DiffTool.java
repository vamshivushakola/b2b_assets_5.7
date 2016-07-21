package com.generic.interfaceerror.delta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;


/**
 * Diff tool contains tool for compute difference between files
 */
public final class DiffTool
{
	/**
	 * logger in case of a not computable delta
	 */
	private static final Logger LOGGER = Logger.getLogger(DiffTool.class);

	/**
	 * tool class not instantiable
	 */
	private DiffTool()
	{
		throw new InstantiationError("Util class instantiation prohibited");
	}

	/**
	 * The method is used to compute differences between two files containing product prices Prices must be sorted by ID
	 * and this id must be in the first column
	 * 
	 * @param yesterdayFileName
	 *           source file, last product prices
	 * @param todayFileName
	 *           today file, new product prices
	 * @param diffFileName
	 *           diff file, all updated or added prices
	 * @param removeFileName
	 *           the output of deleted rows. if null : ignored.
	 * @return Diff result, counter and filename.
	 */
	public static DiffResult diff(final String yesterdayFileName, final String todayFileName, final String diffFileName,
			final String removeFileName)
	{
		final DiffResult result = new DiffResult();
		PrintWriter removeFileWriter = null;
		try (BufferedReader yesterdayBuf = new BufferedReader(new FileReader(yesterdayFileName));
				BufferedReader todayBuf = new BufferedReader(new FileReader(todayFileName));
				PrintWriter diffFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(diffFileName))))
		{
			if (null != removeFileName)
			{
				removeFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(removeFileName)));
			}
			String yesterdayLine = yesterdayBuf.readLine();
			String todayLine = todayBuf.readLine();

			while (!(yesterdayLine == null && todayLine == null))
			{
				if (todayLine == null)
				{
					if (null != removeFileName)
					{
						removeFileWriter.println(yesterdayLine);
					}
					result.setDeleteCount(result.getDeleteCount() + 1);
					yesterdayLine = yesterdayBuf.readLine();
				}
				else if (yesterdayLine == null)
				{
					diffFileWriter.println(todayLine);
					result.setAddedCount(result.getAddedCount() + 1);
					todayLine = todayBuf.readLine();
				}
				else if (todayLine.trim().equals(""))
				{
					todayLine = todayBuf.readLine();
				}
				else if (yesterdayLine.trim().equals(""))
				{
					yesterdayLine = yesterdayBuf.readLine();
				}
				else if (yesterdayLine.equals(todayLine))
				{
					yesterdayLine = yesterdayBuf.readLine();
					todayLine = todayBuf.readLine();
				}
				else
				{
					final String tId = todayLine.split(";")[0];
					final String yId = yesterdayLine.split(";")[0];
					final int compare = yId.compareTo(tId);
					if (compare == 0)
					{
						diffFileWriter.println(todayLine);
						result.setUpdateCount(result.getUpdateCount() + 1);
						yesterdayLine = yesterdayBuf.readLine();
						todayLine = todayBuf.readLine();
					}
					else if (compare > 0)
					{
						diffFileWriter.println(todayLine);
						result.setAddedCount(result.getAddedCount() + 1);
						todayLine = todayBuf.readLine();
					}
					else
					{
						if (null != removeFileName)
						{
							removeFileWriter.println(yesterdayLine);
						}
						result.setDeleteCount(result.getDeleteCount() + 1);
						yesterdayLine = yesterdayBuf.readLine();
					}
				}
				diffFileWriter.flush();
				if (null != removeFileName)
				{
					removeFileWriter.flush();
				}
			}
		}
		catch (final IOException e)
		{
			LOGGER.debug("[delta] couldn't not have been computed because there was an error reading or writing in files. "
					+ e.getMessage());
		}
		finally
		{
			if (null != removeFileName)
			{
				removeFileWriter.close();
			}
		}
		return result;
	}

	/**
	 * pojo representing diff results
	 */
	public static class DiffResult
	{
		/**
		 * number of updated rows
		 */
		private long updateCount = 0;
		/**
		 * number of removed rows
		 */
		private long deleteCount = 0;
		/**
		 * . number of added rows
		 */
		private long addedCount = 0;

		/**
		 * Get update count
		 * 
		 * @return the updateCount
		 */
		public final long getUpdateCount()
		{
			return updateCount;
		}

		/**
		 * Set update count
		 * 
		 * @param uc
		 *           set updateCount
		 */
		public final void setUpdateCount(final long uc)
		{
			this.updateCount = uc;
		}

		/**
		 * Get deleteCount
		 * 
		 * @return the deleteCount
		 */
		public final long getDeleteCount()
		{
			return deleteCount;
		}

		/**
		 * Set deleteCount
		 * 
		 * @param dc
		 *           set deleteCount
		 */
		public final void setDeleteCount(final long dc)
		{
			this.deleteCount = dc;
		}

		/**
		 * Get addedCount
		 * 
		 * @return the addedCount
		 */
		public final long getAddedCount()
		{
			return addedCount;
		}

		/**
		 * Set addedCount
		 * 
		 * @param ac
		 *           set addedCount
		 */
		public final void setAddedCount(final long ac)
		{
			this.addedCount = ac;
		}
	}
}
