/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Oct 26, 2015 11:47:36 AM                    ---
 * ----------------------------------------------------------------
 */
package com.generic.multicarts.constants;

/**
 * @deprecated use constants in Model classes instead
 */
@Deprecated
@SuppressWarnings({"unused","cast","PMD"})
public class GeneratedMulticartsConstants
{
	public static final String EXTENSIONNAME = "multicarts";
	public static class TC
	{
		public static final String CARTFOLLOWER = "CartFollower".intern();
		public static final String CARTLOG = "CartLog".intern();
		public static final String CARTSHARE = "CartShare".intern();
		public static final String SELECTCARTCOMPONENT = "SelectCartComponent".intern();
	}
	public static class Attributes
	{
		public static class AbstractOrder
		{
			public static final String MULTICARTNAME = "multicartName".intern();
		}
		public static class Cart
		{
			public static final String FOLLOWER = "follower".intern();
			public static final String FOLLOWERS = "followers".intern();
			public static final String HASNOTIFICATION = "hasNotification".intern();
			public static final String ISOWNER = "isOwner".intern();
			public static final String ISSHARED = "isShared".intern();
			public static final String PERSIST = "persist".intern();
			public static final String SHARES = "shares".intern();
		}
		public static class Product
		{
			public static final String LOGS = "logs".intern();
		}
		public static class User
		{
			public static final String CARTLOGS = "cartlogs".intern();
			public static final String FOLLOWERS = "followers".intern();
			public static final String HASNOTIFICATION = "hasNotification".intern();
		}
		public static class UserGroup
		{
			public static final String SHARES = "shares".intern();
		}
	}
	public static class Relations
	{
		public static final String CART2CARTFOLLOWERRELATION = "Cart2CartFollowerRelation".intern();
		public static final String CART2CARTSHARERELATION = "Cart2CartShareRelation".intern();
		public static final String PRODUCT2CARTLOGRELATION = "Product2CartLogRelation".intern();
		public static final String USER2CARTFOLLOWERRELATION = "User2CartFollowerRelation".intern();
		public static final String USER2CARTLOGRELATION = "User2CartLogRelation".intern();
		public static final String USERGROUP2CARTSHARERELATION = "UserGroup2CartShareRelation".intern();
	}
	
	protected GeneratedMulticartsConstants()
	{
		// private constructor
	}
	
	
}
