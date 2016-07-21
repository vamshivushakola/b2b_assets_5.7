package com.capgemini.quickorder.converter;

import de.hybris.platform.b2bacceleratorfacades.product.data.ProductQuantityData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;


/**
 * Created by tbrison on 19/06/2014.
 */
public class ProductQuantityConverter implements Converter<ProductModel, ProductQuantityData>
{
	@Override
	public ProductQuantityData convert(final ProductModel productModel) throws ConversionException
	{
		final ProductQuantityData productQuantityData = new ProductQuantityData();
		return convert(productModel, productQuantityData);
	}

	@Override
	public ProductQuantityData convert(final ProductModel productModel, final ProductQuantityData productQuantityData)
			throws ConversionException
	{
		if (productModel == null)
		{
			return null;
		}

		productQuantityData.setSku(productModel.getCode());
		return productQuantityData;
	}
}
