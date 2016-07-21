package com.generic.multicarts.converters.populator;

import de.hybris.platform.commercefacades.order.converters.populator.CartPopulator;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

import com.generic.multicarts.data.CartFollowerData;
import com.generic.multicarts.data.CartShareData;
import com.generic.multicarts.data.MultiCartsData;
import com.generic.multicarts.model.CartFollowerModel;
import com.generic.multicarts.model.CartShareModel;


/**
 * Default multicartsData populator
 * 
 * @author Capgemini
 * @param <T>
 */
public class MultiCartsDataPopulator<T extends MultiCartsData> extends CartPopulator<T>
{
	@Resource
	private Converter<CartFollowerModel, CartFollowerData> cartFollowerConverter;

	@Resource
	private Converter<CartShareModel, CartShareData> cartShareConverter;

	@Override
	public void populate(final CartModel source, final T target)
	{
		super.populate(source, target);
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setPK(source.getPk());
		target.setIsOwner(source.getIsOwner());
		target.setIsShared(source.getIsShared());
		target.setFollowerList(Converters.convertAll(source.getFollowers(), cartFollowerConverter));
		target.setShareList(Converters.convertAll(source.getShares(), cartShareConverter));
		target.setFollower((source.getFollower() != null) ? cartFollowerConverter.convert(source.getFollower()) : null);
		target.setHasNotification(source.getHasNotification());
	}
}
