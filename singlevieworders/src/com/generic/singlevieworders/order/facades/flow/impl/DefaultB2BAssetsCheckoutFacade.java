/**
 *
 */
package com.generic.singlevieworders.order.facades.flow.impl;

import static de.hybris.platform.util.localization.Localization.getLocalizedString;

import de.hybris.platform.b2b.model.B2BCommentModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BPermissionResultModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BCartService;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.b2bacceleratorfacades.checkout.data.PlaceOrderData;
import de.hybris.platform.b2bacceleratorfacades.exception.EntityValidationException;
import de.hybris.platform.b2bacceleratorfacades.order.data.B2BCommentData;
import de.hybris.platform.b2bacceleratorfacades.order.data.B2BReplenishmentRecurrenceEnum;
import de.hybris.platform.b2bacceleratorfacades.order.data.TriggerData;
import de.hybris.platform.b2bacceleratorfacades.order.impl.DefaultB2BCheckoutFacade;
import de.hybris.platform.b2bacceleratorservices.enums.CheckoutPaymentType;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.generic.singlevieworders.model.OrderTrackingModel;



/**
 * @author ammandal
 *
 */
public class DefaultB2BAssetsCheckoutFacade extends DefaultB2BCheckoutFacade
{

	private B2BUnitService<B2BUnitModel, B2BCustomerModel> b2BUnitService;

	@Resource
	private UserService userService;

	/**
	 * @return the b2BUnitService
	 */
	public B2BUnitService<B2BUnitModel, B2BCustomerModel> getB2BUnitService()
	{
		return b2BUnitService;
	}

	/**
	 * @param b2bUnitService
	 *           the b2BUnitService to set
	 */

	public void setB2BUnitService(final B2BUnitService<B2BUnitModel, B2BCustomerModel> b2bUnitService)
	{
		b2BUnitService = b2bUnitService;
	}

	private static final String CART_CHECKOUT_TERM_UNCHECKED = "cart.term.unchecked";
	private static final String CART_CHECKOUT_SECURITYCODE_INVALID = "cart.securityCode.invalid";
	private static final String CART_CHECKOUT_NO_QUOTE_DESCRIPTION = "cart.no.quote.description";
	private static final String CART_CHECKOUT_REPLENISHMENT_NO_STARTDATE = "cart.replenishment.no.startdate";
	private static final String CART_CHECKOUT_REPLENISHMENT_NO_FREQUENCY = "cart.replenishment.no.frequency";



	@Override
	public <T extends AbstractOrderData> T placeOrder(final PlaceOrderData placeOrderData) throws InvalidCartException
	{
		// term must be checked
		if (!placeOrderData.getTermsCheck().equals(Boolean.TRUE))
		{
			throw new EntityValidationException(getLocalizedString(CART_CHECKOUT_TERM_UNCHECKED));
		}

		// for CARD type, securityCode must be set and valid
		final boolean isCardtPaymentType = CheckoutPaymentType.CARD.getCode().equals(getCart().getPaymentType().getCode());
		if (isCardtPaymentType
				&& (StringUtils.isBlank(placeOrderData.getSecurityCode()) || !authorizePayment(placeOrderData.getSecurityCode())))
		{
			throw new EntityValidationException(getLocalizedString(CART_CHECKOUT_SECURITYCODE_INVALID));
		}

		if (isValidCheckoutCart())
		{
			// validate quote negotiation
			if (placeOrderData.getNegotiateQuote() != null && placeOrderData.getNegotiateQuote().equals(Boolean.TRUE))
			{
				if (StringUtils.isBlank(placeOrderData.getQuoteRequestDescription()))
				{
					throw new EntityValidationException(getLocalizedString(CART_CHECKOUT_NO_QUOTE_DESCRIPTION));
				}
				else
				{
					final B2BCommentData b2BComment = new B2BCommentData();
					b2BComment.setComment(placeOrderData.getQuoteRequestDescription());

					final CartData cartData = new CartData();
					cartData.setB2BComment(b2BComment);

					updateCheckoutCart(cartData);
				}
			}

			// validate replenishment
			if (placeOrderData.getReplenishmentOrder() != null && placeOrderData.getReplenishmentOrder().equals(Boolean.TRUE))
			{
				if (placeOrderData.getReplenishmentStartDate() == null)
				{
					throw new EntityValidationException(getLocalizedString(CART_CHECKOUT_REPLENISHMENT_NO_STARTDATE));
				}

				if (placeOrderData.getReplenishmentRecurrence().equals(B2BReplenishmentRecurrenceEnum.WEEKLY)
						&& CollectionUtils.isEmpty(placeOrderData.getNDaysOfWeek()))
				{
					throw new EntityValidationException(getLocalizedString(CART_CHECKOUT_REPLENISHMENT_NO_FREQUENCY));
				}

				final TriggerData triggerData = new TriggerData();
				populateTriggerDataFromPlaceOrderData(placeOrderData, triggerData);

				return (T) scheduleOrder(triggerData);
			}

			return (T) placeOrder();
		}

		return null;
	}

