package com.generic.ordersearch.service.impl;

import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.FacetSearchException;
import de.hybris.platform.solrfacetsearch.search.FacetSearchStrategy;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchResult;
import de.hybris.platform.solrfacetsearch.search.impl.DefaultFacetSearchService;

import java.util.Map;


/**
 * @author Capgemini
 */
public class OrderSearchFacetSearchService extends DefaultFacetSearchService
{
	/**
	 * No catalog for orders and cart
	 *
	 * @param query
	 * @throws FacetSearchException
	 */

	//@Override
	protected void checkCatalogVersions(final SearchQuery query) throws FacetSearchException
	{
		// Do nothing
	}


	@Override
	public SearchResult search(final SearchQuery query, final Map searchHints) throws FacetSearchException
	{
		final FacetSearchConfig facetSearchConfig = query.getFacetSearchConfig();
		final IndexedType indexedType = query.getIndexedType();
		final FacetSearchStrategy facetSearchStrategy = getFacetSearchStrategy(facetSearchConfig, indexedType);
		return facetSearchStrategy.search(query, searchHints);
	}
}
