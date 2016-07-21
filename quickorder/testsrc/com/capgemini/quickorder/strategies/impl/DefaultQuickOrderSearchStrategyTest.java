package com.capgemini.quickorder.strategies.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.quickorder.daos.QuickOrderDAO;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;


/**
 * <p>
 * Unit test for DefaultQuickOrderSearchStrategy
 * </p>
 *
 * @author Capgemini
 */
@UnitTest
public class DefaultQuickOrderSearchStrategyTest
{
	private DefaultQuickOrderSearchStrategy defaultQuickOrderSearchStrategy;
	private QuickOrderDAO quickOrderDAO;
	private ProductService productService;
	private ProductModel productModel;

	private final static String CODE_QUERY = "ba";
	private final static String CODE = "bar";
	private final static int NUMBER_RESULTS = 4;

	@Before
	public void setUp()
	{
		defaultQuickOrderSearchStrategy = new DefaultQuickOrderSearchStrategy();
		quickOrderDAO = mock(QuickOrderDAO.class);
		productService = mock(ProductService.class);
		defaultQuickOrderSearchStrategy.setQuickOrderDAO(quickOrderDAO);
		defaultQuickOrderSearchStrategy.setProductService(productService);

		productModel = new ProductModel();
		productModel.setCode(CODE);
	}

	@Test
	public void testFindProductsForQuery()
	{
		when(quickOrderDAO.findProductsByCodeStart(CODE_QUERY, NUMBER_RESULTS)).thenReturn(Collections.singletonList(productModel));
		final List<ProductModel> results = defaultQuickOrderSearchStrategy.findProductsForQuery(CODE_QUERY, NUMBER_RESULTS);
		assertEquals("We should find one product", 1, results.size());
		assertEquals("Product should equal the mock returned", productModel, results.get(0));
	}

	@Test
	public void testFindProductForQuery()
	{
		when(productService.getProductForCode(CODE_QUERY)).thenReturn(productModel);
		final ProductModel result = defaultQuickOrderSearchStrategy.findProductForQuery(CODE_QUERY);
		assertNotNull("Product should not be null", productModel);
		assertEquals("Product should equal the mock returned", productModel, result);
	}
}
