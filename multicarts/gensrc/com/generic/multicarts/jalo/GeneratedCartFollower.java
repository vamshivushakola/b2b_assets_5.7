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
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem CartFollower}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCartFollower extends GenericItem
{
	/** Qualifier of the <code>CartFollower.notification</code> attribute **/
	public static final String NOTIFICATION = "notification";
	/** Qualifier of the <code>CartFollower.cart</code> attribute **/
	public static final String CART = "cart";
	/** Qualifier of the <code>CartFollower.user</code> attribute **/
	public static final String USER = "user";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n CART's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedCartFollower> CARTHANDLER = new BidirectionalOneToManyHandler<GeneratedCartFollower>(
	MulticartsConstants.TC.CARTFOLLOWER,
	false,
	"cart",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n USER's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedCartFollower> USERHANDLER = new BidirectionalOneToManyHandler<GeneratedCartFollower>(
	MulticartsConstants.TC.CARTFOLLOWER,
	false,
	"user",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(NOTIFICATION, AttributeMode.INITIAL);
		tmp.put(CART, AttributeMode.INITIAL);
		tmp.put(USER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartFollower.cart</code> attribute.
	 * @return the cart
	 */
	public Cart getCart(final SessionContext ctx)
	{
		return (Cart)getProperty( ctx, CART);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartFollower.cart</code> attribute.
	 * @return the cart
	 */
	public Cart getCart()
	{
		return getCart( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartFollower.cart</code> attribute. 
	 * @param value the cart
	 */
	public void setCart(final SessionContext ctx, final Cart value)
	{
		CARTHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartFollower.cart</code> attribute. 
	 * @param value the cart
	 */
	public void setCart(final Cart value)
	{
		setCart( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		CARTHANDLER.newInstance(ctx, allAttributes);
		USERHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartFollower.notification</code> attribute.
	 * @return the notification
	 */
	public Boolean isNotification(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, NOTIFICATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartFollower.notification</code> attribute.
	 * @return the notification
	 */
	public Boolean isNotification()
	{
		return isNotification( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartFollower.notification</code> attribute. 
	 * @return the notification
	 */
	public boolean isNotificationAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isNotification( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartFollower.notification</code> attribute. 
	 * @return the notification
	 */
	public boolean isNotificationAsPrimitive()
	{
		return isNotificationAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartFollower.notification</code> attribute. 
	 * @param value the notification
	 */
	public void setNotification(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, NOTIFICATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartFollower.notification</code> attribute. 
	 * @param value the notification
	 */
	public void setNotification(final Boolean value)
	{
		setNotification( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartFollower.notification</code> attribute. 
	 * @param value the notification
	 */
	public void setNotification(final SessionContext ctx, final boolean value)
	{
		setNotification( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartFollower.notification</code> attribute. 
	 * @param value the notification
	 */
	public void setNotification(final boolean value)
	{
		setNotification( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartFollower.user</code> attribute.
	 * @return the user
	 */
	public User getUser(final SessionContext ctx)
	{
		return (User)getProperty( ctx, USER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartFollower.user</code> attribute.
	 * @return the user
	 */
	public User getUser()
	{
		return getUser( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartFollower.user</code> attribute. 
	 * @param value the user
	 */
	public void setUser(final SessionContext ctx, final User value)
	{
		USERHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartFollower.user</code> attribute. 
	 * @param value the user
	 */
	public void setUser(final User value)
	{
		setUser( getSession().getSessionContext(), value );
	}
	
}
