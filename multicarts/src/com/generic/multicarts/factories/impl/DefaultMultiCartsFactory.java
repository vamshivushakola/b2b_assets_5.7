package com.generic.multicarts.factories.impl;


import de.hybris.platform.b2b.order.impl.DefaultB2BCartFactory;
import de.hybris.platform.core.model.order.CartModel;

import com.generic.multicarts.factories.MultiCartsFactory;


/**
 * Implementaion of MultiCartsFactory
 * 
 * @author Capgemini
 * @see com.generic.multicarts.factories.MultiCartsFactory
 */
public class DefaultMultiCartsFactory extends DefaultB2BCartFactory implements MultiCartsFactory
{

	@Override
	public CartModel createCart(final String name)
	{
		final CartModel cart = super.createCart();
		cart.setName(name);
		cart.setPersist(true);
		return cart;
	}
}
