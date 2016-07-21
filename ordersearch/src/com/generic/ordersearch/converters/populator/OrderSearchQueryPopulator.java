package com.generic.ordersearch.converters.populator;

import com.generic.ordersearch.OrderSolrSearchQueryData;
import com.generic.ordersearch.field.OrderSearchField;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.search.QueryField;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @author Capgemini
 */
public class OrderSearchQueryPopulator<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE>  implements Populator<SearchQueryPageableData<OrderSolrSearchQueryData>,SolrSearchRequest<FACET_SEARCH_CONFIG_TYPE,INDEXED_TYPE_TYPE,INDEXED_PROPERTY_TYPE,SearchQuery,INDEXED_TYPE_SORT_TYPE>> {
    @Override
    public void populate(SearchQueryPageableData<OrderSolrSearchQueryData> source, SolrSearchRequest<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE> target) throws ConversionException {
        final OrderSolrSearchQueryData<OrderSearchField> queryData = source.getSearchQueryData();
        final SearchQuery searchQuery = target.getSearchQuery();
        for(OrderSearchField field : queryData.getFields())
        {
            if(!field.isEmpty())
            {
                QueryField fieldDefault = new QueryField(field.getFieldName(), field.getValues(), SearchQuery.Operator.OR);
                searchQuery.getAllFields().add(fieldDefault);
            }
            if(field.isFilter())
            {
                searchQuery.addSolrParams("fl", field.getFieldName()+"*");
            }
            if(field.isSort())
            {
                searchQuery.addOrderField(field.getFieldName(), field.asc());
            }
            if(!StringUtils.isBlank(field.getRawString()))
            {
                searchQuery.addRawQuery(field.getRawString(), SearchQuery.Operator.AND);
            }
        }
    }
}
