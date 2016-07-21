package com.generic.ordersearch.converters.populator;

import com.generic.ordersearch.service.impl.OrderSearchFacetSearchService;
import de.hybris.platform.commerceservices.search.solrfacetsearch.config.IndexedTypeSort;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.FacetSearchException;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchResult;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

/**
 * @author Capgemini
 */
public class OrderSearchRequestPopulator  implements Populator<
        SolrSearchRequest<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort>,
        SolrSearchResponse<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort, SearchResult>> {
    private static final Logger LOG =Logger.getLogger(OrderSearchRequestPopulator.class);


    @Resource
    private OrderSearchFacetSearchService orderSearchFacetSearchService;

    @Override
    public void populate(SolrSearchRequest<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort> source,
                         SolrSearchResponse<FacetSearchConfig, IndexedType, IndexedProperty, SearchQuery, IndexedTypeSort, SearchResult> target) throws ConversionException
    {
        try {
            target.setRequest(source);
            source.setCatalogVersions(null);
            source.getSearchQuery().setCatalogVersions(null);
            final SearchResult search = orderSearchFacetSearchService.search(source.getSearchQuery());
            target.setSearchResult(search);
        } catch (FacetSearchException e) {
            LOG.error("Exception while executing SOLR search", e);
        }
    }
}
