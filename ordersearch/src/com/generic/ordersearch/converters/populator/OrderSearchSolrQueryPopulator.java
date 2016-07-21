package com.generic.ordersearch.converters.populator;

import de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchSolrQueryPopulator;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.FacetSearchService;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchQuery.Operator;


/**
 * @author Capgemini
 */
public class OrderSearchSolrQueryPopulator extends SearchSolrQueryPopulator
{

	private String indexedTypeName;
	private FacetSearchService facetSearchService;

	@Override
	protected IndexedType getIndexedType(final FacetSearchConfig config)
	{
		final IndexConfig indexConfig = config.getIndexConfig();
		return indexConfig.getIndexedTypes().get(indexedTypeName);
		//return super.getIndexedType(config);
	}

	public String getIndexedTypeName()
	{
		return indexedTypeName;
	}

	public void setIndexedTypeName(final String indexedTypeName)
	{
		this.indexedTypeName = indexedTypeName;
	}


	@Override
	protected SearchQuery createSearchQuery(final FacetSearchConfig facetSearchConfig, final IndexedType indexedType,
			final String freeTextSearch)
	{
		SearchQuery searchQuery;

		if (facetSearchConfig.getSearchConfig().isLegacyMode())
		{
			searchQuery = new SearchQuery(facetSearchConfig, indexedType);
			searchQuery.setDefaultOperator(Operator.AND);
			searchQuery.setUserQuery(freeTextSearch);
		}
		else
		{
			searchQuery = facetSearchService.createFreeTextSearchQuery(facetSearchConfig, indexedType, freeTextSearch);
		}

		return searchQuery;
	}
}
