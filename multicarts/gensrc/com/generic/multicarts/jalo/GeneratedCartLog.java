/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Oct 26, 2015 11:47:36 AM                    ---
 * ----------------------------------------------------------------
 */
package com.generic.multicarts.jalo;

import com.generic.multicarts.constants.MulticartsConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem CartLog}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCartLog extends GenericItem
{
	/** Qualifier of the <code>CartLog.message</code> attribute **/
	public static final String MESSAGE = "message";
	/** Qualifier of the <code>CartLog.orderCode</code> attribute **/
	public static final String ORDERCODE = "orderCode";
	/** Qualifier of the <code>CartLog.oldValue</code> attribute **/
	public static final String OLDVALUE = "oldValue";
	/** Qualifier of the <code>CartLog.newValue</code> attribute **/
	public static final String NEWVALUE = "newValue";
	/** Qualifier of the <code>CartLog.user</code> attribute **/
	public static final String USER = "user";
	/** Qualifier of the <code>CartLog.product</code> attribute **/
	public static final String PRODUCT = "product";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n USER's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedCartLog> USERHANDLER = new BidirectionalOneToManyHandler<GeneratedCartLog>(
	MulticartsConstants.TC.CARTLOG,
	false,
	"user",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n PRODUCT's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedCartLog> PRODUCTHANDLER = new BidirectionalOneToManyHandler<GeneratedCartLog>(
	MulticartsConstants.TC.CARTLOG,
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
		tmp.put(MESSAGE, AttributeMode.INITIAL);
		tmp.put(ORDERCODE, AttributeMode.INITIAL);
		tmp.put(OLDVALUE, AttributeMode.INITIAL);
		tmp.put(NEWVALUE, AttributeMode.INITIAL);
		tmp.put(USER, AttributeMode.INITIAL);
		tmp.put(PRODUCT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		USERHANDLER.newInstance(ctx, allAttributes);
		PRODUCTHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.message</code> attribute.
	 * @return the message
	 */
	public String getMessage(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.message</code> attribute.
	 * @return the message
	 */
	public String getMessage()
	{
		return getMessage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.message</code> attribute. 
	 * @param value the message
	 */
	public void setMessage(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.message</code> attribute. 
	 * @param value the message
	 */
	public void setMessage(final String value)
	{
		setMessage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.newValue</code> attribute.
	 * @return the newValue
	 */
	public String getNewValue(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NEWVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.newValue</code> attribute.
	 * @return the newValue
	 */
	public String getNewValue()
	{
		return getNewValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.newValue</code> attribute. 
	 * @param value the newValue
	 */
	public void setNewValue(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NEWVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.newValue</code> attribute. 
	 * @param value the newValue
	 */
	public void setNewValue(final String value)
	{
		setNewValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.oldValue</code> attribute.
	 * @return the oldValue
	 */
	public String getOldValue(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OLDVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.oldValue</code> attribute.
	 * @return the oldValue
	 */
	public String getOldValue()
	{
		return getOldValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.oldValue</code> attribute. 
	 * @param value the oldValue
	 */
	public void setOldValue(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OLDVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.oldValue</code> attribute. 
	 * @param value the oldValue
	 */
	public void setOldValue(final String value)
	{
		setOldValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.orderCode</code> attribute.
	 * @return the orderCode
	 */
	public String getOrderCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.orderCode</code> attribute.
	 * @return the orderCode
	 */
	public String getOrderCode()
	{
		return getOrderCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.orderCode</code> attribute. 
	 * @param value the orderCode
	 */
	public void setOrderCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.orderCode</code> attribute. 
	 * @param value the orderCode
	 */
	public void setOrderCode(final String value)
	{
		setOrderCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.product</code> attribute.
	 * @return the product
	 */
	public Product getProduct(final SessionContext ctx)
	{
		return (Product)getProperty( ctx, PRODUCT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.product</code> attribute.
	 * @return the product
	 */
	public Product getProduct()
	{
		return getProduct( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.product</code> attribute. 
	 * @param value the product
	 */
	public void setProduct(final SessionContext ctx, final Product value)
	{
		PRODUCTHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.product</code> attribute. 
	 * @param value the product
	 */
	public void setProduct(final Product value)
	{
		setProduct( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.user</code> attribute.
	 * @return the user
	 */
	public User getUser(final SessionContext ctx)
	{
		return (User)getProperty( ctx, USER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartLog.user</code> attribute.
	 * @return the user
	 */
	public User getUser()
	{
		return getUser( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.user</code> attribute. 
	 * @param value the user
	 */
	public void setUser(final SessionContext ctx, final User value)
	{
		USERHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartLog.user</code> attribute. 
	 * @param value the user
	 */
	public void setUser(final User value)
	{
		setUser( getSession().getSessionContext(), value );
	}
	
}
