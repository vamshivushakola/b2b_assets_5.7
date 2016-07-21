package com.generic.multicarts.services;

import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;

import java.util.List;

import com.generic.multicarts.model.CartLogModel;


/**
 * MultiCartsService which contains all methods for multicarts
 * 
 * @author Capgemini
 */
public interface MultiCartsService extends CartService
{

	/**
	 * Log action
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

	/**
	 * Get logs for the cart by cartCode
	 * 
	 * @param code
	 *           The cartCode
	 * @param pageableData
	 *           the pageable data
	 * @return Logs
	 */
	SearchPageData<CartLogModel> getLogs(String code, final PageableData pageableData);

	/**
	 * Return carts for the user
	 * 
	 * @param user
	 *           The user
	 * @return the carts
	 */
	List<CartModel> getCarts(UserModel user);

	/**
	 * Get cart by code
	 * 
	 * @param cartCode
	 *           The cart code
	 * @return The cart
	 */
	CartModel getCartByCode(String cartCode);

	/**
	 * Remove cart by cartCode
	 * 
	 * @param cartCode
	 *           The cartCode
	 */
	void removeCart(String cartCode);

	/**
	 * Remove cart by cartModel
	 * 
	 * @param cart
	 *           The cart
	 */
	void removeCart(CartModel cart);

	/**
	 * Share the cart with B2Bunit
	 * 
	 * @param cart
	 *           The cartCode
	 * @param b2bUnit
	 *           The B2BUnit
	 */
	void share(CartModel cart, B2BUnitModel b2bUnit);

	/**
	 * Unshare the cart with B2Bunit
	 * 
	 * @param cart
	 *           The cartCode
	 * @param b2bUnit
	 *           The B2BUnit
	 */
	void unshare(CartModel cart, B2BUnitModel b2bUnit);

	/**
	 * Set cart name
	 * 
	 * @param cart
	 *           The cart
	 * @param name
	 *           The cart Name
	 */
	void setName(CartModel cart, String name);

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
	 * @param user
	 *           The user
	 * @param cart
	 *           The cart
	 */
	void follow(UserModel user, CartModel cart);

	/**
	 * Unfollow cart
	 * 
	 * @param user
	 *           The user
	 * @param cart
	 *           The cart
	 */
	void unfollow(UserModel user, CartModel cart);

}
