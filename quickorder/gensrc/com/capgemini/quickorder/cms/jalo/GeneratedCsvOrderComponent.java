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
 * Generated class for type {@link com.capgemini.quickorder.cms.jalo.CsvOrderComponent CsvOrderComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCsvOrderComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>CsvOrderComponent.file</code> attribute **/
	public static final String FILE = "file";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FILE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CsvOrderComponent.file</code> attribute.
	 * @return the file - To Store CSV file
	 */
	public String getFile(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CsvOrderComponent.file</code> attribute.
	 * @return the file - To Store CSV file
	 */
	public String getFile()
	{
		return getFile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CsvOrderComponent.file</code> attribute. 
	 * @param value the file - To Store CSV file
	 */
	public void setFile(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CsvOrderComponent.file</code> attribute. 
	 * @param value the file - To Store CSV file
	 */
	public void setFile(final String value)
	{
		setFile( getSession().getSessionContext(), value );
	}
	
}
