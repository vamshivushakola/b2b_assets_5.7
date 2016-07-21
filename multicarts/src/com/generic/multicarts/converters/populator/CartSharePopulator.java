package com.generic.multicarts.converters.populator;


import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.generic.multicarts.data.CartShareData;
import com.generic.multicarts.model.CartShareModel;


/**
 * Default CartShare populator
 * 
 * @author Capgemini
 */
public class CartSharePopulator implements Populator<CartShareModel, CartShareData>
{

	@Override
	public void populate(final CartShareModel cartShareModel, final CartShareData cartShareData) throws ConversionException
	{
		cartShareData.setB2bunit(cartShareModel.getGroup().getUid());
		cartShareData.setB2bunitName(cartShareModel.getGroup().getLocName());
		cartShareData.setCartCode(cartShareModel.getCartSharePK().getCode());
	}
}
