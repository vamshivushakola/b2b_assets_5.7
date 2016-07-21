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
import de.hybris.platform.jalo.user.UserGroup;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem CartShare}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCartShare extends GenericItem
{
	/** Qualifier of the <code>CartShare.cartSharePK</code> attribute **/
	public static final String CARTSHAREPK = "cartSharePK";
	/** Qualifier of the <code>CartShare.group</code> attribute **/
	public static final String GROUP = "group";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n CARTSHAREPK's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedCartShare> CARTSHAREPKHANDLER = new BidirectionalOneToManyHandler<GeneratedCartShare>(
	MulticartsConstants.TC.CARTSHARE,
	false,
	"cartSharePK",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n GROUP's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedCartShare> GROUPHANDLER = new BidirectionalOneToManyHandler<GeneratedCartShare>(
	MulticartsConstants.TC.CARTSHARE,
	false,
	"group",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CARTSHAREPK, AttributeMode.INITIAL);
		tmp.put(GROUP, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartShare.cartSharePK</code> attribute.
	 * @return the cartSharePK
	 */
	public Cart getCartSharePK(final SessionContext ctx)
	{
		return (Cart)getProperty( ctx, CARTSHAREPK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartShare.cartSharePK</code> attribute.
	 * @return the cartSharePK
	 */
	public Cart getCartSharePK()
	{
		return getCartSharePK( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartShare.cartSharePK</code> attribute. 
	 * @param value the cartSharePK
	 */
	public void setCartSharePK(final SessionContext ctx, final Cart value)
	{
		CARTSHAREPKHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartShare.cartSharePK</code> attribute. 
	 * @param value the cartSharePK
	 */
	public void setCartSharePK(final Cart value)
	{
		setCartSharePK( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		CARTSHAREPKHANDLER.newInstance(ctx, allAttributes);
		GROUPHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartShare.group</code> attribute.
	 * @return the group
	 */
	public UserGroup getGroup(final SessionContext ctx)
	{
		return (UserGroup)getProperty( ctx, GROUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CartShare.group</code> attribute.
	 * @return the group
	 */
	public UserGroup getGroup()
	{
		return getGroup( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartShare.group</code> attribute. 
	 * @param value the group
	 */
	public void setGroup(final SessionContext ctx, final UserGroup value)
	{
		GROUPHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CartShare.group</code> attribute. 
	 * @param value the group
	 */
	public void setGroup(final UserGroup value)
	{
		setGroup( getSession().getSessionContext(), value );
	}
	
}
