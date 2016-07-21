package com.generic.multicarts.services.impl;

import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.impl.DefaultCartService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.generic.multicarts.daos.CartLogDAO;
import com.generic.multicarts.daos.MultiCartsDAO;
import com.generic.multicarts.model.CartFollowerModel;
import com.generic.multicarts.model.CartLogModel;
import com.generic.multicarts.model.CartShareModel;
import com.generic.multicarts.services.MultiCartsService;


/**
 * MultiCartsService implementation
 * 
 * @author Capgemini
 * @see com.generic.multicarts.services.MultiCartsService
 */
public class MultiCartsServiceImpl extends DefaultCartService implements MultiCartsService
{
	@Resource
	private CartLogDAO cartLogDAO;

	@Resource
	private MultiCartsDAO multiCartsDAO;

	@Override
	public void log(final String order, final UserModel user, final ProductModel product, final String message,
			final String oldValue, final String newValue)
	{
		cartLogDAO.log(order, user, product, message, oldValue, newValue);
	}

	@Override
	public void log(final CartLogModel log)
	{
		cartLogDAO.log(log);
	}

	@Override
	public SearchPageData<CartLogModel> getLogs(final String code, final PageableData pageableData)
	{
		return cartLogDAO.findLogForCart(code, pageableData);
	}

	@Override
	public List<CartModel> getCarts(final UserModel user)
	{
		final List<CartModel> tempCarts = multiCartsDAO.getCarts(user);
		final List<CartModel> carts = new ArrayList<CartModel>();
		final Iterator<CartModel> it = tempCarts.iterator();
		while (it.hasNext())
		{

			final CartModel cart = it.next();
			final String cartName = cart.getName();
			if (cartName != null && !cartName.equals(""))
			{
				carts.add(cart);
			}
		}
		return carts;
	}

	@Override
	public CartModel getCartByCode(final String cartCode)
	{
		final List<CartModel> cartByCode = multiCartsDAO.findCartByCode(cartCode);
		if (cartByCode.size() == 1)
		{
			return cartByCode.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	public void removeCart(final String cartCode)
	{
		this.removeCart(this.getCartByCode(cartCode));
	}

	@Override
	public void removeCart(final CartModel cart)
	{
		cart.setPersist(false);
		if (getSessionCart().getCode().equals(cart.getCode()))
		{
			this.removeSessionCart();
		}
		else
		{
			getModelService().remove(cart);
		}
	}

	@Override
	public void share(final CartModel cart, final B2BUnitModel b2bUnit)
	{
		final CartShareModel shareModel = getModelService().create(CartShareModel.class);
		shareModel.setCartSharePK(cart);
		shareModel.setGroup(b2bUnit);
		getModelService().save(shareModel);
	}

	@Override
	public void unshare(final CartModel cart, final B2BUnitModel b2bUnit)
	{
		final Collection<CartShareModel> shares = cart.getShares();
		for (final CartShareModel share : shares)
		{
			if (share.getGroup().getUid().equals(b2bUnit.getUid()))
			{
				getModelService().remove(share);
			}
		}
	}

	@Override
	public void setName(final CartModel cart, final String name)
	{
		cart.setName(name);
		getModelService().save(cart);
	}

	@Override
	public void setName(final String cartCode, final String name)
	{
		final CartModel cart = getCartByCode(cartCode);
		if (cart != null)
		{
			setName(cart, name);
		}
	}

	@Override
	public void follow(final UserModel user, final CartModel cart)
	{
		final CartFollowerModel follower = getModelService().create(CartFollowerModel.class);
		follower.setUser(user);
		follower.setCart(cart);
		follower.setNotification(false);
		getModelService().save(follower);
	}

	@Override
	public void unfollow(final UserModel user, final CartModel cart)
	{
		final List<CartFollowerModel> followers = multiCartsDAO.getFollowerByCartAndUser(user, cart);
		getModelService().removeAll(followers);
	}
}
