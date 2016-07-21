/**
 *
 */
package com.generic.singlevieworders.order.populator;

import de.hybris.platform.commercefacades.order.converters.populator.OrderEntryPopulator;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.order.data.OrderTrackingData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.generic.singlevieworders.model.OrderTrackingModel;


/**
 * B2B Order entry populator
 *
 * @author ammandal
 *
 */
public class B2BOrderEntryPopulator extends OrderEntryPopulator
{
	@Resource(name = "productService")
	private ProductService productService;

	@Override
	protected void addProduct(final AbstractOrderEntryModel orderEntry, final OrderEntryData entry)
	{
		entry.setProduct(getProductConverter().convert(orderEntry.getProduct()));
		//if (orderEntry.getOrder() != null && orderEntry.getOrder().getSite() == null)
		if (orderEntry.getOrder() != null && orderEntry.getOrder().getSite() != null)
		{
			//Offline Product
			try
			{
				final ProductModel productModel = productService.getProductForCode(orderEntry.getProduct().getCode());
				if (productModel == null)
				{
					entry.setOfflineProduct(true);
				}
			}
			catch (final UnknownIdentifierException e)
			{
				entry.setOfflineProduct(true);
			}

			final List<OrderTrackingData> listOrderTrackingData = new ArrayList<OrderTrackingData>();
			for (final OrderTrackingModel orderTracking : orderEntry.getOrderTracking())
			{
				final OrderTrackingData tempOrderTrackingData = new OrderTrackingData();
				tempOrderTrackingData.setScheduledDate(orderTracking.getScheduledDate());
				tempOrderTrackingData.setScheduledQty(orderTracking.getScheduledQty());
				tempOrderTrackingData.setDeliveryDate(orderTracking.getDeliveryDate());
				tempOrderTrackingData.setDeliveryQty(orderTracking.getDeliveryQty());
				tempOrderTrackingData.setTrackingNumber(orderTracking.getTrackingNumber());
				listOrderTrackingData.add(tempOrderTrackingData);
			}
			entry.setOrderTracking(listOrderTrackingData);
		}
	}
}
