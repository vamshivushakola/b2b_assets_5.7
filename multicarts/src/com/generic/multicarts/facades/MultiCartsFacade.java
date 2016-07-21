package com.generic.multicarts.facades;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.List;

import com.generic.multicarts.data.CartLogData;
import com.generic.multicarts.data.MultiCartsData;


/**
 * MultiCartsFacade Extends the default CartFacade
 * 
 * @author Capgemini
 * @see de.hybris.platform.commercefacades.order.CartFacade
 */
public interface MultiCartsFacade extends de.hybris.platform.commercefacades.order.CartFacade,
		de.hybris.platform.b2bacceleratorfacades.api.cart.CartFacade
{
	/**
	 * Get logs for the cart by cartCode
	 * 
	 * @param cartCode
	 *           The cartCode
	 * @param pageableData
	 *           The pageableData
	 * @return Logs
	 */
	SearchPageData<CartLogData> getLogs(String cartCode, final PageableData pageableData);

	/**
	 * Return carts for the current user
	 * 
	 * @return the carts
	 */
	List<MultiCartsData> getCarts();

	/**
	 * Get cart by code
	 * 
	 * @param cartCode
	 *           The cart code
	 * @return The cart
	 */
	MultiCartsData getCart(String cartCode);

	/**
	 * Get the selected cart for the current user
	 * 
	 * @return The cart
	 */
	MultiCartsData getCurrentCart();


	/**
	 * Log
	 * 
	 * @param orderCode
	 *           the order code
	 * @param product
	 *           the product
	 * @param message
	 *           the message
	 * @param oldValue
	 *           the old value
	 * @param newValue
	 *           the new value
	 */
	void log(String orderCode, String product, String message, String oldValue, String newValue);

	/**
	 * Create a new cart
	 * 
	 * @return the created cart
	 */
	MultiCartsData createCart();

	/**
	 * Create a new cart
	 * 
	 * @param name
	 *           The cart name
	 * @return the created cart
	 */
	MultiCartsData createCart(String name);


	/**
	 * Create a new cart
	 * 
	 * @param name
	 *           The cart name
	 * @param site
	 *           The site
	 * @return the created cart
	 */
	MultiCartsData createCart(String name, CMSSiteModel site);


	/**
	 * Select cart by cartCode
	 * 
	 * @param cartCode
	 *           The cartCode
	 */
	void selectCart(String cartCode);

	/**
	 * Remove cart by cartCode
	 * 
	 * @param cartCode
	 *           The cartCode
	 */
	void removeCart(String cartCode);

	/**
	 * Share the cart selected by cartCode
	 * 
	 * @param cartCode
	 *           The cartCode
	 * @param b2bUnit
	 *           The B2BUnit
	 */
	void share(String cartCode, String b2bUnit);

	/**
	 * Unshare the cart selected by cartCode
	 * 
	 * @param cartCode
	 *           The cartCode
	 * @param b2bUnit
	 *           The B2BUnit
	 */
	void unshare(String cartCode, String b2bUnit);

	/**
	 * Set cart name
	 * 
	 * @param cartCode
	 *           The cart Code
	 * @param name
	 *           The cart Name
	 */
	void setName(String cartCode, String name);


	/**
	 * Follow cart
	 * 
	 * @param cartCode
	 *           The cart code
	 */
	void follow(String cartCode);

	/**
	 * Unfollow cart
	 * 
	 * @param cartCode
	 *           The cart code
	 */
	void unfollow(String cartCode);
}
