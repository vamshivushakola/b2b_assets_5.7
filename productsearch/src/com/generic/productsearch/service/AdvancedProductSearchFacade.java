package com.generic.productsearch.service;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


/**
 * AdvancedProductSearchFacade for search products
 * 
 * @author Capgemini
 */
public interface AdvancedProductSearchFacade
{
	/**
	 * Get fields
	 * 
	 * @return the feilds
	 */
	Collection<String> getFields();

	/**
	 * Search products in specified fields
	 * 
	 * @param fields
	 *           {"solrFieldName": {"value1", "value2"}} search value1 OR value2 in field solrFieldName. The default
	 *           operator between fields is the operator AND
	 * @param pageableData
	 *           pageableData
	 * @param sort
	 *           sort field
	 * @return All products and pageable data
	 */
	ProductSearchPageData<SearchStateData, ProductData> textSearch(final HashMap<String, Set<String>> fields,
			PageableData pageableData, String sort);

	/*
	 * ProductSearchPageData<SearchStateData, ProductData> textSearch2(final HashMap<String, String> fields, PageableData
	 * pageableData, String sort);
	 */
}
