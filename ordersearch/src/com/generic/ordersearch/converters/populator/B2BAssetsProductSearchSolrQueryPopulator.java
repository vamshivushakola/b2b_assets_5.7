/**
 *
 */
package com.generic.ordersearch.converters.populator;

import de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchSolrQueryPopulator;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;


/**
 * @author ammandal
 *
 */
public class B2BAssetsProductSearchSolrQueryPopulator extends SearchSolrQueryPopulator
{
	private String indexedTypeName;

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

}
