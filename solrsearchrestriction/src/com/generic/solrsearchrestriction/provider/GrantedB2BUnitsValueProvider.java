/**
 * 
 */
package com.generic.solrsearchrestriction.provider;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.generic.solrsearchrestriction.model.ProductB2BUnitLinkModel;



/**
 * GrantedB2BUnitsValueProvider
 * 
 * @author Capgemini
 * 
 */
public class GrantedB2BUnitsValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{
	private FieldNameProvider fieldNameProvider;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConf, final IndexedProperty indexProp, final Object model)
			throws FieldValueProviderException
	{
		//this provider shall only be used with products
		final ProductModel product = (ProductModel) model;

		final Collection<ProductB2BUnitLinkModel> partners = product.getB2bUnitLinks();

		final List<FieldValue> retList = new ArrayList();


		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexProp, null);
		if (partners != null && !partners.isEmpty())
		{
			for (final ProductB2BUnitLinkModel partner : partners)
			{
				for (final String fieldName : fieldNames)
				{
					final Boolean isDisplayable = partner.getIsDisplayable();
					if (isDisplayable != null && isDisplayable.booleanValue() && partner.getB2bunit() != null)
					{
						retList.add(new FieldValue(fieldName, partner.getB2bunit().getUid()));
					}
				}
			}
		}
		else
		{
			for (final String fieldName : fieldNames)
			{
				retList.add(new FieldValue(fieldName, ":visible-for-all"));
			}
		}

		return retList;
	}

	/**
	 * Get Field name provider
	 * 
	 * @return the fieldNameProvider
	 */
	protected FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	/**
	 * Set fieldNameProvider
	 * 
	 * @param fieldNameProvider
	 *           set fieldNameProvider
	 */
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}


}
