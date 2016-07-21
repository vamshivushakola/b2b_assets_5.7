package com.generic.ordersearch.converters.populator;

import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.generic.ordersearch.OrderSearchResultData;
import com.generic.ordersearch.util.FieldNameTranslator;


/**
 * @author Capgemini
 */
public class OrderSearchResultsPopulator<RESULT extends OrderSearchResultData>
		implements
		Populator<SolrSearchResponse<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort, SearchResult>, FacetSearchPageData<SearchStateData, RESULT>>
{
	@Resource
	private FieldNameProvider solrFieldNameProvider;


	protected String codeField;
	protected String typeField;
	protected String productsField;
	/*
	 * Commented namefield as cartlabel is removed in this version
	 */
	//protected String nameField;
	protected String userField;
	protected String creationDate;

	protected void initFieldNames(final SearchQuery searchQuery, final Map<String, IndexedProperty> indexedProperties)
	{
		codeField = FieldNameTranslator.translateFieldName(solrFieldNameProvider, searchQuery, indexedProperties.get("code"));
		typeField = FieldNameTranslator.translateFieldName(solrFieldNameProvider, searchQuery, indexedProperties.get("order_type"));
		productsField = FieldNameTranslator.translateFieldName(solrFieldNameProvider, searchQuery,
				indexedProperties.get("product_id"));
		//nameField = FieldNameTranslator.translateFieldName(solrFieldNameProvider, searchQuery, indexedProperties.get("name"));
		userField = FieldNameTranslator.translateFieldName(solrFieldNameProvider, searchQuery, indexedProperties.get("user"));
		creationDate = FieldNameTranslator.translateFieldName(solrFieldNameProvider, searchQuery,
				indexedProperties.get("creation_date"));
	}

	@Override
	public void populate(
			final SolrSearchResponse<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort, SearchResult> source,
			final FacetSearchPageData<SearchStateData, RESULT> target) throws ConversionException
	{
		final List<RESULT> ret = new ArrayList<>();
		if (null != source.getSearchResult())
		{
			final Map<String, IndexedProperty> indexedProperties = source.getRequest().getIndexedType().getIndexedProperties();
			final SolrDocumentList results = source.getSearchResult().getSolrObject().getResults();

			initFieldNames(source.getRequest().getSearchQuery(), indexedProperties);
			for (final SolrDocument doc : results)
			{
				ret.add(translateResult(doc));
			}
		}
		target.setResults(ret);
	}

	protected RESULT translateResult(final SolrDocument doc)
	{

		final OrderSearchResultData result = new OrderSearchResultData();
		result.setCode((String) doc.get(codeField));
		result.setType((String) doc.get(typeField));
		result.setProducts((ArrayList<String>) doc.get(productsField));
		//result.setName((String) doc.get(nameField));
		result.setUser((String) doc.get(userField));
		result.setDate((String) doc.get(creationDate));
		return (RESULT) result;
	}

	public FieldNameProvider getSolrFieldNameProvider()
	{
		return solrFieldNameProvider;
	}

	public void setSolrFieldNameProvider(final FieldNameProvider solrFieldNameProvider)
	{
		this.solrFieldNameProvider = solrFieldNameProvider;
	}
}
