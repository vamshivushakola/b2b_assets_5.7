package com.generic.ordersearch.facade;

import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;

import java.util.Collection;

import com.generic.ordersearch.OrderSearchResultData;
import com.generic.ordersearch.field.OrderSearchField;


/**
 * @author Capgemini
 */
public interface OrderSearchFacade
{

	Collection<String> getFields();

	<T extends OrderSearchResultData> FacetSearchPageData<SearchStateData, T> textSearch(
			final Collection<OrderSearchField> fields, PageableData pageableData, String sort);

	SolrSearchResponse searchForAutoComplete(final Collection<OrderSearchField> fields);
}
