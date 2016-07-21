package com.capgemini.quickorder.daos.impl;

import com.capgemini.quickorder.daos.QuickOrderDAO;
import de.hybris.platform.catalog.CatalogVersionService;
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
import static org.junit.Assert.assertTrue;


/**
 * @author Thomas Brison <thomas.brison@capgemini.com>
 */
public class DefaultQuickOrderDAOIntegrationTest extends ServicelayerTransactionalTest {
	private static final Logger LOG = Logger.getLogger(DefaultQuickOrderDAOIntegrationTest.class);
	private static final String TEST_BASESITE_UID = "testSite";

    @Resource
    private QuickOrderDAO quickOrderDAO;

    @Resource
    private ModelService modelService;

	@Resource
	private UserService userService;

	@Resource
	private BaseSiteService baseSiteService;

    @Resource
    private CatalogVersionService catalogVersionService;

    /**
     * Catalog ID
     */
    private static final String CATALOG_ID = "testCatalog";

    /**
     * Catalog version name
     */
    private static final String CATALOG_VERSION = "Online";

    /**
     * Code to query
     */
    private static final String CODE_QUERY = "FERT";

    /**
     * Code of the product
     */
    private static final String CODE = "Fertilisant-42";

    /**
     * Number of results to query for
     */
    private static final int MAXIMUM_NUMBER_RESULTS = 2;

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
    public void quickOrderDAOTest() {
        List<ProductModel> productsByCode = quickOrderDAO.findProductsByCodeStart(CODE_QUERY, MAXIMUM_NUMBER_RESULTS);
        assertTrue("No products should be returned", productsByCode.isEmpty());

        createProduct();

        productsByCode = quickOrderDAO.findProductsByCodeStart(CODE, MAXIMUM_NUMBER_RESULTS);
        assertEquals("Find the one we just saved", 1, productsByCode.size());
        assertEquals("Check the references", CODE, productsByCode.get(0).getCode());
    }

}
