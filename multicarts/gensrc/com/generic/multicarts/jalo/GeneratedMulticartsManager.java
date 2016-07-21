/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Oct 26, 2015 11:47:36 AM                    ---
 * ----------------------------------------------------------------
 */
package com.generic.multicarts.jalo;

import com.generic.multicarts.constants.MulticartsConstants;
import com.generic.multicarts.jalo.CartFollower;
import com.generic.multicarts.jalo.CartLog;
import com.generic.multicarts.jalo.CartShare;
import com.generic.multicarts.jalo.SelectCartComponent;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.Principal;
import de.hybris.platform.jalo.security.PrincipalGroup;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserGroup;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>MulticartsManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMulticartsManager extends Extension
{
	/**
	* {@link OneToManyHandler} for handling 1:n CARTLOGS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<CartLog> USER2CARTLOGRELATIONCARTLOGSHANDLER = new OneToManyHandler<CartLog>(
	MulticartsConstants.TC.CARTLOG,
	false,
	"user",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n FOLLOWERS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<CartFollower> USER2CARTFOLLOWERRELATIONFOLLOWERSHANDLER = new OneToManyHandler<CartFollower>(
	MulticartsConstants.TC.CARTFOLLOWER,
	false,
	"user",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n FOLLOWERS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<CartFollower> CART2CARTFOLLOWERRELATIONFOLLOWERSHANDLER = new OneToManyHandler<CartFollower>(
	MulticartsConstants.TC.CARTFOLLOWER,
	false,
	"cart",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n SHARES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<CartShare> CART2CARTSHARERELATIONSHARESHANDLER = new OneToManyHandler<CartShare>(
	MulticartsConstants.TC.CARTSHARE,
	false,
	"cartSharePK",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n LOGS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<CartLog> PRODUCT2CARTLOGRELATIONLOGSHANDLER = new OneToManyHandler<CartLog>(
	MulticartsConstants.TC.CARTLOG,
	false,
	"product",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n SHARES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<CartShare> USERGROUP2CARTSHARERELATIONSHARESHANDLER = new OneToManyHandler<CartShare>(
	MulticartsConstants.TC.CARTSHARE,
	false,
	"group",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("hasNotification", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.user.User", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("multicartName", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrder", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("persist", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.Cart", Collections.unmodifiableMap(tmp));
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
	 * <i>Generated method</i> - Getter of the <code>User.cartlogs</code> attribute.
	 * @return the cartlogs
	 */
	public Collection<CartLog> getCartlogs(final SessionContext ctx, final User item)
	{
		return USER2CARTLOGRELATIONCARTLOGSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.cartlogs</code> attribute.
	 * @return the cartlogs
	 */
	public Collection<CartLog> getCartlogs(final User item)
	{
		return getCartlogs( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.cartlogs</code> attribute. 
	 * @param value the cartlogs
	 */
	public void setCartlogs(final SessionContext ctx, final User item, final Collection<CartLog> value)
	{
		USER2CARTLOGRELATIONCARTLOGSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.cartlogs</code> attribute. 
	 * @param value the cartlogs
	 */
	public void setCartlogs(final User item, final Collection<CartLog> value)
	{
		setCartlogs( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to cartlogs. 
	 * @param value the item to add to cartlogs
	 */
	public void addToCartlogs(final SessionContext ctx, final User item, final CartLog value)
	{
		USER2CARTLOGRELATIONCARTLOGSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to cartlogs. 
	 * @param value the item to add to cartlogs
	 */
	public void addToCartlogs(final User item, final CartLog value)
	{
		addToCartlogs( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from cartlogs. 
	 * @param value the item to remove from cartlogs
	 */
	public void removeFromCartlogs(final SessionContext ctx, final User item, final CartLog value)
	{
		USER2CARTLOGRELATIONCARTLOGSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from cartlogs. 
	 * @param value the item to remove from cartlogs
	 */
	public void removeFromCartlogs(final User item, final CartLog value)
	{
		removeFromCartlogs( getSession().getSessionContext(), item, value );
	}
	
	public CartFollower createCartFollower(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MulticartsConstants.TC.CARTFOLLOWER );
			return (CartFollower)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating CartFollower : "+e.getMessage(), 0 );
		}
	}
	
	public CartFollower createCartFollower(final Map attributeValues)
	{
		return createCartFollower( getSession().getSessionContext(), attributeValues );
	}
	
	public CartLog createCartLog(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MulticartsConstants.TC.CARTLOG );
			return (CartLog)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating CartLog : "+e.getMessage(), 0 );
		}
	}
	
	public CartLog createCartLog(final Map attributeValues)
	{
		return createCartLog( getSession().getSessionContext(), attributeValues );
	}
	
	public CartShare createCartShare(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MulticartsConstants.TC.CARTSHARE );
			return (CartShare)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating CartShare : "+e.getMessage(), 0 );
		}
	}
	
	public CartShare createCartShare(final Map attributeValues)
	{
		return createCartShare( getSession().getSessionContext(), attributeValues );
	}
	
	public SelectCartComponent createSelectCartComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MulticartsConstants.TC.SELECTCARTCOMPONENT );
			return (SelectCartComponent)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating SelectCartComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SelectCartComponent createSelectCartComponent(final Map attributeValues)
	{
		return createSelectCartComponent( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.followers</code> attribute.
	 * @return the followers
	 */
	public Collection<CartFollower> getFollowers(final SessionContext ctx, final User item)
	{
		return USER2CARTFOLLOWERRELATIONFOLLOWERSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.followers</code> attribute.
	 * @return the followers
	 */
	public Collection<CartFollower> getFollowers(final User item)
	{
		return getFollowers( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.followers</code> attribute. 
	 * @param value the followers
	 */
	public void setFollowers(final SessionContext ctx, final User item, final Collection<CartFollower> value)
	{
		USER2CARTFOLLOWERRELATIONFOLLOWERSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.followers</code> attribute. 
	 * @param value the followers
	 */
	public void setFollowers(final User item, final Collection<CartFollower> value)
	{
		setFollowers( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to followers. 
	 * @param value the item to add to followers
	 */
	public void addToFollowers(final SessionContext ctx, final User item, final CartFollower value)
	{
		USER2CARTFOLLOWERRELATIONFOLLOWERSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to followers. 
	 * @param value the item to add to followers
	 */
	public void addToFollowers(final User item, final CartFollower value)
	{
		addToFollowers( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from followers. 
	 * @param value the item to remove from followers
	 */
	public void removeFromFollowers(final SessionContext ctx, final User item, final CartFollower value)
	{
		USER2CARTFOLLOWERRELATIONFOLLOWERSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from followers. 
	 * @param value the item to remove from followers
	 */
	public void removeFromFollowers(final User item, final CartFollower value)
	{
		removeFromFollowers( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.followers</code> attribute.
	 * @return the followers
	 */
	public Collection<CartFollower> getFollowers(final SessionContext ctx, final Cart item)
	{
		return CART2CARTFOLLOWERRELATIONFOLLOWERSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.followers</code> attribute.
	 * @return the followers
	 */
	public Collection<CartFollower> getFollowers(final Cart item)
	{
		return getFollowers( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.followers</code> attribute. 
	 * @param value the followers
	 */
	public void setFollowers(final SessionContext ctx, final Cart item, final Collection<CartFollower> value)
	{
		CART2CARTFOLLOWERRELATIONFOLLOWERSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.followers</code> attribute. 
	 * @param value the followers
	 */
	public void setFollowers(final Cart item, final Collection<CartFollower> value)
	{
		setFollowers( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to followers. 
	 * @param value the item to add to followers
	 */
	public void addToFollowers(final SessionContext ctx, final Cart item, final CartFollower value)
	{
		CART2CARTFOLLOWERRELATIONFOLLOWERSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to followers. 
	 * @param value the item to add to followers
	 */
	public void addToFollowers(final Cart item, final CartFollower value)
	{
		addToFollowers( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from followers. 
	 * @param value the item to remove from followers
	 */
	public void removeFromFollowers(final SessionContext ctx, final Cart item, final CartFollower value)
	{
		CART2CARTFOLLOWERRELATIONFOLLOWERSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from followers. 
	 * @param value the item to remove from followers
	 */
	public void removeFromFollowers(final Cart item, final CartFollower value)
	{
		removeFromFollowers( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return MulticartsConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.hasNotification</code> attribute.
	 * @return the hasNotification
	 */
	public Boolean isHasNotification(final SessionContext ctx, final User item)
	{
		return (Boolean)item.getProperty( ctx, MulticartsConstants.Attributes.User.HASNOTIFICATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.hasNotification</code> attribute.
	 * @return the hasNotification
	 */
	public Boolean isHasNotification(final User item)
	{
		return isHasNotification( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.hasNotification</code> attribute. 
	 * @return the hasNotification
	 */
	public boolean isHasNotificationAsPrimitive(final SessionContext ctx, final User item)
	{
		Boolean value = isHasNotification( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.hasNotification</code> attribute. 
	 * @return the hasNotification
	 */
	public boolean isHasNotificationAsPrimitive(final User item)
	{
		return isHasNotificationAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.hasNotification</code> attribute. 
	 * @param value the hasNotification
	 */
	public void setHasNotification(final SessionContext ctx, final User item, final Boolean value)
	{
		item.setProperty(ctx, MulticartsConstants.Attributes.User.HASNOTIFICATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.hasNotification</code> attribute. 
	 * @param value the hasNotification
	 */
	public void setHasNotification(final User item, final Boolean value)
	{
		setHasNotification( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.hasNotification</code> attribute. 
	 * @param value the hasNotification
	 */
	public void setHasNotification(final SessionContext ctx, final User item, final boolean value)
	{
		setHasNotification( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.hasNotification</code> attribute. 
	 * @param value the hasNotification
	 */
	public void setHasNotification(final User item, final boolean value)
	{
		setHasNotification( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.logs</code> attribute.
	 * @return the logs
	 */
	public Collection<CartLog> getLogs(final SessionContext ctx, final Product item)
	{
		return PRODUCT2CARTLOGRELATIONLOGSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.logs</code> attribute.
	 * @return the logs
	 */
	public Collection<CartLog> getLogs(final Product item)
	{
		return getLogs( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.logs</code> attribute. 
	 * @param value the logs
	 */
	public void setLogs(final SessionContext ctx, final Product item, final Collection<CartLog> value)
	{
		PRODUCT2CARTLOGRELATIONLOGSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.logs</code> attribute. 
	 * @param value the logs
	 */
	public void setLogs(final Product item, final Collection<CartLog> value)
	{
		setLogs( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to logs. 
	 * @param value the item to add to logs
	 */
	public void addToLogs(final SessionContext ctx, final Product item, final CartLog value)
	{
		PRODUCT2CARTLOGRELATIONLOGSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to logs. 
	 * @param value the item to add to logs
	 */
	public void addToLogs(final Product item, final CartLog value)
	{
		addToLogs( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from logs. 
	 * @param value the item to remove from logs
	 */
	public void removeFromLogs(final SessionContext ctx, final Product item, final CartLog value)
	{
		PRODUCT2CARTLOGRELATIONLOGSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from logs. 
	 * @param value the item to remove from logs
	 */
	public void removeFromLogs(final Product item, final CartLog value)
	{
		removeFromLogs( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.multicartName</code> attribute.
	 * @return the multicartName
	 */
	public String getMulticartName(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, MulticartsConstants.Attributes.AbstractOrder.MULTICARTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.multicartName</code> attribute.
	 * @return the multicartName
	 */
	public String getMulticartName(final AbstractOrder item)
	{
		return getMulticartName( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.multicartName</code> attribute. 
	 * @param value the multicartName
	 */
	public void setMulticartName(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, MulticartsConstants.Attributes.AbstractOrder.MULTICARTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.multicartName</code> attribute. 
	 * @param value the multicartName
	 */
	public void setMulticartName(final AbstractOrder item, final String value)
	{
		setMulticartName( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.persist</code> attribute.
	 * @return the persist
	 */
	public Boolean isPersist(final SessionContext ctx, final Cart item)
	{
		return (Boolean)item.getProperty( ctx, MulticartsConstants.Attributes.Cart.PERSIST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.persist</code> attribute.
	 * @return the persist
	 */
	public Boolean isPersist(final Cart item)
	{
		return isPersist( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.persist</code> attribute. 
	 * @return the persist
	 */
	public boolean isPersistAsPrimitive(final SessionContext ctx, final Cart item)
	{
		Boolean value = isPersist( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.persist</code> attribute. 
	 * @return the persist
	 */
	public boolean isPersistAsPrimitive(final Cart item)
	{
		return isPersistAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.persist</code> attribute. 
	 * @param value the persist
	 */
	public void setPersist(final SessionContext ctx, final Cart item, final Boolean value)
	{
		item.setProperty(ctx, MulticartsConstants.Attributes.Cart.PERSIST,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.persist</code> attribute. 
	 * @param value the persist
	 */
	public void setPersist(final Cart item, final Boolean value)
	{
		setPersist( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.persist</code> attribute. 
	 * @param value the persist
	 */
	public void setPersist(final SessionContext ctx, final Cart item, final boolean value)
	{
		setPersist( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.persist</code> attribute. 
	 * @param value the persist
	 */
	public void setPersist(final Cart item, final boolean value)
	{
		setPersist( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.shares</code> attribute.
	 * @return the shares
	 */
	public Collection<CartShare> getShares(final SessionContext ctx, final Cart item)
	{
		return CART2CARTSHARERELATIONSHARESHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.shares</code> attribute.
	 * @return the shares
	 */
	public Collection<CartShare> getShares(final Cart item)
	{
		return getShares( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.shares</code> attribute. 
	 * @param value the shares
	 */
	public void setShares(final SessionContext ctx, final Cart item, final Collection<CartShare> value)
	{
		CART2CARTSHARERELATIONSHARESHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.shares</code> attribute. 
	 * @param value the shares
	 */
	public void setShares(final Cart item, final Collection<CartShare> value)
	{
		setShares( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to shares. 
	 * @param value the item to add to shares
	 */
	public void addToShares(final SessionContext ctx, final Cart item, final CartShare value)
	{
		CART2CARTSHARERELATIONSHARESHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to shares. 
	 * @param value the item to add to shares
	 */
	public void addToShares(final Cart item, final CartShare value)
	{
		addToShares( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from shares. 
	 * @param value the item to remove from shares
	 */
	public void removeFromShares(final SessionContext ctx, final Cart item, final CartShare value)
	{
		CART2CARTSHARERELATIONSHARESHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from shares. 
	 * @param value the item to remove from shares
	 */
	public void removeFromShares(final Cart item, final CartShare value)
	{
		removeFromShares( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserGroup.shares</code> attribute.
	 * @return the shares
	 */
	public Collection<CartShare> getShares(final SessionContext ctx, final UserGroup item)
	{
		return USERGROUP2CARTSHARERELATIONSHARESHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UserGroup.shares</code> attribute.
	 * @return the shares
	 */
	public Collection<CartShare> getShares(final UserGroup item)
	{
		return getShares( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserGroup.shares</code> attribute. 
	 * @param value the shares
	 */
	public void setShares(final SessionContext ctx, final UserGroup item, final Collection<CartShare> value)
	{
		USERGROUP2CARTSHARERELATIONSHARESHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UserGroup.shares</code> attribute. 
	 * @param value the shares
	 */
	public void setShares(final UserGroup item, final Collection<CartShare> value)
	{
		setShares( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to shares. 
	 * @param value the item to add to shares
	 */
	public void addToShares(final SessionContext ctx, final UserGroup item, final CartShare value)
	{
		USERGROUP2CARTSHARERELATIONSHARESHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to shares. 
	 * @param value the item to add to shares
	 */
	public void addToShares(final UserGroup item, final CartShare value)
	{
		addToShares( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from shares. 
	 * @param value the item to remove from shares
	 */
	public void removeFromShares(final SessionContext ctx, final UserGroup item, final CartShare value)
	{
		USERGROUP2CARTSHARERELATIONSHARESHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from shares. 
	 * @param value the item to remove from shares
	 */
	public void removeFromShares(final UserGroup item, final CartShare value)
	{
		removeFromShares( getSession().getSessionContext(), item, value );
	}
	
}
