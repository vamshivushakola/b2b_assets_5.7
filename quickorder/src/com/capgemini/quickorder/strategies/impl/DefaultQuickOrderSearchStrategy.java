package com.capgemini.quickorder.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;

import java.util.List;

import com.capgemini.quickorder.constants.QuickorderConstants;
import com.capgemini.quickorder.daos.QuickOrderDAO;
import com.capgemini.quickorder.strategies.QuickOrderSearchStrategy;


/**
 * Created by Thomas Brison <thomas.brison@capgemini.com> on 01/08/2014.
 */
public class DefaultQuickOrderSearchStrategy implements QuickOrderSearchStrategy
{

	private QuickOrderDAO quickOrderDAO;
	private ProductService productService;

	@Override
	public List<ProductModel> findProductsForQuery(final String query, int nbResults)
	{
		if (nbResults <= 0)
		{
			nbResults = QuickorderConstants.MAX_NUMBER_QUERY_RESULTS;
		}
		return quickOrderDAO.findProductsByCodeStart(query, Math.min(nbResults, QuickorderConstants.MAX_NUMBER_QUERY_RESULTS));
	}

	@Override
	public ProductModel findProductForQuery(final String query) throws IllegalArgumentException
	{
		return productService.getProductForCode(query);
	}

	/**
	 * Set the QuickOrderDAO to find products
	 * 
	 * @param quickOrderDAO
	 *           set quickOrderDAO
	 */
	public void setQuickOrderDAO(final QuickOrderDAO quickOrderDAO)
	{
		this.quickOrderDAO = quickOrderDAO;
	}

	/**
	 * Set the Product Service to read and update
	 * 
	 * @param productService
	 *           set productService
	 */
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}
}
