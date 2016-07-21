package com.capgemini.quickorder.converter;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.apache.log4j.Logger;

import com.capgemini.quickorder.data.QuickOrderProductData;


/**
 * Created by tbrison on 19/06/2014.
 */
public class QuickOrderProductConverter implements Converter<ProductModel, QuickOrderProductData>
{

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(QuickOrderProductConverter.class);

	@Override
	public QuickOrderProductData convert(final ProductModel productModel) throws ConversionException
	{
		final QuickOrderProductData quickOrderProductData = new QuickOrderProductData();
		return convert(productModel, quickOrderProductData);
	}

	@Override
	public QuickOrderProductData convert(final ProductModel productModel, final QuickOrderProductData quickOrderProductData)
			throws ConversionException
	{
		if (productModel == null)
		{
			return null;
		}
		LOG.info("user entered quick order product code  " + productModel.getCode());
		LOG.info("user entered quick order product name  " + productModel.getName());
		quickOrderProductData.setCode(productModel.getCode());
		quickOrderProductData.setName(productModel.getName());
		return quickOrderProductData;
	}
}
