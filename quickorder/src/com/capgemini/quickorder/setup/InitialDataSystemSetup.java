/**
 * 
 */
package com.capgemini.quickorder.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.commerceservices.setup.events.SampleDataImportedEvent;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.capgemini.quickorder.constants.QuickorderConstants;


/**
 * Class is used is to prepare the data for your store while initialization process.
 * 
 * @author svc-in-ecommusr
 * 
 */

public class InitialDataSystemSetup extends AbstractSystemSetup
{

	public static final String IMPORT_QUICKORDER_CATALOGS = "contentCatalogs";
	public static final String POWERTOOLS = "powertools";

	/**
	 * Generates the Dropdown and Multi-select boxes for the project data import
	 */
	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(IMPORT_QUICKORDER_CATALOGS, "Import Quickorder Component", true));

		return params;
	}

	/**
	 * This method will be called during the system initialization.
	 * 
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{

		if (getBooleanSystemSetupParameter(context, IMPORT_QUICKORDER_CATALOGS))
		{
			importContentCatalog(context, POWERTOOLS, Collections.singletonList(POWERTOOLS));

			final ImportData powertoolsImportData = new ImportData();
			powertoolsImportData.setProductCatalogName(POWERTOOLS);
			powertoolsImportData.setContentCatalogNames(Arrays.asList(POWERTOOLS));
			powertoolsImportData.setStoreNames(Arrays.asList(POWERTOOLS));
			// Send an event to notify any AddOns that the initial data import is complete
			getEventService().publishEvent(new SampleDataImportedEvent(context, Arrays.asList(powertoolsImportData)));
		}
	}

	/**
	 * Method used for catalog synchronization
	 * 
	 * @param context
	 *           the context provides the selected parameters and values
	 * @param catalogName
	 *           name of the catalog
	 * @param sync
	 *           it is boolean variable
	 * @return true is success
	 */
	protected boolean synchronizeContentCatalog(final SystemSetupContext context, final String catalogName, final boolean sync)
	{
		logInfo(context, "Begin synchronizing Content Catalog [" + catalogName + "] - "
				+ (sync ? "synchronizing" : "initializing job"));

		createContentCatalogSyncJob(context, catalogName + "ContentCatalog");

		boolean result = true;

		if (sync)
		{
			final PerformResult syncCronJobResult = executeCatalogSyncJob(context, catalogName + "ContentCatalog");
			if (isSyncRerunNeeded(syncCronJobResult))
			{
				logInfo(context, "Catalog catalog [" + catalogName + "] sync has issues.");
				result = false;
			}
		}

		logInfo(context, "Done " + (sync ? "synchronizing" : "initializing job") + " Content Catalog [" + catalogName + "]");
		return result;
	}

	/**
	 * Method used for importing content catalog
	 * 
	 * @param context
	 *           the context provides the selected parameters and values
	 * @param catalogName
	 *           name of the catalog
	 * @param contentCatalogs
	 *           list of the content catalogs
	 */
	protected void importContentCatalog(final SystemSetupContext context, final String catalogName,
			final List<String> contentCatalogs)
	{
		logInfo(context, "Begin importing catalog [" + catalogName + "]");

		importImpexFile(context, "/quickorder/import/contentCatalogs/" + catalogName + "ContentCatalog/cms-content.impex", true);
		logInfo(context, "Done importing catalog [" + catalogName + "]");

		// perform content sync jobs
		for (final String contentCatalog : contentCatalogs)
		{
			synchronizeContentCatalog(context, contentCatalog, true);
		}
	}

}
