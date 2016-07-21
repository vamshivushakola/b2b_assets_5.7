/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.generic.interfaceerror.constants;

/**
 * Global class for all Interfaceerror constants. You can add global constants for your extension into this class.
 */
public final class InterfaceerrorConstants extends GeneratedInterfaceerrorConstants
{
	public static final String EXTENSIONNAME = "interfaceerror";

	/**
	 * InterfaceerrorConstants constructor
	 */
	private InterfaceerrorConstants()
	{
		//empty to avoid instantiating this constant class
	}


	/**
	 * bioMerieux ImpEx specific constants.
	 */
	public final class Impex
	{
		/**
		 * Character used as field separator in the csv files.
		 */
		public static final char CSV_FIELD_SEPARATOR = ';';
		/**
		 * Key used to store the csv filename in the resulting Map of the csv parsing action for each line in the file.
		 * 
		 * @see {@link com.generic.interfaceerror.logger.InterfaceerrorCSVReader#parseLine(String)}
		 */
		public static final int CSV_FILE_IND = 9998;
		/**
		 * Key used to store the line number in the resulting Map of the csv parsing action for each line in the file.
		 * 
		 * @see {@link com.generic.interfaceerror.logger.InterfaceerrorCSVReader#parseLine(String)}
		 */
		public static final int CSV_LINE_NUMBER = 9999;
		/**
		 * Key used to retrieve the csv filename in the resulting Map of the Impex file.
		 */
		public static final int IMPEX_FILE_NAME_IND = 1;
		/**
		 * Key used to store the line number in the resulting Map of the Impex file.
		 * 
		 * @see {@link com.generic.interfaceerror.logger.InterfaceerrorCSVReader#parseLine(String)}
		 */
		public static final int IMPEX_LINE_NUMBER_IND = 2;
		/**
		 * remove this tag from impex
		 */
		public static final String IGNORE_TAG = "<ignore>";

		/**
		 * Impex constructor
		 */
		private Impex()
		{
		}
	}


	/**
	 * bioMerieux ImpEx specific constants.
	 */
	public final class Logger
	{
		public static final String REJECT_SUFFIX = "_reject";

		public static final String LOG_SUFFIX = "_log";

		/**
		 * Logger constructor
		 */
		private Logger()
		{
		}
	}

	/**
	 * log4jLabel
	 * 
	 */
	public final class log4jLabel
	{

		public static final String ORIG_FILE_LABEL = "original file: ";

		public static final String LINE_LABEL = "line: ";

		public static final String REJECT_FILE_LABEL = "reject file: ";

		public static final String MSG_LABEL = "message: ";

		public static final String ORIG_LINE_LABEL = "original line: ";

		/**
		 * log4jLabel constructor
		 */
		private log4jLabel()
		{
		}
	}

	/**
	 * Manage delta calculation within the price hot-folder
	 */
	public static class Delta
	{
		/**
		 * dir name in the price hotfolder
		 */
		public static final String DELTA_DIR_NAME = "delta_data";

		/**
		 * yesterday file name in the delta dir
		 */
		public static final String YESTERDAY_FILE_NAME = "yesterday.csv";

		/**
		 * today file name in the delta dir
		 */
		public static final String TODAY_FILE_NAME = "today.csv";

		/**
		 * suffix for "remove file"
		 */
		public static final String DELETE_PREFIX = "delete_";
	}
}
