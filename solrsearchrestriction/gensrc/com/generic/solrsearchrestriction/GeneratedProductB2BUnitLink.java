/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Oct 28, 2015 5:38:42 PM                     ---
 * ----------------------------------------------------------------
 */
package com.generic.solrsearchrestriction;

import com.generic.solrsearchrestriction.constants.SolrsearchrestrictionConstants;
import de.hybris.platform.b2b.jalo.B2BUnit;
import de.hybris.platform.catalog.jalo.CatalogVersion;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.generic.solrsearchrestriction.ProductB2BUnitLink ProductB2BUnitLink}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductB2BUnitLink extends GenericItem
{
	/** Qualifier of the <code>ProductB2BUnitLink.isDisplayable</code> attribute **/
	public static final String ISDISPLAYABLE = "isDisplayable";
	/** Qualifier of the <code>ProductB2BUnitLink.catalogVersion</code> attribute **/
	public static final String CATALOGVERSION = "catalogVersion";
	/** Qualifier of the <code>ProductB2BUnitLink.b2bunit</code> attribute **/
	public static final String B2BUNIT = "b2bunit";
	/** Qualifier of the <code>ProductB2BUnitLink.product</code> attribute **/
	public static final String PRODUCT = "product";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n B2BUNIT's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedProductB2BUnitLink> B2BUNITHANDLER = new BidirectionalOneToManyHandler<GeneratedProductB2BUnitLink>(
	SolrsearchrestrictionConstants.TC.PRODUCTB2BUNITLINK,
	false,
	"b2bunit",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n PRODUCT's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedProductB2BUnitLink> PRODUCTHANDLER = new BidirectionalOneToManyHandler<GeneratedProductB2BUnitLink>(
	SolrsearchrestrictionConstants.TC.PRODUCTB2BUNITLINK,
	false,
	"product",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ISDISPLAYABLE, AttributeMode.INITIAL);
		tmp.put(CATALOGVERSION, AttributeMode.INITIAL);
		tmp.put(B2BUNIT, AttributeMode.INITIAL);
		tmp.put(PRODUCT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.b2bunit</code> attribute.
	 * @return the b2bunit
	 */
	public B2BUnit getB2bunit(final SessionContext ctx)
	{
		return (B2BUnit)getProperty( ctx, B2BUNIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.b2bunit</code> attribute.
	 * @return the b2bunit
	 */
	public B2BUnit getB2bunit()
	{
		return getB2bunit( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.b2bunit</code> attribute. 
	 * @param value the b2bunit
	 */
	public void setB2bunit(final SessionContext ctx, final B2BUnit value)
	{
		B2BUNITHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.b2bunit</code> attribute. 
	 * @param value the b2bunit
	 */
	public void setB2bunit(final B2BUnit value)
	{
		setB2bunit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.catalogVersion</code> attribute.
	 * @return the catalogVersion
	 */
	public CatalogVersion getCatalogVersion(final SessionContext ctx)
	{
		return (CatalogVersion)getProperty( ctx, CATALOGVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.catalogVersion</code> attribute.
	 * @return the catalogVersion
	 */
	public CatalogVersion getCatalogVersion()
	{
		return getCatalogVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.catalogVersion</code> attribute. 
	 * @param value the catalogVersion
	 */
	protected void setCatalogVersion(final SessionContext ctx, final CatalogVersion value)
	{
		// initial-only attribute: make sure this attribute can be set during item creation only
		if ( ctx.getAttribute( "core.types.creation.initial") != Boolean.TRUE )
		{
			throw new JaloInvalidParameterException( "attribute '"+CATALOGVERSION+"' is not changeable", 0 );
		}
		setProperty(ctx, CATALOGVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.catalogVersion</code> attribute. 
	 * @param value the catalogVersion
	 */
	protected void setCatalogVersion(final CatalogVersion value)
	{
		setCatalogVersion( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		B2BUNITHANDLER.newInstance(ctx, allAttributes);
		PRODUCTHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.isDisplayable</code> attribute.
	 * @return the isDisplayable - Information to display the right products to the client
	 */
	public Boolean isIsDisplayable(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISDISPLAYABLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.isDisplayable</code> attribute.
	 * @return the isDisplayable - Information to display the right products to the client
	 */
	public Boolean isIsDisplayable()
	{
		return isIsDisplayable( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.isDisplayable</code> attribute. 
	 * @return the isDisplayable - Information to display the right products to the client
	 */
	public boolean isIsDisplayableAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsDisplayable( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.isDisplayable</code> attribute. 
	 * @return the isDisplayable - Information to display the right products to the client
	 */
	public boolean isIsDisplayableAsPrimitive()
	{
		return isIsDisplayableAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.isDisplayable</code> attribute. 
	 * @param value the isDisplayable - Information to display the right products to the client
	 */
	public void setIsDisplayable(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISDISPLAYABLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.isDisplayable</code> attribute. 
	 * @param value the isDisplayable - Information to display the right products to the client
	 */
	public void setIsDisplayable(final Boolean value)
	{
		setIsDisplayable( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.isDisplayable</code> attribute. 
	 * @param value the isDisplayable - Information to display the right products to the client
	 */
	public void setIsDisplayable(final SessionContext ctx, final boolean value)
	{
		setIsDisplayable( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.isDisplayable</code> attribute. 
	 * @param value the isDisplayable - Information to display the right products to the client
	 */
	public void setIsDisplayable(final boolean value)
	{
		setIsDisplayable( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.product</code> attribute.
	 * @return the product
	 */
	public Product getProduct(final SessionContext ctx)
	{
		return (Product)getProperty( ctx, PRODUCT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductB2BUnitLink.product</code> attribute.
	 * @return the product
	 */
	public Product getProduct()
	{
		return getProduct( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.product</code> attribute. 
	 * @param value the product
	 */
	public void setProduct(final SessionContext ctx, final Product value)
	{
		PRODUCTHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductB2BUnitLink.product</code> attribute. 
	 * @param value the product
	 */
	public void setProduct(final Product value)
	{
		setProduct( getSession().getSessionContext(), value );
	}
	
}
