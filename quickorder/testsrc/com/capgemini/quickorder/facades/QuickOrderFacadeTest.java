package com.capgemini.quickorder.facades;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.quickorder.data.QuickOrderProductData;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.b2bacceleratorfacades.product.data.ProductQuantityData;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;


/**
 * <p>
 * Integration test for QuickOrderFacade
 * </p>
 *
 * @author Capgemini
 */
@IntegrationTest
public class QuickOrderFacadeTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(QuickOrderFacadeTest.class);
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private QuickOrderFacade quickOrderFacade;

	@Resource
	private Converter<ProductModel, QuickOrderProductData> quickOrderProductConverter;

	@Resource
	private Converter<ProductModel, ProductQuantityData> productQuantityConverter;

	@Resource
	private ModelService modelService;

	@Resource
	private UserService userService;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private CatalogVersionService catalogVersionService;

	private static final String CATALOG_ID = "testCatalog";
	private static final String CATALOG_VERSION = "Online";
	private final static String CODE_QUERY = "ba";
	private final static String CODE = "bar";
	private final static int NUMBER_RESULTS = 4;

	@Before
	public void setUp() throws ImpExException
	{
		LOG.info("Creating data for Quickorder...");
		userService.setCurrentUser(userService.getAdminUser());
		final long startTime = System.currentTimeMillis();
		importCsv("/quickorder/test/testQuickorder.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);
		LOG.info("Finished data for Quickorder in " + (System.currentTimeMillis() - startTime) + "ms");
	}

	private ProductModel createProduct()
	{
		final ProductModel productModel = new ProductModel();
		productModel.setCode(CODE);
		productModel.setCatalogVersion(catalogVersionService.getCatalogVersion(CATALOG_ID, CATALOG_VERSION));
		modelService.save(productModel);
		return productModel;
	}

	private QuickOrderProductData createProductData()
	{
		return quickOrderProductConverter.convert(createProduct());
	}

	private ProductQuantityData createProductQuantityData()
	{
		return productQuantityConverter.convert(createProduct());
	}

	@Test
	public void testFindProductsForQueryStart()
	{
		List<QuickOrderProductData> products = quickOrderFacade.findProductsForQueryStart(CODE_QUERY, NUMBER_RESULTS);
		final int size = products.size();

		final QuickOrderProductData expectedProduct = createProductData();

		products = quickOrderFacade.findProductsForQueryStart(CODE_QUERY, NUMBER_RESULTS);
		assertEquals(size + 1, products.size());
		assertEquals("Unexpected product code found", expectedProduct.getCode(), products.get(products.size() - 1).getCode());
	}

	@Test
	public void testParseQuickOrderForm()
	{
		final Integer quantity = Integer.valueOf(8);
		final ProductQuantityData expectedProductQuantity = createProductQuantityData();
		expectedProductQuantity.setQuantity(quantity);

		final List<ProductQuantityData> productQuantities = quickOrderFacade.parseQuickOrderForm(new Integer[]
		{ quantity }, new String[]
		{ CODE });
		assertEquals(1, productQuantities.size());
		assertEquals("Unexpected product code found", expectedProductQuantity.getSku(),
				productQuantities.get(productQuantities.size() - 1).getSku());
		assertEquals("Unexpected product quantity found", expectedProductQuantity.getQuantity(),
				productQuantities.get(productQuantities.size() - 1).getQuantity());
	}

	@Test
	public void testParseQuickOrderForm2()
	{
		final ProductModel expectedProduct = createProduct();

		final List<ProductModel> products = quickOrderFacade.parseQuickOrderForm(new String[]
		{ CODE });
		assertEquals(1, products.size());
		assertEquals("Unexpected product found", expectedProduct, products.get(products.size() - 1));
	}
}
