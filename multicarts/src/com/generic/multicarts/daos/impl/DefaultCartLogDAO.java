package com.generic.multicarts.daos.impl;


import de.hybris.platform.commerceservices.search.dao.impl.DefaultPagedGenericDao;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.generic.multicarts.daos.CartLogDAO;
import com.generic.multicarts.model.CartLogModel;


/**
 * CartLogDAO implementation
 * 
 * @author Capgemini
 * @see com.generic.multicarts.daos.CartLogDAO
 */
public class DefaultCartLogDAO extends DefaultPagedGenericDao<CartLogModel> implements CartLogDAO
{

	private static final Logger LOG = Logger.getLogger(DefaultCartLogDAO.class);

	@Resource
	private ModelService modelService;

	/**
	 * DefaultCartLogDAO constructor
	 */
	public DefaultCartLogDAO()
	{
		super("CartLog");
	}

	@Override
	public SearchPageData<CartLogModel> findLogForCart(final String code, final PageableData pageableData)
	{
		SearchPageData<CartLogModel> queryResults = null;

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT {pk}, {message}, {user} FROM {CartLog} WHERE {orderCode} = ?code");


		final Map<String, Object> params = new HashMap<>();
		params.put("code", code);

		final List<SortQueryData> sortQueries = Arrays.asList(createSortQueryData("byCreationtime", stringBuilder.toString()
				+ " ORDER BY {creationtime} DESC"));
		try
		{
			queryResults = getPagedFlexibleSearchService().search(sortQueries, "byCreationtime", params, pageableData);
		}
		catch (final Exception e)
		{
			LOG.error(e.getMessage());
		}

		return queryResults;
	}

	@Override
	public void log(final String order, final UserModel user, final ProductModel product, final String message,
			final String oldValue, final String newValue)
	{
		final CartLogModel log = new CartLogModel();
		log.setOrderCode(order);
		log.setMessage(message);
		log.setUser(user);
		log.setProduct(product);
		log.setOldValue(oldValue);
		log.setNewValue(newValue);
		this.log(log);
	}

	@Override
	public void log(final CartLogModel log)
	{
		modelService.save(log);
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
}
