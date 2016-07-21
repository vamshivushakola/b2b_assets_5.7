package com.generic.ordersearch.converters.populator;

import de.hybris.platform.commerceservices.search.solrfacetsearch.config.IndexedTypeSort;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchResult;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Required;

import com.generic.ordersearch.util.FieldNameTranslator;


/**
 * @author Capgemini
 */
public class OrderSearchAutocompleteResultsPopulator
		implements
		Populator<SolrSearchResponse<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort, SearchResult>, Collection<String>>
{

	@Resource
	private FieldNameProvider solrFieldNameProvider;



	private String fieldName;
	private String translatedFieldName;

	@Override
	public void populate(
			final SolrSearchResponse<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort, SearchResult> source,
			final Collection<String> target) throws ConversionException
	{
		if (null != source.getSearchResult())
		{
			final Map<String, IndexedProperty> indexedProperties = source.getRequest().getIndexedType().getIndexedProperties();
			final SolrDocumentList results = source.getSearchResult().getSolrObject().getResults();
			translatedFieldName = FieldNameTranslator.translateFieldName(solrFieldNameProvider,
					source.getRequest().getSearchQuery(), indexedProperties.get(fieldName));
			for (final SolrDocument doc : results)
			{
				target.add((String) doc.get(translatedFieldName));
			}
		}
	}

	@Required
	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(final String fieldName)
	{
		this.fieldName = fieldName;
	}
}
