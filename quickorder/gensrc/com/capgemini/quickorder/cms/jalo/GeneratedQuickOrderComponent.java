/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Oct 21, 2015 5:32:29 PM                     ---
 * ----------------------------------------------------------------
 */
package com.capgemini.quickorder.cms.jalo;

import com.capgemini.quickorder.constants.QuickorderConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.capgemini.quickorder.cms.jalo.QuickOrderComponent QuickOrderComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedQuickOrderComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>QuickOrderComponent.numberRows</code> attribute **/
	public static final String NUMBERROWS = "numberRows";
	/** Qualifier of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute **/
	public static final String MINIMUMCHARACTERSSEARCH = "minimumCharactersSearch";
	/** Qualifier of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute **/
	public static final String MAXIMUMRESULTSSEARCH = "maximumResultsSearch";
	/** Qualifier of the <code>QuickOrderComponent.delaySearch</code> attribute **/
	public static final String DELAYSEARCH = "delaySearch";
	/** Qualifier of the <code>QuickOrderComponent.styleClass</code> attribute **/
	public static final String STYLECLASS = "styleClass";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(NUMBERROWS, AttributeMode.INITIAL);
		tmp.put(MINIMUMCHARACTERSSEARCH, AttributeMode.INITIAL);
		tmp.put(MAXIMUMRESULTSSEARCH, AttributeMode.INITIAL);
		tmp.put(DELAYSEARCH, AttributeMode.INITIAL);
		tmp.put(STYLECLASS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.delaySearch</code> attribute.
	 * @return the delaySearch - Delay in milliseconds before displaying auto-completion results.
	 */
	public Integer getDelaySearch(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, DELAYSEARCH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.delaySearch</code> attribute.
	 * @return the delaySearch - Delay in milliseconds before displaying auto-completion results.
	 */
	public Integer getDelaySearch()
	{
		return getDelaySearch( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.delaySearch</code> attribute. 
	 * @return the delaySearch - Delay in milliseconds before displaying auto-completion results.
	 */
	public int getDelaySearchAsPrimitive(final SessionContext ctx)
	{
		Integer value = getDelaySearch( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.delaySearch</code> attribute. 
	 * @return the delaySearch - Delay in milliseconds before displaying auto-completion results.
	 */
	public int getDelaySearchAsPrimitive()
	{
		return getDelaySearchAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.delaySearch</code> attribute. 
	 * @param value the delaySearch - Delay in milliseconds before displaying auto-completion results.
	 */
	public void setDelaySearch(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, DELAYSEARCH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.delaySearch</code> attribute. 
	 * @param value the delaySearch - Delay in milliseconds before displaying auto-completion results.
	 */
	public void setDelaySearch(final Integer value)
	{
		setDelaySearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.delaySearch</code> attribute. 
	 * @param value the delaySearch - Delay in milliseconds before displaying auto-completion results.
	 */
	public void setDelaySearch(final SessionContext ctx, final int value)
	{
		setDelaySearch( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.delaySearch</code> attribute. 
	 * @param value the delaySearch - Delay in milliseconds before displaying auto-completion results.
	 */
	public void setDelaySearch(final int value)
	{
		setDelaySearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute.
	 * @return the maximumResultsSearch - Maximum number of results to display by auto-completion.
	 */
	public Integer getMaximumResultsSearch(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, MAXIMUMRESULTSSEARCH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute.
	 * @return the maximumResultsSearch - Maximum number of results to display by auto-completion.
	 */
	public Integer getMaximumResultsSearch()
	{
		return getMaximumResultsSearch( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute. 
	 * @return the maximumResultsSearch - Maximum number of results to display by auto-completion.
	 */
	public int getMaximumResultsSearchAsPrimitive(final SessionContext ctx)
	{
		Integer value = getMaximumResultsSearch( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute. 
	 * @return the maximumResultsSearch - Maximum number of results to display by auto-completion.
	 */
	public int getMaximumResultsSearchAsPrimitive()
	{
		return getMaximumResultsSearchAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute. 
	 * @param value the maximumResultsSearch - Maximum number of results to display by auto-completion.
	 */
	public void setMaximumResultsSearch(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, MAXIMUMRESULTSSEARCH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute. 
	 * @param value the maximumResultsSearch - Maximum number of results to display by auto-completion.
	 */
	public void setMaximumResultsSearch(final Integer value)
	{
		setMaximumResultsSearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute. 
	 * @param value the maximumResultsSearch - Maximum number of results to display by auto-completion.
	 */
	public void setMaximumResultsSearch(final SessionContext ctx, final int value)
	{
		setMaximumResultsSearch( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.maximumResultsSearch</code> attribute. 
	 * @param value the maximumResultsSearch - Maximum number of results to display by auto-completion.
	 */
	public void setMaximumResultsSearch(final int value)
	{
		setMaximumResultsSearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute.
	 * @return the minimumCharactersSearch - Number of characters for auto-completion to start.
	 */
	public Integer getMinimumCharactersSearch(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, MINIMUMCHARACTERSSEARCH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute.
	 * @return the minimumCharactersSearch - Number of characters for auto-completion to start.
	 */
	public Integer getMinimumCharactersSearch()
	{
		return getMinimumCharactersSearch( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute. 
	 * @return the minimumCharactersSearch - Number of characters for auto-completion to start.
	 */
	public int getMinimumCharactersSearchAsPrimitive(final SessionContext ctx)
	{
		Integer value = getMinimumCharactersSearch( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute. 
	 * @return the minimumCharactersSearch - Number of characters for auto-completion to start.
	 */
	public int getMinimumCharactersSearchAsPrimitive()
	{
		return getMinimumCharactersSearchAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute. 
	 * @param value the minimumCharactersSearch - Number of characters for auto-completion to start.
	 */
	public void setMinimumCharactersSearch(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, MINIMUMCHARACTERSSEARCH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute. 
	 * @param value the minimumCharactersSearch - Number of characters for auto-completion to start.
	 */
	public void setMinimumCharactersSearch(final Integer value)
	{
		setMinimumCharactersSearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute. 
	 * @param value the minimumCharactersSearch - Number of characters for auto-completion to start.
	 */
	public void setMinimumCharactersSearch(final SessionContext ctx, final int value)
	{
		setMinimumCharactersSearch( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.minimumCharactersSearch</code> attribute. 
	 * @param value the minimumCharactersSearch - Number of characters for auto-completion to start.
	 */
	public void setMinimumCharactersSearch(final int value)
	{
		setMinimumCharactersSearch( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.numberRows</code> attribute.
	 * @return the numberRows - Number of rows in the table.
	 */
	public Integer getNumberRows(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, NUMBERROWS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.numberRows</code> attribute.
	 * @return the numberRows - Number of rows in the table.
	 */
	public Integer getNumberRows()
	{
		return getNumberRows( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.numberRows</code> attribute. 
	 * @return the numberRows - Number of rows in the table.
	 */
	public int getNumberRowsAsPrimitive(final SessionContext ctx)
	{
		Integer value = getNumberRows( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.numberRows</code> attribute. 
	 * @return the numberRows - Number of rows in the table.
	 */
	public int getNumberRowsAsPrimitive()
	{
		return getNumberRowsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.numberRows</code> attribute. 
	 * @param value the numberRows - Number of rows in the table.
	 */
	public void setNumberRows(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, NUMBERROWS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.numberRows</code> attribute. 
	 * @param value the numberRows - Number of rows in the table.
	 */
	public void setNumberRows(final Integer value)
	{
		setNumberRows( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.numberRows</code> attribute. 
	 * @param value the numberRows - Number of rows in the table.
	 */
	public void setNumberRows(final SessionContext ctx, final int value)
	{
		setNumberRows( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.numberRows</code> attribute. 
	 * @param value the numberRows - Number of rows in the table.
	 */
	public void setNumberRows(final int value)
	{
		setNumberRows( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.styleClass</code> attribute.
	 * @return the styleClass - CSS style class of this quick order component.
	 */
	public String getStyleClass(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STYLECLASS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>QuickOrderComponent.styleClass</code> attribute.
	 * @return the styleClass - CSS style class of this quick order component.
	 */
	public String getStyleClass()
	{
		return getStyleClass( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.styleClass</code> attribute. 
	 * @param value the styleClass - CSS style class of this quick order component.
	 */
	public void setStyleClass(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STYLECLASS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>QuickOrderComponent.styleClass</code> attribute. 
	 * @param value the styleClass - CSS style class of this quick order component.
	 */
	public void setStyleClass(final String value)
	{
		setStyleClass( getSession().getSessionContext(), value );
	}
	
}
