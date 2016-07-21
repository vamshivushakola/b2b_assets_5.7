package com.generic.multicarts.strategy;

/**
 * Strategy to edit multicarts
 * 
 * @author Capgemini
 */
public interface MultiCartsEditStrategy
{
	/**
	 * edit cart
	 * 
	 * @param code
	 *           the code
	 * @return boolean value
	 */
	boolean canEditCart(String code);
}
