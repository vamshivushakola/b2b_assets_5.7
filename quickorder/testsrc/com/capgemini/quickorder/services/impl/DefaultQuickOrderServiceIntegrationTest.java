package com.capgemini.quickorder.services.impl;

import com.capgemini.quickorder.services.QuickOrderService;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by tbrison on 13/06/2014.
 */
@IntegrationTest
public class DefaultQuickOrderServiceIntegrationTest extends ServicelayerTransactionalTest {
	private static final Logger LOG = Logger.getLogger(DefaultQuickOrderServiceIntegrationTest.class);
	private static final String TEST_BASESITE_UID = "testSite";

    @Resource
    private QuickOrderService quickOrderService;

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
    public void setUp() throws ImpExException {
		LOG.info("Creating data for Quickorder...");
		userService.setCurrentUser(userService.getAdminUser());
		final long startTime = System.currentTimeMillis();
		importCsv("/quickorder/test/testQuickorder.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);
		LOG.info("Finished data for Quickorder in " + (System.currentTimeMillis() - startTime) + "ms");
    }

	private ProductModel createProduct() {
		ProductModel productModel = new ProductModel();
		productModel.setCode(CODE);
		productModel.setCatalogVersion(catalogVersionService.getCatalogVersion(CATALOG_ID, CATALOG_VERSION));
		modelService.save(productModel);
		return productModel;
	}

    @Test
    public void testQuickOrderService() {
        List<ProductModel> productModels = quickOrderService.findProductsForQuery(CODE_QUERY, NUMBER_RESULTS);
        final int size = productModels.size();

		ProductModel expectedProduct = createProduct();

        productModels = quickOrderService.findProductsForQuery(CODE_QUERY, NUMBER_RESULTS);
        assertEquals(size + 1, productModels.size());
        assertEquals("Unexpected product found", expectedProduct, productModels.get(productModels.size() - 1));
    }
}
