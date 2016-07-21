package com.capgemini.quickorder.strategies;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

/**
 * Search strategy to find products
 * 
 * Created by Thomas Brison <thomas.brison@capgemini.com> on 01/08/2014.
 */
public interface QuickOrderSearchStrategy
{
	/**
	 * Search for products for a query
	 * 
	 * @param query
	 *           The query to search for
	 * @param nbResults
	 *           The number of results to search
	 * @return List of found ProductModel
	 */
	public List<ProductModel> findProductsForQuery(String query, int nbResults);

	/**
	 * Search a product for a query
	 * 
	 * @param query
	 *           The query to search for
	 * @return ProductModel
	 * @throws IllegalArgumentException
	 *            if query is invalid
	 */
	public ProductModel findProductForQuery(String query) throws IllegalArgumentException;
}
