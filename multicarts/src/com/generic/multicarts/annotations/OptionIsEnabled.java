package com.generic.multicarts.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Check if tan option is enabled
 * 
 * @author Capgemini
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value =
{ ElementType.METHOD })
public @interface OptionIsEnabled
{
	/**
	 * Return the name
	 * 
	 * @return the name
	 */
	String name();
}
