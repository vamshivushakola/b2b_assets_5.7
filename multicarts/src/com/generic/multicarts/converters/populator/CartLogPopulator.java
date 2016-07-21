package com.generic.multicarts.converters.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.generic.multicarts.data.CartLogData;
import com.generic.multicarts.model.CartLogModel;


/**
 * Default CartLog populator
 * 
 * @author Capgemini
 */
public class CartLogPopulator implements Populator<CartLogModel, CartLogData>
{
	@Override
	public void populate(final CartLogModel cartLogModel, final CartLogData cartLogData) throws ConversionException
	{
		Assert.notNull(cartLogModel, "Parameter cartLogModel cannot be null.");
		Assert.notNull(cartLogData, "Parameter cartLogData cannot be null.");
		if (StringUtils.isNotBlank(cartLogModel.getMessage()))
		{
			cartLogData.setMessage(cartLogModel.getMessage());
		}
		cartLogData.setCart(cartLogModel.getOrderCode());
		cartLogData.setUserName(cartLogModel.getUser().getName());
		cartLogData.setUserUID(cartLogModel.getUser().getUid());
		cartLogData.setDate(cartLogModel.getCreationtime());
		if (cartLogModel.getProduct() != null)
		{
			cartLogData.setProductCode(cartLogModel.getProduct().getCode());
			cartLogData.setProductName(cartLogModel.getProduct().getName());
			cartLogData.setProductUrl("/p/" + cartLogModel.getProduct().getCode());
		}
		cartLogData.setNewValue(cartLogModel.getNewValue());
	}
}
