package com.generic.ordersearch.util;

import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;


/**
 * @author Capgemini
 */
public class FieldNameTranslator
{
	private FieldNameTranslator()
	{
	}

	public static String translateFieldName(final FieldNameProvider solrFieldNameProvider, final SearchQuery searchQuery,
			final IndexedProperty property)
	{
		if (property.isLocalized())
		{
			return solrFieldNameProvider.getFieldName(property, searchQuery.getLanguage(), FieldNameProvider.FieldType.INDEX);
			//        if(property.isCurrency())
			//            return solrFieldNameProvider.getFieldName(property, searchQuery.getCurrency(), FieldNameProvider.FieldType.INDEX);
		}
		else
		{
			return solrFieldNameProvider.getFieldName(property, null, FieldNameProvider.FieldType.INDEX);
		}
	}
}
