/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Oct 29, 2015 7:46:16 PM                     ---
 * ----------------------------------------------------------------
 */
package com.generic.singlevieworders.jalo;

import com.generic.singlevieworders.constants.SingleviewordersConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>SingleviewordersManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSingleviewordersManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("ERPOrderNumber", AttributeMode.INITIAL);
		tmp.put("origin", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrder", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.ERPOrderNumber</code> attribute.
	 * @return the ERPOrderNumber - Addon - addition Defines the ERP order Number for the Order
	 */
	public String getERPOrderNumber(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, SingleviewordersConstants.Attributes.AbstractOrder.ERPORDERNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.ERPOrderNumber</code> attribute.
	 * @return the ERPOrderNumber - Addon - addition Defines the ERP order Number for the Order
	 */
	public String getERPOrderNumber(final AbstractOrder item)
	{
		return getERPOrderNumber( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.ERPOrderNumber</code> attribute. 
	 * @param value the ERPOrderNumber - Addon - addition Defines the ERP order Number for the Order
	 */
	public void setERPOrderNumber(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, SingleviewordersConstants.Attributes.AbstractOrder.ERPORDERNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.ERPOrderNumber</code> attribute. 
	 * @param value the ERPOrderNumber - Addon - addition Defines the ERP order Number for the Order
	 */
	public void setERPOrderNumber(final AbstractOrder item, final String value)
	{
		setERPOrderNumber( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return SingleviewordersConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.origin</code> attribute.
	 * @return the origin - Addon addition - Defines the ERP order Number for the Order
	 */
	public String getOrigin(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, SingleviewordersConstants.Attributes.AbstractOrder.ORIGIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.origin</code> attribute.
	 * @return the origin - Addon addition - Defines the ERP order Number for the Order
	 */
	public String getOrigin(final AbstractOrder item)
	{
		return getOrigin( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.origin</code> attribute. 
	 * @param value the origin - Addon addition - Defines the ERP order Number for the Order
	 */
	public void setOrigin(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, SingleviewordersConstants.Attributes.AbstractOrder.ORIGIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.origin</code> attribute. 
	 * @param value the origin - Addon addition - Defines the ERP order Number for the Order
	 */
	public void setOrigin(final AbstractOrder item, final String value)
	{
		setOrigin( getSession().getSessionContext(), item, value );
	}
	
}
