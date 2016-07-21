/**
 *
 */
package com.generic.ordersearch.service.impl;

import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.FacetSearchException;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchQueryCatalogVersionsResolver;
import de.hybris.platform.solrfacetsearch.search.SearchResult;
import de.hybris.platform.solrfacetsearch.search.impl.LegacyFacetSearchStrategy;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;


/**
 * @author derathor
 *
 */
public class DefaultB2BAssetsFacetSearchStrategy extends LegacyFacetSearchStrategy
{
	private SearchQueryCatalogVersionsResolver searchQueryCatalogVersionsResolver;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.solrfacetsearch.search.FacetSearchStrategy#search(de.hybris.platform.solrfacetsearch.search
	 * .SearchQuery, java.util.Map)
	 */

	@Override
	public SearchResult search(final SearchQuery searchquery, final Map<String, String> map) throws FacetSearchException
	{
		// YTODO Auto-generated method stub
		return super.search(searchquery, map);
	}

	@Override
	protected void checkCatalogVersions(final SearchQuery query, final FacetSearchConfig facetSearchConfig,
			final IndexedType indexedType) throws FacetSearchException
	{
		if (query.getIndexedType().getCode().equals("AbstractOrder"))
		{
			if (CollectionUtils.isEmpty(query.getCatalogVersions()))

			{
				query.setCatalogVersions(null);
			}
		}
		else
		{

			if (CollectionUtils.isEmpty(query.getCatalogVersions()))
			{
				query.setCatalogVersions(searchQueryCatalogVersionsResolver.resolveCatalogVersions(facetSearchConfig, indexedType));
			}

		}
	}
}
