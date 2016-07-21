package com.capgemini.quickorder.facades;

import com.capgemini.quickorder.data.QuickOrderProductData;
import de.hybris.platform.b2bacceleratorfacades.product.data.ProductQuantityData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;




/**
 * QuickOrderFacade is an Interface which contains methods.
 * 
 * @author svc-in-ecommusr
 * 
 */
public interface QuickOrderFacade
{

	/**
	 * Get products for a query, searching by the start of a code
	 * 
	 * @param query
	 *           The query to search
	 * @param nbResults
	 *           Maximum number of query results
	 * @return List of QuickOrderProductData
	 * @throws IllegalArgumentException
	 *            if query is invalid
	 */
	public List<QuickOrderProductData> findProductsForQueryStart(String query, int nbResults) throws IllegalArgumentException;

	/**
	 * Parse the quickorder form and only get relevant values.
	 * 
	 * @param quantities
	 *           Array of Quantities
	 * @param values
	 *           Array of SKU's
	 * @return List of ProductQuantityData
	 */
	public List<ProductQuantityData> parseQuickOrderForm(final Integer[] quantities, final String[] values);

	/**
	 * Parse the quickorder form and only get relevant values without quantities.
	 * 
	 * @param values
	 *           Array of SKU's
	 * @return List of ProductModel
	 */
	public List<ProductModel> parseQuickOrderForm(final String[] values);

	/**
	 * Check if session cart is empty
	 * 
	 * @return True is the cart is empty, false otherwise
	 */
	public boolean isCartEmpty();
}
