package com.generic.multicarts.attributehandlers;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import org.springframework.stereotype.Component;

import com.generic.multicarts.model.CartFollowerModel;


/**
 * Return true if the follower has a notification
 * 
 * @author Capgemini
 */
@Component
public class CartNotificationAttributeHandler extends AbstractDynamicAttributeHandler<Boolean, CartModel>
{
	@Override
	public Boolean get(final CartModel model)
	{
		final CartFollowerModel follower = model.getFollower();
		if (follower != null)
		{
			return follower.getNotification();
		}
		return false;
	}
}
