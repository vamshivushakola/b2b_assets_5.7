package com.generic.productsearch.converters.populator;

import de.hybris.platform.commerceservices.search.solrfacetsearch.config.IndexedTypeSort;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.FacetSearchException;
import de.hybris.platform.solrfacetsearch.search.FacetSearchService;
import de.hybris.platform.solrfacetsearch.search.QueryField;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchResult;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.generic.productsearch.PASSolrSearchQueryData;


/**
 * DefaultPasSearchRequestPopulator
 * 
 * @author Capgemini
 */
public class DefaultPasSearchRequestPopulator
		implements
		Populator<SolrSearchRequest<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort>, 
		SolrSearchResponse<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort, SearchResult>>
{
	@Resource
	private FacetSearchService solrFacetSearchService;

	private static final Logger LOG = Logger.getLogger(DefaultPasSearchRequestPopulator.class);


	@Override
	public void populate(
			final SolrSearchRequest<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort> source,
			final SolrSearchResponse<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort, SearchResult> target)
			throws ConversionException
	{
		source.getSearchQuery().addSolrParams("q.op", "AND");
		target.setRequest(source);
		if (source.getSearchQueryData() instanceof PASSolrSearchQueryData)
		{
			final PASSolrSearchQueryData pasQueryData = (PASSolrSearchQueryData) source.getSearchQueryData();
			final SearchQuery searchQuery = source.getSearchQuery();

			for (final Map.Entry<String, Set<String>> field : pasQueryData.getFields().entrySet())
			{
				if (!field.getValue().isEmpty())
				{
					final QueryField fieldDefault = new QueryField(field.getKey(), field.getValue(), SearchQuery.Operator.OR);
					searchQuery.getAllFields().add(fieldDefault);
				}
			}
			try
			{
				final SearchResult search = solrFacetSearchService.search(searchQuery);
				target.setSearchResult(search);
			}
			catch (final FacetSearchException e)
			{
				LOG.error("Exception while executing SOLR search", e);
			}
		}
	}
}
