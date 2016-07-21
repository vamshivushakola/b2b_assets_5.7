package com.generic.multicarts.aspects;

import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.lang.annotation.Annotation;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.generic.multicarts.annotations.CartCode;
import com.generic.multicarts.annotations.OptionIsEnabled;
import com.generic.multicarts.strategy.MultiCartsEditStrategy;


/**
 * This class contains all aspects for the multicarts All annotations are intercepeted here
 * 
 * @author Capgemini
 */
@Aspect
@Component
public class MultiCartAnnotationsAspect
{
	@Resource
	private ConfigurationService configurationService;

	@Resource
	private MultiCartsEditStrategy multiCartsEditStrategy;


	/**
	 * Check for option is enabled
	 * 
	 * @param joinPoint
	 *           ProceedingJoinPoint object
	 * @return the Object
	 * @throws Throwable
	 *            Throwable
	 */
	@Around(value = "@within(com.generic.multicarts.annotations.OptionIsEnabled) || @annotation(com.generic.multicarts.annotations.OptionIsEnabled)")
	public Object optionIsEnabled(final ProceedingJoinPoint joinPoint) throws Throwable
	{
		final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		final OptionIsEnabled annotation = signature.getMethod().getAnnotation(OptionIsEnabled.class);
		if (configurationService.getConfiguration().getBoolean(annotation.name(), true))
		{
			return joinPoint.proceed();
		}
		throw new ResourceNotFoundException();
	}

	/**
	 * Check if the current can edit the cart
	 * 
	 * @param joinPoint
	 *           ProceedingJoinPoint object
	 * @return the Object
	 * @throws Throwable
	 *            Throwable
	 */
	@Around(value = "@within(com.generic.multicarts.annotations.CanEditCart) || @annotation(com.generic.multicarts.annotations.CanEditCart)")
	public Object canEditCart(final ProceedingJoinPoint joinPoint) throws Throwable
	{
		final String value = (String) getParam(joinPoint, CartCode.class);
		if (value == null || multiCartsEditStrategy.canEditCart(value))
		{
			return joinPoint.proceed();
		}
		else
		{
			throw new AccessDeniedException();
		}
	}

	/**
	 * Get parameters for provided fields
	 * 
	 * @param joinPoint
	 *           joinPoint
	 * @param annotationClass
	 *           annotationClass
	 * @return the Object
	 */
	protected Object getParam(final JoinPoint joinPoint, final Class<?> annotationClass)
	{
		final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		final Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
		for (int i = 0; i < parameterAnnotations.length; i++)
		{
			if (hasAnnotation(parameterAnnotations[i], annotationClass))
			{
				return joinPoint.getArgs()[i];
			}
		}
		return null;
	}

	/**
	 * check isInstance
	 * 
	 * @param annotation
	 *           annotation
	 * @param annotationClass
	 *           annotationClass
	 * @return the boolean value
	 */
	protected boolean hasAnnotation(final Annotation[] annotation, final Class<?> annotationClass)
	{
		boolean find = false;
		final int size = annotation.length;
		for (int i = 0; i < size && !find; i++)
		{
			find = annotationClass.isInstance(annotation[i]);
		}
		return find;
	}

	/**
	 * ResourceNotFoundException
	 * 
	 */
	@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
	public final class ResourceNotFoundException extends RuntimeException
	{
	}

	/**
	 * AccessDeniedException
	 * 
	 */
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public final class AccessDeniedException extends RuntimeException
	{
	}
}
