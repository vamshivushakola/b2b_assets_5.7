package com.capgemini.quickorder.services;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * Service to find products
 * 
 * @author Thomas Brison <thomas.brison@capgemini.com>
 */
public interface QuickOrderService
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
