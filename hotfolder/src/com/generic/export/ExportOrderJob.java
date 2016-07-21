package com.generic.export;

/**
 * Created by shbezalw on 11/03/2015.
 */
/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */

import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.CSVWriter;
import de.hybris.platform.util.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * Export Order Job for exporting order
 * 
 * @author svc-in-ecommusr
 * 
 */
public class ExportOrderJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(ExportOrderJob.class);
	private static final String ENCODING = "UTF-8";
	private static final String TEMPEXT = ".txt";
	private static final String FILENAME = "OrderData";
	private static final String FILENAMEDATEFORMAT = "yyyyMMddHHmmss";



	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		try
		{

			//select all completed and non exported orders
			final OrderModel orders = new OrderModel();
			orders.setExportStatus(ExportStatus.NOTEXPORTED);
			orders.setStatus(OrderStatus.COMPLETED);

			final List<OrderModel> orderList = flexibleSearchService.getModelsByExample(orders);


			if (orderList != null && !orderList.isEmpty())
			{
				final String basePath = Config.getParameter("acceleratorservices.export.basefolder");
				final String stamp = new SimpleDateFormat(FILENAMEDATEFORMAT).format(new Date());
				final String filename = basePath + File.separator + FILENAME + "-" + stamp;

				final File csvFile = new File(filename + TEMPEXT);

				final CSVWriter writer = new CSVWriter(csvFile, ENCODING);

				try
				{
					//export
					for (final OrderModel order : orderList)
					{
						exportOrder(order, writer);

						//mark as exported

						order.setExportStatus(ExportStatus.EXPORTED);
						modelService.save(order);
					}

					//unlock (rename) file
					writer.close();
					csvFile.renameTo(new File(filename + ".csv"));
				}
				catch (final Exception e)
				{
					writer.close();
					csvFile.delete();
					throw e;
				}
			}
		}
		catch (final FileNotFoundException fnfe)
		{
			LOG.error("Could not write file for CSV export ", fnfe);
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
		catch (final UnsupportedEncodingException e)
		{
			LOG.error(e.getMessage(), e);
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
		catch (final IOException e)
		{
			LOG.error(e.getMessage(), e);
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}


		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * Exports the order to CSV
	 * 
	 * @param order
	 *           the order
	 * @param writer
	 *           the write
	 * @throws IOException
	 *            IOException
	 */
	private void exportOrder(final OrderModel order, final CSVWriter writer) throws IOException
	{



		LOG.info("Exporting order " + order.getCode());

		final Map<Integer, String> values = new HashMap();


		//prepare order info
		values.put(Integer.valueOf(0), order.getCode());
		values.put(Integer.valueOf(1), order.getStatusDisplay());
		values.put(Integer.valueOf(2), order.getPurchaseOrderNumber());
		values.put(Integer.valueOf(3), order.getUser().getDisplayName());
		values.put(Integer.valueOf(4), order.getCalculated().toString());
		values.put(Integer.valueOf(5), order.getCurrency().getIsocode());
		final String paymentStatus = order.getPaymentStatus() == null ? "N.A." : order.getPaymentStatus().getCode();
		values.put(Integer.valueOf(6), paymentStatus);
		final String paymentMode = order.getPaymentMode() == null ? "N.A." : order.getPaymentMode().getName();
		values.put(Integer.valueOf(7), paymentMode);
		values.put(Integer.valueOf(8), order.getTotalTax().toString());
		values.put(Integer.valueOf(9), order.getTotalDiscounts().toString());
		values.put(Integer.valueOf(10), order.getTotalPrice().toString());
		values.put(Integer.valueOf(11), getAddress(order.getDeliveryAddress()));
		values.put(Integer.valueOf(12), order.getDeliveryMode().getName());
		values.put(Integer.valueOf(13), order.getDeliveryCost().toString());
		for (final AbstractOrderEntryModel entry : order.getEntries())
		{
			final int entryID = (entry.getEntryNumber().intValue() + 1);

			values.put(Integer.valueOf(14), entryID + "");
			values.put(Integer.valueOf(15), entry.getProduct().getCode());
			values.put(Integer.valueOf(16), entry.getQuantity().toString());
			values.put(Integer.valueOf(17), entry.getTotalPrice().toString());
			writer.write(values);
		}
	}

	/**
	 * Get address
	 * 
	 * @param addressM
	 *           the addressM
	 * @return the address
	 */
	private String getAddress(final AddressModel addressM)
	{
		String address = "";
		address += addressM.getFirstname() + " " + addressM.getLastname() + " , " + addressM.getStreetname() + " , "
				+ addressM.getTown() + " , " + addressM.getCountry().getIsocode() + " , " + addressM.getPostalcode();
		return address;


	}
}
