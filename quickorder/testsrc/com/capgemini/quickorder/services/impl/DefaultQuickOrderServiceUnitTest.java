package com.capgemini.quickorder.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.quickorder.strategies.QuickOrderSearchStrategy;


/**
 * Created by tbrison on 13/06/2014.
 */
@UnitTest
public class DefaultQuickOrderServiceUnitTest
{
	private DefaultQuickOrderService quickOrderService;
	private QuickOrderSearchStrategy quickOrderSearchStrategy;

	private ProductModel productModel;
	private final static String REFERENCE_QUERY = "fo";
	private final static String REFERENCE = "foo";
	private final static String CODE_QUERY = "ba";
	private final static String CODE = "bar";
	private final static int NUMBER_RESULTS = 4;

	@Before
	public void setUp()
	{
		quickOrderService = new DefaultQuickOrderService();
		quickOrderSearchStrategy = mock(QuickOrderSearchStrategy.class);
		quickOrderService.setQuickOrderSearchStrategy(quickOrderSearchStrategy);

		productModel = new ProductModel();
		productModel.setCode(CODE);
		//productModel.setReference(REFERENCE);
	}

	@Test
	public void testFindByCode()
	{
		when(quickOrderSearchStrategy.findProductsForQuery(CODE_QUERY, NUMBER_RESULTS)).thenReturn(
				Collections.singletonList(productModel));
		final List<ProductModel> result = quickOrderService.findProductsForQuery(CODE_QUERY, NUMBER_RESULTS);
		assertEquals("We should find one product", 1, result.size());
		assertEquals("Product should equal the mock returned", productModel, result.get(0));
	}
}
