package com.generic.ordersearch.provider;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.provider.IdentityProvider;

/**
 * @author Capgemini
 */
public class AbstractOrderIdentityProvider implements IdentityProvider<AbstractOrderModel> {
    @Override
    public String getIdentifier(IndexConfig indexConfig, AbstractOrderModel abstractOrderModel) {
        return abstractOrderModel.getCode();
    }
}
