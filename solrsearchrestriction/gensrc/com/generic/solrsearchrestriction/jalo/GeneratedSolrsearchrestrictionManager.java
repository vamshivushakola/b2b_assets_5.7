/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Oct 28, 2015 5:38:42 PM                     ---
 * ----------------------------------------------------------------
 */
package com.generic.solrsearchrestriction.jalo;

import com.generic.solrsearchrestriction.ProductB2BUnitLink;
import com.generic.solrsearchrestriction.constants.SolrsearchrestrictionConstants;
import de.hybris.platform.b2b.jalo.B2BUnit;
import de.hybris.platform.catalog.jalo.Company;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>SolrsearchrestrictionManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSolrsearchrestrictionManager extends Extension
{
	/**
	* {@link OneToManyHandler} for handling 1:n PRODUCTLINKS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<ProductB2BUnitLink> B2BUNIT2PRODUCTB2BUNITLINKPRODUCTLINKSHANDLER = new OneToManyHandler<ProductB2BUnitLink>(
	SolrsearchrestrictionConstants.TC.PRODUCTB2BUNITLINK,
	false,
	"b2bunit",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n B2BUNITLINKS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<ProductB2BUnitLink> PRODUCT2PRODUCTB2BUNITLINKB2BUNITLINKSHANDLER = new OneToManyHandler<ProductB2BUnitLink>(
	SolrsearchrestrictionConstants.TC.PRODUCTB2BUNITLINK,
	true,
	"product",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
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
	 * <i>Generated method</i> - Getter of the <code>Product.b2bUnitLinks</code> attribute.
	 * @return the b2bUnitLinks
	 */
	public Collection<ProductB2BUnitLink> getB2bUnitLinks(final SessionContext ctx, final Product item)
	{
		return PRODUCT2PRODUCTB2BUNITLINKB2BUNITLINKSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.b2bUnitLinks</code> attribute.
	 * @return the b2bUnitLinks
	 */
	public Collection<ProductB2BUnitLink> getB2bUnitLinks(final Product item)
	{
		return getB2bUnitLinks( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.b2bUnitLinks</code> attribute. 
	 * @param value the b2bUnitLinks
	 */
	public void setB2bUnitLinks(final SessionContext ctx, final Product item, final Collection<ProductB2BUnitLink> value)
	{
		PRODUCT2PRODUCTB2BUNITLINKB2BUNITLINKSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.b2bUnitLinks</code> attribute. 
	 * @param value the b2bUnitLinks
	 */
	public void setB2bUnitLinks(final Product item, final Collection<ProductB2BUnitLink> value)
	{
		setB2bUnitLinks( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to b2bUnitLinks. 
	 * @param value the item to add to b2bUnitLinks
	 */
	public void addToB2bUnitLinks(final SessionContext ctx, final Product item, final ProductB2BUnitLink value)
	{
		PRODUCT2PRODUCTB2BUNITLINKB2BUNITLINKSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to b2bUnitLinks. 
	 * @param value the item to add to b2bUnitLinks
	 */
	public void addToB2bUnitLinks(final Product item, final ProductB2BUnitLink value)
	{
		addToB2bUnitLinks( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from b2bUnitLinks. 
	 * @param value the item to remove from b2bUnitLinks
	 */
	public void removeFromB2bUnitLinks(final SessionContext ctx, final Product item, final ProductB2BUnitLink value)
	{
		PRODUCT2PRODUCTB2BUNITLINKB2BUNITLINKSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from b2bUnitLinks. 
	 * @param value the item to remove from b2bUnitLinks
	 */
	public void removeFromB2bUnitLinks(final Product item, final ProductB2BUnitLink value)
	{
		removeFromB2bUnitLinks( getSession().getSessionContext(), item, value );
	}
	
	public ProductB2BUnitLink createProductB2BUnitLink(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( SolrsearchrestrictionConstants.TC.PRODUCTB2BUNITLINK );
			return (ProductB2BUnitLink)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductB2BUnitLink : "+e.getMessage(), 0 );
		}
	}
	
	public ProductB2BUnitLink createProductB2BUnitLink(final Map attributeValues)
	{
		return createProductB2BUnitLink( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return SolrsearchrestrictionConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BUnit.productLinks</code> attribute.
	 * @return the productLinks
	 */
	public Collection<ProductB2BUnitLink> getProductLinks(final SessionContext ctx, final B2BUnit item)
	{
		return B2BUNIT2PRODUCTB2BUNITLINKPRODUCTLINKSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>B2BUnit.productLinks</code> attribute.
	 * @return the productLinks
	 */
	public Collection<ProductB2BUnitLink> getProductLinks(final B2BUnit item)
	{
		return getProductLinks( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>B2BUnit.productLinks</code> attribute. 
	 * @param value the productLinks
	 */
	public void setProductLinks(final SessionContext ctx, final B2BUnit item, final Collection<ProductB2BUnitLink> value)
	{
		B2BUNIT2PRODUCTB2BUNITLINKPRODUCTLINKSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>B2BUnit.productLinks</code> attribute. 
	 * @param value the productLinks
	 */
	public void setProductLinks(final B2BUnit item, final Collection<ProductB2BUnitLink> value)
	{
		setProductLinks( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to productLinks. 
	 * @param value the item to add to productLinks
	 */
	public void addToProductLinks(final SessionContext ctx, final B2BUnit item, final ProductB2BUnitLink value)
	{
		B2BUNIT2PRODUCTB2BUNITLINKPRODUCTLINKSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to productLinks. 
	 * @param value the item to add to productLinks
	 */
	public void addToProductLinks(final B2BUnit item, final ProductB2BUnitLink value)
	{
		addToProductLinks( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from productLinks. 
	 * @param value the item to remove from productLinks
	 */
	public void removeFromProductLinks(final SessionContext ctx, final B2BUnit item, final ProductB2BUnitLink value)
	{
		B2BUNIT2PRODUCTB2BUNITLINKPRODUCTLINKSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from productLinks. 
	 * @param value the item to remove from productLinks
	 */
	public void removeFromProductLinks(final B2BUnit item, final ProductB2BUnitLink value)
	{
		removeFromProductLinks( getSession().getSessionContext(), item, value );
	}
	
}
