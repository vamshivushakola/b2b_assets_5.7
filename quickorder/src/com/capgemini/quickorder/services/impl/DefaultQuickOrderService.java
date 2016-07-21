package com.capgemini.quickorder.services.impl;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import com.capgemini.quickorder.services.QuickOrderService;
import com.capgemini.quickorder.strategies.QuickOrderSearchStrategy;


/**
 * Service to find products
 * 
 * @author Thomas Brison <thomas.brison@capgemini.com>
 */
public class DefaultQuickOrderService implements QuickOrderService
{

	private QuickOrderSearchStrategy quickOrderSearchStrategy;

	@Override
	public List<ProductModel> findProductsForQuery(final String query, final int nbResults)
	{
		return quickOrderSearchStrategy.findProductsForQuery(query, nbResults);
	}

	@Override
	public ProductModel findProductForQuery(final String query) throws IllegalArgumentException
	{
		return quickOrderSearchStrategy.findProductForQuery(query);
	}

	/**
	 * Set quickOrderSearchStrategy
	 * 
	 * @param quickOrderSearchStrategy
	 *           set quickOrderSearchStrategy
	 */
	public void setQuickOrderSearchStrategy(final QuickOrderSearchStrategy quickOrderSearchStrategy)
	{
		this.quickOrderSearchStrategy = quickOrderSearchStrategy;
	}
}
