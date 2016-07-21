package com.generic.multicarts.strategy.impl;

import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import javax.annotation.Resource;

import org.fest.util.Collections;

import com.generic.multicarts.model.CartLogModel;
import com.generic.multicarts.services.MultiCartsService;
import com.generic.multicarts.strategy.MultiCartsStrategy;


/**
 * Multicart Strategy for update and remove cart
 * 
 * @author Capgemini
 */
public class DefaultMultiCartsStrategy implements MultiCartsStrategy
{
	@Resource
	private UserService userService;

	@Resource
	private ModelService modelService;

	@Resource
	private MultiCartsService multiCartsService;

	@Override
	public void createCart(final CartModel cart)
	{
		multiCartsService.log(createLog(cart, null, "text.multicarts.log.createcart", null, cart.getName()));
	}

	@Override
	public void addProduct(final CommerceCartParameter parameter)
	{
		multiCartsService.log(createLog(parameter.getCart(), parameter.getProduct(), "text.multicarts.log.addproduct", null, null));
	}

	@Override
	public void setProductQuantity(final CommerceCartParameter parameter)
	{
		final ProductModel product = getProduct(parameter);
		multiCartsService.log(createLog(parameter.getCart(), product, "text.multicarts.log.setquantity",
				String.valueOf(parameter.getQuantity()), String.valueOf(parameter.getQuantity())));
	}

	@Override
	public void deleteProduct(final CommerceCartParameter parameter)
	{
		final ProductModel product = getProduct(parameter);

		multiCartsService.log(createLog(parameter.getCart(), product, "text.multicarts.log.removeproduct", null, null));
	}

	/**
	 * Get product
	 * 
	 * @param parameter
	 *           the CommerceCartParameter
	 * @return the product model
	 */
	private ProductModel getProduct(final CommerceCartParameter parameter)
	{
		final ProductModel product;
		final List<AbstractOrderEntryModel> entries = parameter.getCart().getEntries();
		final int entryNumber = (int) parameter.getEntryNumber();
		if (Collections.isEmpty(entries) && entries.size() > entryNumber)
		{
			product = entries.get(entryNumber).getProduct();
		}
		else
		{
			product = null;
		}
		return product;
	}

	@Override
	public void share(final CartModel cart, final B2BUnitModel b2bUnit)
	{

	}

	@Override
	public void unShare(final CartModel cart, final B2BUnitModel b2bUnit)
	{

	}

	/**
	 * Create Log
	 * 
	 * @param cart
	 *           the cart
	 * @param product
	 *           the product
	 * @param message
	 *           the message
	 * @param oldValue
	 *           the old value
	 * @param newValue
	 *           the new value
	 * @return the CartLogModel
	 */
	protected CartLogModel createLog(final AbstractOrderModel cart, final ProductModel product, final String message,
			final String oldValue, final String newValue)
	{
		final CartLogModel log = getModelService().create(CartLogModel.class);
		log.setMessage(message);
		log.setUser(userService.getCurrentUser());
		log.setNewValue(newValue);
		log.setOldValue(oldValue);
		log.setProduct(product);
		log.setOrderCode(cart.getCode());
		return log;
	}

	/**
	 * Get modelService
	 * 
	 * @return the modelService
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * Get multiCartsService
	 * 
	 * @return the multiCartsService
	 */
	public MultiCartsService getMultiCartsService()
	{
		return multiCartsService;
	}
}
