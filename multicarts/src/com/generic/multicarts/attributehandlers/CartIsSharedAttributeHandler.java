package com.generic.multicarts.attributehandlers;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import org.springframework.stereotype.Component;


/**
 * Dynamic attribute
 * 
 * @author Capgemini
 */
@Component
public class CartIsSharedAttributeHandler extends AbstractDynamicAttributeHandler<Boolean, CartModel>
{
	/**
	 * IsShared attriute
	 * 
	 * @param model
	 *           the model
	 * @return true if the cart is shared
	 */
	@Override
	public Boolean get(final CartModel model)
	{
		return model.getShares().size() > 0;
	}
}
