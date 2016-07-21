package com.capgemini.quickorder.facades.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.quickorder.data.QuickOrderProductData;
import com.capgemini.quickorder.services.QuickOrderService;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.b2bacceleratorfacades.product.data.ProductQuantityData;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;


/**
 * <p>
 * Unit test for DefaultQuickOrderFacade
 * </p>
 *
 * @author Capgemini
 */
@UnitTest
public class DefaultQuickOrderFacadeTest
{
	private DefaultQuickOrderFacade defaultQuickOrderFacade;
	private QuickOrderService quickOrderService;
	private CartFacade cartFacade;
	private Converter<ProductModel, QuickOrderProductData> quickOrderProductConverter;
	private Converter<ProductModel, ProductQuantityData> productQuantityConverter;

	private ProductModel product;
	private final static String CODE_QUERY = "ba";
	private final static String CODE = "bar";
	private final static int NUMBER_RESULTS = 4;

	@Before
	public void setUp()
	{
		defaultQuickOrderFacade = new DefaultQuickOrderFacade();
		quickOrderService = mock(QuickOrderService.class);
		cartFacade = mock(CartFacade.class);
		quickOrderProductConverter = mock(Converter.class);
		productQuantityConverter = mock(Converter.class);
		defaultQuickOrderFacade.setQuickOrderService(quickOrderService);
		defaultQuickOrderFacade.setCartFacade(cartFacade);
		defaultQuickOrderFacade.setQuickOrderProductConverter(quickOrderProductConverter);
		defaultQuickOrderFacade.setProductQuantityConverter(productQuantityConverter);

		product = new ProductModel();
		product.setCode(CODE);
	}

	@Test
	public void testFindProductsForQueryStart()
	{
		final QuickOrderProductData productData = new QuickOrderProductData();
		productData.setCode(CODE);
		when(quickOrderProductConverter.convert(product)).thenReturn(productData);
		when(quickOrderService.findProductsForQuery(CODE_QUERY, NUMBER_RESULTS)).thenReturn(Collections.singletonList(product));
		final List<QuickOrderProductData> results = defaultQuickOrderFacade.findProductsForQueryStart(CODE_QUERY, NUMBER_RESULTS);
		assertEquals("We should find one product", 1, results.size());
		assertEquals("Product should equal the mock returned", productData, results.get(0));
	}

	@Test
	public void testParseQuickOrderForm()
	{
		final Integer quantity = Integer.valueOf(8);
		final ProductQuantityData productQuantityData = new ProductQuantityData();
		productQuantityData.setSku(CODE);
		when(quickOrderService.findProductForQuery(CODE)).thenReturn(product);
		when(productQuantityConverter.convert(product)).thenReturn(productQuantityData);
		final List<ProductQuantityData> results = defaultQuickOrderFacade.parseQuickOrderForm(new Integer[]
		{ quantity }, new String[]
		{ CODE });
		assertEquals("We should find one product", 1, results.size());
		assertEquals("Product should equal the mock returned", productQuantityData, results.get(0));
		assertEquals("Product quantity is wrong", quantity, results.get(0).getQuantity());
	}

	@Test
	public void testParseQuickOrderForm2()
	{
		when(quickOrderService.findProductForQuery(CODE)).thenReturn(product);
		final List<ProductModel> results = defaultQuickOrderFacade.parseQuickOrderForm(new String[]
		{ CODE });
		assertEquals("We should find one product", 1, results.size());
		assertEquals("Product should equal the mock returned", product, results.get(0));
	}
}
