package com.generic.multicarts.strategy;

import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;


/**
 * Strategy for update and remove cart
 * 
 * @author Capgemini
 */
public interface MultiCartsStrategy
{
	/**
	 * createCart
	 * 
	 * @param cart
	 *           the cart
	 */
	void createCart(CartModel cart);

	/**
	 * addProduct
	 * 
	 * @param parameter
	 *           CommerceCartParameter
	 */
	void addProduct(CommerceCartParameter parameter);

	/**
	 * setProductQuantity
	 * 
	 * @param parameter
	 *           CommerceCartParameter
	 */
	void setProductQuantity(CommerceCartParameter parameter);

	/**
	 * deleteProduct
	 * 
	 * @param parameter
	 *           CommerceCartParameter
	 */
	void deleteProduct(CommerceCartParameter parameter);

	/**
	 * share cart
	 * 
	 * @param cart
	 *           the cart
	 * @param b2bUnit
	 *           the b2bUnit
	 */
	void share(CartModel cart, B2BUnitModel b2bUnit);

	/**
	 * unShare cart
	 * 
	 * @param cart
	 *           the cart
	 * @param b2bUnit
	 *           the b2bUnit
	 */
	void unShare(CartModel cart, B2BUnitModel b2bUnit);
}
