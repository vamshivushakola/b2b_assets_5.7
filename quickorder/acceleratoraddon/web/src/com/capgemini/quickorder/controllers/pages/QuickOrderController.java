package com.capgemini.quickorder.controllers.pages;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.b2bacceleratorfacades.product.data.ProductQuantityData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationStatus;
import de.hybris.platform.util.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.quickorder.controllers.QuickorderControllerConstants;
import com.capgemini.quickorder.data.QuickOrderProductData;
import com.capgemini.quickorder.facades.QuickOrderFacade;
import com.capgemini.quickorder.util.GlobalMessages;


/**
 * Controller for Quick Order
 * 
 * @author svc-in-ecommusr
 * 
 */
@Controller
@RequestMapping(value = "/quickorder")
public class QuickOrderController extends AbstractAddOnPageController
{
	protected static final Logger LOG = Logger.getLogger(QuickOrderController.class);
	private static final Integer MINIMUM_SINGLE_SKU_ADD_CART = Integer.valueOf(0);

	@Autowired
	private QuickOrderFacade quickOrderFacade;

	@Resource
	private CartFacade cartFacade;

	/**
	 * Method for add products to cart
	 * 
	 * @param productQuantity
	 *           contains code and quantity of product
	 * @return The Cart Modification data
	 * @throws CommerceCartModificationException
	 *            occurred while adding to cart
	 */
	protected CartModificationData addToCart(final ProductQuantityData productQuantity) throws CommerceCartModificationException
	{
		return cartFacade.addToCart(productQuantity.getSku(), productQuantity.getQuantity());
	}

