package com.generic.interfaceerror.concat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;


//import java.io.*;

/**
 * Concatenate files in one
 * 
 * @author capgemini on 16/04/2014.
 */
public final class ConcatTool
{
	/**
	 * log4j logger
	 */
	private static final Logger LOGGER = Logger.getLogger(ConcatTool.class);

	/**
	 * tool class not instantiable
	 */
	private ConcatTool()
	{
		throw new InstantiationError("Util class instantiation prohibited");
	}

	/**
	 * reader and writer buffer size
	 */
	static final int BUFFERSIZE = 1024 << 8;

	/**
	 * concatenates every files in concatDir and put the result in concatFilePath
	 * 
	 * @param concatDir
	 *           the directory containing filers to be concatenated
	 * @param concatFilePath
	 *           the resulting concatenated file
	 */
	public static void concat(final String concatDir, final String concatFilePath)
	{
		final File baseDir = new File(concatDir);
		concat(baseDir.listFiles(), concatFilePath);
	}

	/**
	 * concat files given in the fileNames array and put the result in concatFilePath
	 * 
	 * @param fileNames
	 *           The array of absolute path
	 * @param concatFilePath
	 *           the resulting concatenated file
	 */
	public static void concat(final String[] fileNames, final String concatFilePath)
	{
		final File[] files = new File[fileNames.length];
		for (int i = 0; i < fileNames.length; i++)
		{
			files[i] = new File(fileNames[i]);
		}
		concat(files, concatFilePath);
	}

	/**
	 * concat files given in the fileNames array and put the result in concatFilePath
	 * 
	 * @param files
	 *           The List of files
	 * @param concatFilePath
	 *           the resulting concatenated file
	 */
	public static void concat(final List<File> files, final String concatFilePath)
	{
		concat(files.toArray(new File[files.size()]), concatFilePath);
	}

	/**
	 * concat files given in the files array and put the result in concatFilePath
	 * 
	 * @param files
	 *           The array of files to concatenate
	 * @param concatFilePath
	 *           the resulting concatenated file
	 */
	public static void concat(final File[] files, final String concatFilePath)
	{
		final File concatenated = new File(concatFilePath);
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(concatenated), BUFFERSIZE);)
		{
			final byte[] bytes = new byte[BUFFERSIZE];
			int bytesRead;
			final byte[] newLine = "\n".getBytes();

			for (final File file : files)
			{
				try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file), BUFFERSIZE);)
				{
					while ((bytesRead = in.read(bytes, 0, bytes.length)) != -1)
					{
						out.write(bytes, 0, bytesRead);
					}
					in.close();
				}
				catch (final Exception e)
				{
					LOGGER.debug("[concat] error reading file : " + e.getMessage());
				}
				out.write(newLine);
				out.flush();

			}
		}
		catch (final Exception e)
		{
			LOGGER.debug("[concat] error writing in file : " + e.getMessage());
		}
	}
}
