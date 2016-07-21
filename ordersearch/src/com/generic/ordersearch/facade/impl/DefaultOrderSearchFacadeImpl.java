package com.generic.ordersearch.facade.impl;

import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import com.generic.ordersearch.OrderSearchResultData;
import com.generic.ordersearch.OrderSolrSearchQueryData;
import com.generic.ordersearch.facade.OrderSearchFacade;
import com.generic.ordersearch.field.OrderSearchField;


/**
 * @author Capgemini
 */
public class DefaultOrderSearchFacadeImpl implements OrderSearchFacade
{

	@Resource
	private Converter<SearchQueryPageableData, SolrSearchRequest> orderSearchQueryPageableConverter;

	@Resource
	private Converter<SolrSearchRequest, SolrSearchResponse> orderSearchRequestConverter;

	@Resource
	private Converter<SolrSearchResponse, FacetSearchPageData> orderSolrSearchResponseConverter;

	private List<String> fieldsList;

	@Override
	public <T extends OrderSearchResultData> FacetSearchPageData<SearchStateData, T> textSearch(
			final Collection<OrderSearchField> fields, final PageableData pageableData, final String sort)
	{
		final OrderSolrSearchQueryData<OrderSearchField> queryData = new OrderSolrSearchQueryData();
		queryData.setFields(fields);
		queryData.setSort(sort);
		final SolrSearchRequest solrRequest = orderSearchQueryPageableConverter.convert(buildSearchQueryPageableData(queryData,
				pageableData));
		final SolrSearchResponse solrResponse = orderSearchRequestConverter.convert(solrRequest);
		return orderSolrSearchResponseConverter.convert(solrResponse);
	}

	@Override
	public SolrSearchResponse searchForAutoComplete(final Collection<OrderSearchField> fields)
	{
		final OrderSolrSearchQueryData<OrderSearchField> queryData = new OrderSolrSearchQueryData();
		queryData.setFields(fields);
		final SolrSearchRequest solrRequest = orderSearchQueryPageableConverter.convert(buildSearchQueryPageableData(queryData,
				null));
		return orderSearchRequestConverter.convert(solrRequest);
	}

	protected SearchQueryPageableData buildSearchQueryPageableData(final SolrSearchQueryData searchQueryData,
			final PageableData pageableData)
	{
		final SearchQueryPageableData searchQueryPageableData = createSearchQueryPageableData();
		searchQueryPageableData.setSearchQueryData(searchQueryData);
		searchQueryPageableData.setPageableData(pageableData);
		return searchQueryPageableData;
	}

	protected SearchQueryPageableData createSearchQueryPageableData()
	{
		return new SearchQueryPageableData();
	}

	@Override
	public Collection<String> getFields()
	{
		return this.fieldsList;
	}

	public void setFieldsList(final List<String> fieldsList)
	{
		this.fieldsList = fieldsList;
	}
}
