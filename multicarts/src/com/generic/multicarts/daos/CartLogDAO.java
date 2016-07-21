package com.generic.multicarts.daos;


import de.hybris.platform.commerceservices.search.dao.PagedGenericDao;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;

import com.generic.multicarts.model.CartLogModel;


/**
 * Interface for the cartLogDAO
 * 
 * @author Capgemini
 * 
 */
public interface CartLogDAO extends PagedGenericDao<CartLogModel>
{
	/**
	 * find logs
	 * 
	 * @param code
	 *           The cart
	 * @param pageableData
	 *           the pageable data
	 * @return Logs
	 */
	SearchPageData<CartLogModel> findLogForCart(String code, final PageableData pageableData);

	/**
	 * Log
	 * 
	 * @param order
	 *           the order
	 * @param user
	 *           the user
	 * @param product
	 *           the product
	 * @param message
	 *           the message
	 * @param oldValue
	 *           the old value
	 * @param newValue
	 *           the new value
	 */
	void log(String order, UserModel user, ProductModel product, String message, String oldValue, String newValue);

	/**
	 * Log action with CartLogModel
	 * 
	 * @param log
	 *           the log
	 */
	void log(CartLogModel log);
}
