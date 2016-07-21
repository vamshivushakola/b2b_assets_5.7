package com.generic.multicarts.attributehandlers;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.generic.multicarts.model.CartFollowerModel;


/**
 * Return follower if the user follow the cart
 * 
 * @author Capgemini
 */
@Component
public class CartFollowerAttributeHandler extends AbstractDynamicAttributeHandler<CartFollowerModel, CartModel>
{

	@Resource
	private UserService userService;

	@Override
	public CartFollowerModel get(final CartModel model)
	{
		for (final CartFollowerModel follower : model.getFollowers())
		{
			if (userService.getCurrentUser().getUid().equals(follower.getUser().getUid()))
			{
				return follower;
			}
		}
		return null;
	}
}
