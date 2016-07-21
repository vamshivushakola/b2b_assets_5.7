package com.generic.productsearch.service.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.generic.productsearch.PASSolrSearchQueryData;
import com.generic.productsearch.service.AdvancedProductSearchFacade;


/**
 * Implentation of DefaultAdvancedProductSearchImpl
 * 
 * @author Capgemini
 */
public class DefaultAdvancedProductSearchImpl implements AdvancedProductSearchFacade
{
	//@Resource
	//private Converter<SearchQueryPageableData, SolrSearchRequest> commerceSearchQueryPageableConverter;

	@Resource
	private Converter<SearchQueryPageableData, SolrSearchRequest> productSearchCommerceSearchQueryPageableConverter;

	@Resource
	private Converter<SolrSearchRequest, SolrSearchResponse> pasSearchRequestConverter;

	@Resource
	private Converter<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, 
	ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData>> productCategorySearchPageConverter;

	private List<String> fieldsList;

	@Resource
	private Converter<SolrSearchResponse, ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, 
	CategoryModel>> commerceSolrSearchResponseConverter;

	@Override
	public ProductSearchPageData<SearchStateData, ProductData> textSearch(final HashMap<String, Set<String>> fields,
			final PageableData pageableData, final String sort)
	{
		final PASSolrSearchQueryData queryData = new PASSolrSearchQueryData();
		queryData.setFields(processFields(fields));
		queryData.setSort(sort);
		final SolrSearchRequest solrRequest = productSearchCommerceSearchQueryPageableConverter
				.convert(buildSearchQueryPageableData(queryData, pageableData));
		final SolrSearchResponse solrResponse = pasSearchRequestConverter.convert(solrRequest);
		final ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> searchData = commerceSolrSearchResponseConverter
				.convert(solrResponse);
		return productCategorySearchPageConverter.convert(searchData);
	}

	/**
	 * Search query pageableData
	 * 
	 * @param searchQueryData
	 *           the searchQueryData
	 * @param pageableData
	 *           the pageableData
	 * @return the SearchQueryPageableData
	 */
	protected SearchQueryPageableData buildSearchQueryPageableData(final SolrSearchQueryData searchQueryData,
			final PageableData pageableData)
	{
		final SearchQueryPageableData searchQueryPageableData = createSearchQueryPageableData();
		searchQueryPageableData.setSearchQueryData(searchQueryData);
		searchQueryPageableData.setPageableData(pageableData);
		return searchQueryPageableData;
	}

	/**
	 * processFields
	 * 
	 * @param fields
	 *           the fields
	 * @return the fields
	 */
	protected HashMap<String, Set<String>> processFields(final HashMap<String, Set<String>> fields)
	{
		return fields;
	}

	/**
	 * Create Search query pageableData
	 * 
	 * @return the SearchQueryPageableData
	 */
	protected SearchQueryPageableData createSearchQueryPageableData()
	{
		return new SearchQueryPageableData();
	}

	@Override
	public Collection<String> getFields()
	{
		return this.fieldsList;
	}

	/**
	 * Set fieldList
	 * 
	 * @param fieldsList
	 *           set fieldsList
	 */
	public void setFieldsList(final List<String> fieldsList)
	{
		this.fieldsList = fieldsList;
	}
}
