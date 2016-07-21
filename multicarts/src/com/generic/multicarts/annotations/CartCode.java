package com.generic.multicarts.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Parameter annotation Used to select the cart code parameter
 * 
 * @author Capgemini
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value =
{ ElementType.PARAMETER })
public @interface CartCode
{
}
