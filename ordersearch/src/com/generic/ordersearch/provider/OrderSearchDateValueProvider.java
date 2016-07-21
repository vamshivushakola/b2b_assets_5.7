package com.generic.ordersearch.provider;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author Capgemini
 */
public class OrderSearchDateValueProvider implements FieldValueProvider
{
	private FieldNameProvider fieldNameProvider;

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object o) throws FieldValueProviderException
	{
		Assert.assertTrue(o instanceof AbstractOrderModel);
		final AbstractOrderModel order = (AbstractOrderModel) o;
		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
		final List<FieldValue> retList = new ArrayList();
		for (final String fieldName : fieldNames)
		{
			retList.add(new FieldValue(fieldName, sdf.format(order.getCreationtime())));
		}
		return retList;
	}

	protected FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}
}
