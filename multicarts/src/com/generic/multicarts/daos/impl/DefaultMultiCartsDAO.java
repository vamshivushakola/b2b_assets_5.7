package com.generic.multicarts.daos.impl;


import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.generic.multicarts.daos.MultiCartsDAO;
import com.generic.multicarts.model.CartFollowerModel;


/**
 * MultiCartsDAO implementation
 * 
 * @author Capgemini
 * @see com.generic.multicarts.daos.MultiCartsDAO
 */
public class DefaultMultiCartsDAO implements MultiCartsDAO
{
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<CartModel> findCartByCode(final String cartCode)
	{
		final String queryString = "SELECT {pk} FROM {Cart} WHERE {code} = ?code";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("code", cartCode);
		return flexibleSearchService.<CartModel> search(query).getResult();
	}

	@Override
	public List<CartModel> getCarts(final UserModel user)
	{
		final String queryString = "SELECT {cart.PK} "
				+ "FROM {Cart as cart LEFT JOIN CartShare as share ON {cart.pk} = {share.cartSharePK}} "
				+ "WHERE {cart.user} = ?user " + "OR ({cart.pk} = {share.cartSharePK} AND {share.group} "
				+ "IN ({{SELECT {r.target} FROM {PrincipalGroupRelation as r} WHERE {r.source} = ?user}}))";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("user", user.getPk());
		return flexibleSearchService.<CartModel> search(query).getResult();
	}

	@Override
	public List<CartFollowerModel> getFollowerByCartAndUser(final UserModel user, final CartModel cart)
	{
		final String queryString = "SELECT {pk} FROM {CartFollower} WHERE {cart} = ?code AND {user} = ?user";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("code", cart.getPk());
		query.addQueryParameter("user", user.getPk());
		return flexibleSearchService.<CartFollowerModel> search(query).getResult();
	}
}
