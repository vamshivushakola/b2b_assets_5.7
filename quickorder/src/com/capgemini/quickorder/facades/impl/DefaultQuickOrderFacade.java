package com.capgemini.quickorder.facades.impl;

import de.hybris.platform.b2bacceleratorfacades.product.data.ProductQuantityData;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.quickorder.data.QuickOrderProductData;
import com.capgemini.quickorder.facades.QuickOrderFacade;
import com.capgemini.quickorder.services.QuickOrderService;


/**
 * Default implementation for the QuickOrderFacade
 * 
 * @author svc-in-ecommusr
 * 
 */
public class DefaultQuickOrderFacade implements QuickOrderFacade
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultQuickOrderFacade.class);

	private CartFacade cartFacade;

	private QuickOrderService quickOrderService;

	private Converter<ProductModel, QuickOrderProductData> quickOrderProductConverter;
	private Converter<ProductModel, ProductQuantityData> productQuantityConverter;

	/**
	 * This method is used to find product existence
	 * 
	 * @param query
	 *           The query to search
	 * @return The ProductModel
	 * @throws IllegalArgumentException
	 *            if query is invalid
	 */
	private ProductModel findProductForQuery(final String query) throws IllegalArgumentException
	{
		return quickOrderService.findProductForQuery(query);
	}

	/**
	 * This method is used to convert Product model
	 * 
	 * @param query
	 *           The query to search
	 * @return The ProductQuantityData
	 * @throws IllegalArgumentException
	 *            if query is invalid
	 */
	protected ProductQuantityData findProductDataForQuery(final String query) throws IllegalArgumentException
	{
		return productQuantityConverter.convert(findProductForQuery(query));
	}

	@Override
	public List<QuickOrderProductData> findProductsForQueryStart(final String query, final int nbResults)
			throws IllegalArgumentException
	{
		LOG.info("inside findProductsForQueryStart");
		final List<ProductModel> results = quickOrderService.findProductsForQuery(query, nbResults);
		final List<QuickOrderProductData> products = new ArrayList<QuickOrderProductData>();
		if (results != null && !results.isEmpty())
		{
			for (final ProductModel product : results)
			{
				LOG.info("before calling quickOrderProductConverter");
				products.add(quickOrderProductConverter.convert(product));
			}
			return products;
		}
		else
		{
			return products;
		}

	}

	@Override
	public List<ProductQuantityData> parseQuickOrderForm(final Integer[] quantities, final String[] values)
	{
		final List<ProductQuantityData> result = new ArrayList<ProductQuantityData>();
		if (values == null)
		{
			return result;
		}
		int i;
		String value;
		Integer quantity;
		ProductQuantityData productQuantityData;
		for (i = 0; i < values.length; i++)
		{
			value = values[i];
			quantity = quantities[i];
			if (value != null && !("".equals(value)) && quantity != null && quantity.intValue() >= 0)
			{
				// Check if the product really exists
				try
				{
					productQuantityData = findProductDataForQuery(value);
					if (productQuantityData != null)
					{
						productQuantityData.setQuantity(quantity);
						result.add(productQuantityData);
					}
				}
				catch (final UnknownIdentifierException e)
				{
					productQuantityData = new ProductQuantityData();
					productQuantityData.setSku(value);
					productQuantityData.setQuantity(new Integer(-1));
					result.add(productQuantityData);
				}
			}
			else if (value.equals("") || quantity == null)
			{
				productQuantityData = new ProductQuantityData();
				productQuantityData.setSku(value);
				productQuantityData.setQuantity(quantity);
				result.add(productQuantityData);
				if (value.equals("") && quantity == null)
				{
					result.remove(productQuantityData);
				}

			}

		}
		return result;
	}

	@Override
	public List<ProductModel> parseQuickOrderForm(final String[] values)
	{
		final List<ProductModel> result = new ArrayList<ProductModel>();
		if (values == null)
		{
			return result;
		}
		int i;
		String value;
		ProductModel product;
		for (i = 0; i < values.length; i++)
		{
			value = values[i];
			if (value != null && !("".equals(value)))
			{
				// Check if the product really exists
				product = findProductForQuery(value);
				if (product != null)
				{
					result.add(product);
				}
			}
		}
		return result;
	}

	@Override
	public boolean isCartEmpty()
	{
		return !cartFacade.hasSessionCart() || cartFacade.getSessionCart().getEntries().isEmpty();
	}


	/**
	 * Set cartFacede
	 * 
	 * @param cartFacade
	 *           the cartFacade to set
	 */
	public void setCartFacade(final CartFacade cartFacade)
	{
		this.cartFacade = cartFacade;
	}


	/**
	 * Set QuickOrder Service
	 * 
	 * @param quickOrderService
	 *           the quickOrderService to set
	 */
	public void setQuickOrderService(final QuickOrderService quickOrderService)
	{
		this.quickOrderService = quickOrderService;
	}


	/**
	 * Set QuickOrder Product Converter
	 * 
	 * @param quickOrderProductConverter
	 *           the quickOrderProductConverter to set
	 */
	public void setQuickOrderProductConverter(final Converter<ProductModel, QuickOrderProductData> quickOrderProductConverter)
	{
		this.quickOrderProductConverter = quickOrderProductConverter;
	}


	/**
	 * Set Product Quantity Converter
	 * 
	 * @param productQuantityConverter
	 *           the productQuantityConverter to set
	 */
	public void setProductQuantityConverter(final Converter<ProductModel, ProductQuantityData> productQuantityConverter)
	{
		this.productQuantityConverter = productQuantityConverter;
	}

}
