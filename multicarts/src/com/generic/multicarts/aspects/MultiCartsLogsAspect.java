package com.generic.multicarts.aspects;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.generic.multicarts.strategy.MultiCartsStrategy;


/**
 * MultiCartsLogsAspect contains all aspects for the multicarts
 * 
 * @author Capgemini
 */
@Aspect
@Component
public class MultiCartsLogsAspect
{

	@Resource
	private MultiCartsStrategy multiCartsStrategy;

	/**
	 * product add to cart using multiCartsStrategy
	 * 
	 * @param joinPoint
	 *           joinPoint
	 * @param parameter
	 *           CommerceCartParameter
	 */
	@AfterReturning("execution(*  de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService.addToCart(..)) && args(parameter)")
	public void logAddToCart(final JoinPoint joinPoint, final CommerceCartParameter parameter)
	{
		multiCartsStrategy.addProduct(parameter);
	}

	/**
	 * Update cart or delete cart using multiCartsStrategy
	 * 
	 * @param joinPoint
	 *           joinPoint
	 * @param parameter
	 *           CommerceCartParameter
	 * @param cartModificationData
	 *           cartModificationData
	 */
	@AfterReturning(pointcut = "execution(*  de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService.updateQuantityForCartEntry(..)) "
			+ "&& args(parameter)", returning = "cartModificationData")
	public void logUpdateEntry(final JoinPoint joinPoint, final CommerceCartParameter parameter,
			final CommerceCartModification cartModificationData)
	{
		if (cartModificationData.getQuantity() == 0)
		{
			multiCartsStrategy.deleteProduct(parameter);
		}
		else
		{
			multiCartsStrategy.setProductQuantity(parameter);
		}
	}

	/**
	 * Create cart using multiCartsStrategy
	 * 
	 * @param cartModel
	 *           cart model
	 */
	@AfterReturning(pointcut = "execution(*  de.hybris.platform.order.impl.DefaultCartFactory.createCart())", returning = "cartModel")
	public void logCreateCart(final CartModel cartModel)
	{
		multiCartsStrategy.createCart(cartModel);
	}
}
