package com.generic.ordersearch.provider;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
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
public abstract class AbstractOrderSearchProvider<T> implements FieldValueProvider {

    private FieldNameProvider fieldNameProvider;

    @Resource
    private ModelService modelService;

    protected abstract T getModel(AbstractOrderModel order);

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object o) throws FieldValueProviderException {
        Assert.assertTrue(o instanceof AbstractOrderModel);
        AbstractOrderModel order = (AbstractOrderModel) o;

        final List<FieldValue> retList = new ArrayList();
        final String attr = indexedProperty.getValueProviderParameter();

        if(indexedProperty.isLocalized()) {
            final Collection<LanguageModel> languages = indexConfig.getLanguages();
            for(LanguageModel lang: languages) {
                final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, lang.getIsocode());
                populateAttributes(retList, fieldNames, order, attr, lang.getIsocode());
            }
        }
        else
        {
            final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
            populateAttributes(retList, fieldNames, order, attr, null);
        }
        return retList;
    }

    protected void populateAttributes(final List<FieldValue> retList,
                                      final Collection<String> fieldNames,
                                      final AbstractOrderModel order,
                                      final String attr,
                                      final String locale)
    {
        String value;
        for (final String fieldName : fieldNames)
        {
            value = getValue(getModel(order), attr, locale);
            if(value != null)
            {
                retList.add(new FieldValue(fieldName, value));
            }
        }
    }

    protected String getValue(T unit, String attribute)
    {
        return getValue(unit, attribute, null);
    }

    protected String getValue(T unit, String attribute, String locale)
    {
        return (unit != null)?modelService.<String>getAttributeValue(unit, attribute):null;
    }

    protected FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}

