package com.capgemini.quickorder.daos;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * DAO Interface to retrieve list of products from database
 * 
 * @author Thomas Brison <thomas.brison@capgemini.com>
 */
public interface QuickOrderDAO
{
	/**
	 * Search in the database products beginning by a code.
	 * 
	 * @param code
	 *           The code
	 * @param maxNumberResults
	 *           Maximum number of results to display
	 * @return Products beginning by the code
	 */
	List<ProductModel> findProductsByCodeStart(String code, int maxNumberResults);
}
