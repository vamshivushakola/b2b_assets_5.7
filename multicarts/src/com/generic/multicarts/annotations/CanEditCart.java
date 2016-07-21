package com.generic.multicarts.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Check if the user if the cart owner
 * 
 * @author Capgemini
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value =
{ ElementType.METHOD })
public @interface CanEditCart
{
}
