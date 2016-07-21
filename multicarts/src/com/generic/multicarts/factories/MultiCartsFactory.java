package com.generic.multicarts.factories;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartFactory;


/**
 * The new CartFactory Extends CartFactory
 * 
 * @author Capgemini
 * @see de.hybris.platform.order.CartFactory
 */
public interface MultiCartsFactory extends CartFactory
{
	/**
	 * Create cart
	 * 
	 * @param name
	 *           The cart Name
	 * @return The created cart
	 */
	CartModel createCart(String name);
}
