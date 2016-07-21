package com.generic.productsearch.converters.populator.solr;

import de.hybris.platform.commercesearch.search.solrfacetsearch.populators.BoostSearchQueryPopulator;
import de.hybris.platform.commerceservices.search.solrfacetsearch.config.IndexedTypeSort;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.OrderField;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * ProductSearchBoostSearchQueryPopulator
 * 
 * @author capgemini
 * 
 */
public class ProductSearchBoostSearchQueryPopulator extends BoostSearchQueryPopulator
{
	@Override
	public void populate(final SearchQueryPageableData source, final SolrSearchRequest target) throws ConversionException
	{
		final IndexedTypeSort indexedSort = (IndexedTypeSort) target.getCurrentSort();
		if (isBoostDisabled(indexedSort))
		{
			return;
		}
		final SearchQuery searchQuery = (SearchQuery) target.getSearchQuery();
		searchQuery.setQueryParser(de.hybris.platform.solrfacetsearch.search.SearchQuery.QueryParser.EDISMAX);
		final IndexedType indexedType = (IndexedType) target.getIndexedType();
		final FacetSearchConfig facetConfig = (FacetSearchConfig) target.getFacetSearchConfig();
		//if (StringUtils.isBlank(searchQuery.getUserQuery()))
		//{
		//	searchQuery.addRawQuery("*:*", de.hybris.platform.solrfacetsearch.search.SearchQuery.Operator.OR);
		//}
		final String categoryCode = getSelectedCategory(source);
		if (StringUtils.isNotBlank(categoryCode))
		{
			addHeroProductBoosts(searchQuery, categoryCode);
		}
		addConfiguredProductBoosts(searchQuery, indexedType, categoryCode, facetConfig);
		final List orderFields = searchQuery.getOrderFields();
		final OrderField firstOrderField = (OrderField) orderFields.get(0);
		if (!"score".equals(firstOrderField.getField()) || firstOrderField.isAscending())
		{
			orderFields.add(0, new OrderField("score", false));
		}
	}

}