	@Override
	public OrderData placeOrder() throws InvalidCartException
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			final UserModel currentUser = getCurrentUserForCheckout();

			final B2BUnitModel currentUserParentUnit = b2BUnitService.getParent((B2BCustomerModel) currentUser);
			final B2BUnitModel cartUserParentUnit = b2BUnitService.getParent((B2BCustomerModel) cartModel.getUser());

			if (cartModel.getUser().equals(currentUser) || currentUserParentUnit.equals(cartUserParentUnit)
					|| getCheckoutCustomerStrategy().isAnonymousCheckout())
			{
				if (currentUserParentUnit.equals(cartUserParentUnit))
				{
					cartModel.setUser(currentUser);
				}
				beforePlaceOrder(cartModel);

				final OrderModel orderModel = placeOrder(cartModel);

				afterPlaceOrder(cartModel, orderModel);

				// Convert the order to an order data
				if (orderModel != null)
				{
					return getOrderConverter().convert(orderModel);
				}
			}
		}

		return null;
	}

	/*
	 * This method is overridden to resolve issue of reorder the order with orderTracking
	 */
	@Override
	public void createCartFromOrder(final String orderCode)
	{
		final OrderModel order = getB2BOrderService().getOrderForCode(orderCode);
		AddressModel originalDeliveryAddress = order.getDeliveryAddress();
		if (originalDeliveryAddress != null)
		{
			originalDeliveryAddress = originalDeliveryAddress.getOriginal();
		}

		AddressModel originalPaymentAddress = order.getPaymentAddress();
		if (originalPaymentAddress != null)
		{
			originalPaymentAddress = originalPaymentAddress.getOriginal();
		}


		PaymentInfoModel paymentInfoModel = getPaymentInfoModelForClonedCart(order);
		final UserModel currentUser = getCurrentUserForCheckout();
		if (paymentInfoModel != null)
		{
			paymentInfoModel.setUser(currentUser);
			getModelService().save(paymentInfoModel);
		}

		if (order.getOrigin() != null)
		{
			for (final PaymentInfoModel paymentInfosModel : userService.getCurrentUser().getPaymentInfos())
			{
				paymentInfoModel = paymentInfosModel;
				break;
			}
		}


		// detach the order and null the attribute that is not available on the cart to avoid cloning errors.
		getModelService().detach(order);
		order.setSchedulingCronJob(null);
		order.setOriginalVersion(null);
		order.setStatus(OrderStatus.CREATED);
		order.setPaymentAddress(null);
		order.setDeliveryAddress(null);
		order.setHistoryEntries(null);
		order.setB2bcomments(Collections.<B2BCommentModel> emptyList());
		order.setWorkflow(null);
		order.setPermissionResults(Collections.<B2BPermissionResultModel> emptyList());
		order.setExhaustedApprovers(Collections.<B2BCustomerModel> emptySet());

		// create cart from the order object.
		final CartModel cart = this.<B2BCartService> getCartService().createCartFromAbstractOrder(order);

		if (cart != null)
		{
			cart.setDeliveryAddress(originalDeliveryAddress);
			cart.setPaymentAddress(originalPaymentAddress);
			cart.setPaymentInfo(paymentInfoModel);
			cart.setUser(currentUser);

			//Added by Deepali: set ordertacking as null in case of reorder
			final Collection<OrderTrackingModel> orderTrackingList = null;
			final List<AbstractOrderEntryModel> tempCartEntries = new ArrayList<AbstractOrderEntryModel>();
			for (final AbstractOrderEntryModel cartEntry : cart.getEntries())
			{
				if (cartEntry.getOrderTracking() != null)
				{
					cartEntry.setOrderTracking(orderTrackingList);
					tempCartEntries.add(cartEntry);
				}

			}

			for (final AbstractOrderEntryModel tempCartEntry : tempCartEntries)
			{
				getModelService().save(tempCartEntry);
			}
			getModelService().save(cart);
			getCartService().removeSessionCart();
			getCommerceCartService().calculateCart(cart);
			getModelService().refresh(cart);
			getCartService().setSessionCart(cart);
		}
	}
}
