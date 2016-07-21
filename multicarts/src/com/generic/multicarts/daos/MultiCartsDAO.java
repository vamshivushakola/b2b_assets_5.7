package com.generic.multicarts.daos;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.List;

import com.generic.multicarts.model.CartFollowerModel;


/**
 * MulticartsDAO: Add DAO methods for multicarts
 * 
 * @author Capgemini
 */
public interface MultiCartsDAO
{
	/**
	 * Find cart by code
	 * 
	 * @param cartCode
	 *           The cart Code
	 * @return The cartModel
	 */
	List<CartModel> findCartByCode(String cartCode);

	/**
	 * Return all cart for the user
	 * 
	 * @param user
	 *           The user
	 * @return CartModel list
	 */
	List<CartModel> getCarts(UserModel user);

	/**
	 * Return all cart for follower by cart and user
	 * 
	 * @param user
	 *           the user
	 * @param cart
	 *           the cart
	 * @return CartFollowerModel list
	 */
	List<CartFollowerModel> getFollowerByCartAndUser(UserModel user, CartModel cart);
}
