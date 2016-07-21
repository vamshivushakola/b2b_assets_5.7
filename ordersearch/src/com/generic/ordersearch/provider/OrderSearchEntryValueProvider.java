package com.generic.ordersearch.provider;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Capgemini
 */
public class OrderSearchEntryValueProvider implements FieldValueProvider {

    private FieldNameProvider fieldNameProvider;

    @Resource
    private ModelService modelService;

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object o) throws FieldValueProviderException {
        Assert.assertTrue(o instanceof AbstractOrderModel);
        AbstractOrderModel order = (AbstractOrderModel) o;
        final List<AbstractOrderEntryModel> entries = order.getEntries();
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
        final List<FieldValue> retList = new ArrayList();
        final String attr = indexedProperty.getValueProviderParameter();
        for (final String fieldName : fieldNames)
        {
            for(final AbstractOrderEntryModel entry: entries)
            {
                retList.add(new FieldValue(fieldName, getValue(entry.getProduct(), attr)));
            }
        }
        return retList;
    }

    protected String getValue(ProductModel product, String attribute)
    {
        return modelService.getAttributeValue(product, attribute);
    }

    protected FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}
