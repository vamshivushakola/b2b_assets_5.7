/**
 *
 */
package com.generic.singlevieworders.daos.impl;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.commerceservices.customer.dao.impl.DefaultCustomerAccountDao;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * B2BAssetsCustomerAccountDao for searching and sorting order history page data
 *
 * @author ammandal
 *
 */
public class B2BAssetsCustomerAccountDao extends DefaultCustomerAccountDao
{
	/**
	 * Find orders by B2BUnit and Store
	 *
	 * @param b2bCustomers
	 *           the b2bCustomers
	 * @param store
	 *           the store
	 * @param status
	 *           the status
	 * @param pageableData
	 *           the pageableData
	 * @return the SearchPageData
	 */
	public SearchPageData findOrdersByB2BUnitAndStore(final Collection<B2BCustomerModel> b2bCustomers, final BaseStoreModel store,
			final OrderStatus status[], final PageableData pageableData)
	{
		ServicesUtil.validateParameterNotNull(b2bCustomers, "Customer must not be null");
		ServicesUtil.validateParameterNotNull(store, "Store must not be null");
		final Map queryParams = new HashMap();
		queryParams.put("customer", b2bCustomers);
		queryParams.put("store", store);
		List sortQueries;
		if (status != null && status.length > 0)
		{
			queryParams.put("statusList", Arrays.asList(status));
			sortQueries = Arrays
					.asList(new SortQueryData[]
					{
							createSortQueryData(
									"byDate",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  AND {status} IN (?statusList) ORDER BY {creationtime} DESC, {pk}"),
							createSortQueryData(
									"byOrderNumber",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  AND {status} IN (?statusList) ORDER BY {creationtime} DESC, {pk}"),
							createSortQueryData(
									"byStatus",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  AND {status} IN (?statusList) ORDER BY {status} DESC, {pk}"),
							createSortQueryData(
									"byPONumber",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND {versionID} IS NULL "
											+ "AND {store} = ?store  AND {status} IN (?statusList) ORDER BY {purchaseOrderNumber} DESC, {pk}"),
							createSortQueryData("byERPNumber",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  AND {status} IN (?statusList) ORDER BY {pk}") });
		}
		else
		{
			sortQueries = Arrays
					.asList(new SortQueryData[]
					{
							createSortQueryData("byDate",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  ORDER BY {creationtime} DESC, {pk}"),
							createSortQueryData("byOrderNumber",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  ORDER BY {creationtime} DESC, {pk}"),
							createSortQueryData("byStatus",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  ORDER BY {status},{creationtime} DESC, {pk}"),
							createSortQueryData(
									"byPONumber",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  ORDER BY {purchaseOrderNumber},{creationtime} DESC, {pk}"),
							createSortQueryData("byERPNumber",
									"SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} in (?customer) AND "
											+ "{versionID} IS NULL AND {store} = ?store  ORDER BY {creationtime} DESC, {pk}")

					});
		}

		final SearchPageData searchPageData = getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams,
				pageableData);

		if (!searchPageData.getResults().isEmpty()
				&& (pageableData.getSort() != null && pageableData.getSort().equals("byOrderNumber")))
		{
			searchPageData.setResults(sortString(searchPageData, "byOrderNumber"));
		}
		else if (!searchPageData.getResults().isEmpty()
				&& (pageableData.getSort() != null && pageableData.getSort().equals("byERPNumber")))
		{
			searchPageData.setResults(sortString(searchPageData, "byERPNumber"));
		}

		return searchPageData;
	}

	/**
	 * Sorting page data
	 *
	 * @param searchPageData
	 *           the searchPageData
	 * @param sortBy
	 *           the sortBy
	 * @return sorting result list
	 */
	private List<Object> sortString(final SearchPageData searchPageData, final String sortBy)
	{
		final List<Object> temporaryOrderResults = new ArrayList<Object>(searchPageData.getResults());

		{
			Collections.sort(temporaryOrderResults, new Comparator<Object>()
			{
				@Override
				public int compare(final Object orderModel1, final Object orderModel2)
				{
					try
					{
						if (sortBy.equals("byERPNumber"))
						{
							return (Integer.valueOf(((OrderModel) orderModel1).getERPOrderNumber())).compareTo(Integer
									.valueOf(((OrderModel) orderModel2).getERPOrderNumber()));
						}
						else
						{
							return (Integer.valueOf(((OrderModel) orderModel1).getCode())).compareTo(Integer
									.valueOf(((OrderModel) orderModel2).getCode()));
						}
					}
					catch (final Exception e)
					{
						return 1;
					}
				}
			});
		}
		return temporaryOrderResults;
	}
}
