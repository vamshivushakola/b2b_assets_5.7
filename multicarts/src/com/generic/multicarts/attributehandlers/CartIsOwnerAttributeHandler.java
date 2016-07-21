package com.generic.multicarts.attributehandlers;


import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Dynamic attribute: Cart owner
 * 
 * @author Capgemini
 */
@Component
public class CartIsOwnerAttributeHandler extends AbstractDynamicAttributeHandler<Boolean, CartModel>
{

	@Autowired
	private UserService userService;

	/**
	 * CartOwner attribute
	 * 
	 * @param model
	 *           the model
	 * @return true if the current user is the cart owner
	 */
	@Override
	public Boolean get(final CartModel model)
	{
		return userService.getCurrentUser().getUid().equals(model.getUser().getUid());
	}

	/**
	 * Get UserService
	 * 
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * Set UserService
	 * 
	 * @param userService
	 *           set the userService
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