	/**
	 * Method for extracting product code and quantity from ProductQuantityData
	 * 
	 * @param productQuantities
	 *           List of product quantities
	 * @param model
	 *           Redirect Attribute
	 * @return The cartModificationDataList
	 * @throws CommerceCartModificationException
	 *            occurred while adding to cart
	 */
	protected List<CartModificationData> addToCart(final List<ProductQuantityData> productQuantities,
			final RedirectAttributes model) throws CommerceCartModificationException
	{
		final List<CartModificationData> cartModificationDataList = new ArrayList<CartModificationData>();

		for (final ProductQuantityData productQuantity : productQuantities)
		{
			if (productQuantity.getQuantity() != null)
			{
				// Ignore all skus with
				if (productQuantity.getQuantity() > MINIMUM_SINGLE_SKU_ADD_CART)
				{
					try
					{
						final CartModificationData product = addToCart(productQuantity);

						if (product != null)
						{
							cartModificationDataList.add(product);
						}
					}
					catch (final IllegalStateException e)
					{
						GlobalMessages.addFlashMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, "quickorder.error.empty.price",
								new Object[]
								{ productQuantity.getSku() });
					}
				}
			}
		}
		return cartModificationDataList;
	}

	/**
	 * Get SKU and quantity from user and verify
	 * 
	 * @param quantities
	 *           array of quantities
	 * @param values
	 *           array of product code
	 * @param model
	 *           Redirect Attribute
	 * @return The cart page
	 */
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addProductsToCart(@RequestParam("quantity[]") final Integer[] quantities,
			@RequestParam("value[]") final String[] values, final RedirectAttributes model)
	{
		try
		{
			final List<ProductQuantityData> parsedOrders = quickOrderFacade.parseQuickOrderForm(quantities, values);
			checkError(parsedOrders, model);
			final List<CartModificationData> cartModifications = addToCart(parsedOrders, model);
			for (final CartModificationData cartModification : cartModifications)
			{
				if (CommerceCartModificationStatus.NO_STOCK.equals(cartModification.getStatusCode()))
				{
					GlobalMessages.addFlashMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, "quickorder.cart.message.noStock",
							new Object[]
							{ cartModification.getEntry().getProduct().getName() });
				}
				else if (cartModification.getQuantity() != cartModification.getQuantityAdded())
				{
					// item has been modified to match available stock levels
					GlobalMessages.addFlashMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER,
							"quickorder.cart.message.quantity.adjusted", new Object[]
							{ cartModification.getEntry().getProduct().getName() });
				}
				// Here you can handle more messages
			}
		}
		catch (final CommerceCartModificationException exception)
		{
			GlobalMessages.addFlashMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, "quickorder.error.occurred", null);
		}
		return REDIRECT_PREFIX + "/cart";
	}

	/**
	 * Validate the product code, quantity and display appropriate message
	 * 
	 * @param parsedOrders
	 *           ProductQuantityData list
	 * @param model
	 *           Redirect Attribute
	 */
	private void checkError(final List<ProductQuantityData> parsedOrders, final RedirectAttributes model)
	{
		final Iterator<ProductQuantityData> it = parsedOrders.iterator();
		while (it.hasNext())
		{
			final ProductQuantityData data = it.next();
			if (data.getQuantity() != null && !data.getSku().equals(""))
			{
				if (data.getQuantity().equals(new Integer("-1")))
				{
					it.remove();
					GlobalMessages.addFlashMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, "quickorder.error.product.notfount",
							new Object[]
							{ data.getSku() });
				}
				//Check If Quantity is zero
				if (data.getQuantity().intValue() == 0)
				{
					it.remove();
					GlobalMessages.addFlashMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, "quickorder.error.quantity",
							new Object[]
							{ data.getSku() });
				}
			}
			else if (data.getSku().equals("") || data.getQuantity() == null)
			{
				it.remove();
				String msg;
				if (data.getSku().equals(""))
				{
					msg = "null";
				}
				else
				{
					msg = data.getSku();
				}
				GlobalMessages.addFlashMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, "quickorder.error", new Object[]
				{ msg, data.getQuantity() });
			}
		}
	}

	/**
	 * Search Product and display appropriate message
	 * 
	 * @param query
	 *           The query for search
	 * @param nbResults
	 *           maximum number of quaery results
	 * @param request
	 *           the request
	 * @param model
	 *           Redirect Attribute
	 * @return The QuickOrderProductData list
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<QuickOrderProductData> search(@RequestParam("q") final String query,
			@RequestParam(value = "nb", defaultValue = "-1") final Integer nbResults, final HttpServletRequest request,
			final RedirectAttributes model)
	{

		LOG.info("Inside a search method of quick order controller");

		if (query == null || query.isEmpty())
		{
			return Collections.emptyList();
		}
		final List<QuickOrderProductData> products = quickOrderFacade.findProductsForQueryStart(query, nbResults.intValue());
		if (products != null && !products.isEmpty())
		{
			return products;
		}
		else
		{
			final QuickOrderProductData quickOrderProductData = new QuickOrderProductData();
			final String errorMessage = getMessageSource().getMessage("quickorder.error.product.notfount", new Object[]
			{ query }, getI18nService().getCurrentLocale());
			quickOrderProductData.setErrorMessage(errorMessage);
			products.add(quickOrderProductData);
			return products;
		}

	}

	/**
	 * Method is called while import through csv file
	 * 
	 * @param csvFile
	 *           CSV file
	 * @param model
	 *           the model
	 * @param redirectModel
	 *           Redirect Attribute
	 * @return The addProductsToCart method
	 * @throws IOException
	 *            the IOException
	 * @throws CMSItemNotFoundException
	 *            the CMSItemNotFoundException
	 */
	@RequestMapping(value = "/uploadCsv", method = RequestMethod.POST)
	public String upload2(@RequestParam("csvFile") final MultipartFile csvFile, final Model model,
			final RedirectAttributes redirectModel) throws IOException, CMSItemNotFoundException
	{
		final InputStream inputStream = csvFile.getInputStream();
		final Reader reader = new InputStreamReader(inputStream);
		final CSVReader csv = new CSVReader(reader);
		if (csvFile.isEmpty())
		{
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
					"text.fileUpload.PleaseSelectFiletoImport");
		}
		else
		{
			final ArrayList<Integer> quantities = new ArrayList<>();
			final ArrayList<String> values = new ArrayList<>();
			while (csv.readNextLine())
			{
				final Map<Integer, String> line = csv.getLine();
				try
				{
					//Check for SKU is empty
					if (line.get(0).isEmpty())
					{
						GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "quickorder.error",
								new Object[]
								{ line.get(0), line.get(1) });
					}
					else
					{
						quantities.add(Integer.parseInt(line.get(1)));
						values.add(line.get(0));
					}
				}
				catch (final NumberFormatException e)
				{
					GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "quickorder.error",
							new Object[]
							{ line.get(0), line.get(1) });

				}
			}
			return addProductsToCart(quantities.toArray(new Integer[0]), values.toArray(new String[0]), redirectModel);
		}
		return REDIRECT_PREFIX + "/";
	}

	/**
	 * Get CSV file example
	 * 
	 * @param request
	 *           the request
	 * @param response
	 *           the response
	 * @return CSV file
	 */
	@RequestMapping(value = "/getCsvExample", method = RequestMethod.GET)
	public String getCSVFile(final HttpServletRequest request, final HttpServletResponse response)
	{
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename=\"example.csv\"");
		return QuickorderControllerConstants.Views.Pages.QuickOrderCsv;
	}
}
