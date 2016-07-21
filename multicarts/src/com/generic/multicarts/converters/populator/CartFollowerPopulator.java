package com.generic.multicarts.converters.populator;


import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.generic.multicarts.data.CartFollowerData;
import com.generic.multicarts.model.CartFollowerModel;


/**
 * Default CartFollower populator
 * 
 * @author Capgemini
 */
public class CartFollowerPopulator implements Populator<CartFollowerModel, CartFollowerData>
{
	@Override
	public void populate(final CartFollowerModel source, final CartFollowerData target) throws ConversionException
	{
		target.setCartCode(source.getCart().getCode());
		target.setUserCode(source.getUser().getUid());
		target.setHasNotification(source.getNotification());
	}
}
