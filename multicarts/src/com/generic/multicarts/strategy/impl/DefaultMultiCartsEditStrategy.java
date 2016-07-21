package com.generic.multicarts.strategy.impl;

import com.generic.multicarts.strategy.MultiCartsEditStrategy;


/**
 * Strategy to edit multicarts
 * 
 * @author Capgemini
 */
public class DefaultMultiCartsEditStrategy implements MultiCartsEditStrategy
{
	@Override
	public boolean canEditCart(final String code)
	{
		return true;
	}
}
