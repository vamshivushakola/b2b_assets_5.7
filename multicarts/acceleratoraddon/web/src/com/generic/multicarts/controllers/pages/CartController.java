package com.generic.multicarts.controllers.pages;

import com.generic.multicarts.annotations.CanEditCart;
import com.generic.multicarts.annotations.CartCode;
import com.generic.multicarts.annotations.OptionIsEnabled;
import com.generic.multicarts.controllers.MulticartsControllerConstants;
import com.generic.multicarts.data.MultiCartsData;
import com.generic.multicarts.facades.MultiCartsFacade;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.b2bacceleratorfacades.company.B2BCommerceUnitFacade;
//import de.hybris.platform.b2bacceleratorfacades.company.B2BCommerceUserFacade;
import de.hybris.platform.b2bacceleratorfacades.company.data.B2BUnitNodeData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Default multicarts controller
 * 
 * @author Capgemini
 */
@Controller
@RequestMapping(value = MulticartsControllerConstants.Urls.RootUrl)
public class CartController extends AbstractAddOnPageController
{

	/**
	 * Multicarts Facade : Extends standard CartFacede
	 * 
	 * @see de.hybris.platform.commercefacades.order.impl.DefaultCartFacade
	 */
	@Resource
	private MultiCartsFacade multiCartsFacade;

	@Resource
	private B2BCommerceUnitFacade b2bCommerceUnitFacade;

	@Resource
	private UserFacade currentUser;

	@Resource
	private ConfigurationService configurationService;



	/**
	 * Model attribute for the jsp page
	 * 
	 * @return User carts
	 */
	@ModelAttribute("carts")
	public Collection<MultiCartsData> getCarts()
	{
		if (!currentUser.isAnonymousUser())
		{
			return multiCartsFacade.getCarts();
		}
		else
		{
			return new ArrayList<>();
		}
	}

	/**
	 * Model attribure for the jsp page
	 * 
	 * @return Current user cart
	 */
	@ModelAttribute("currentCart")
	public MultiCartsData getCurrentCart()
	{
		if (currentUser.isAnonymousUser())
		{
			return new MultiCartsData();
		}
		return multiCartsFacade.getCurrentCart();
	}

	/**
	 * Model attribure for the jsp page
	 * 
	 * @return User B2Bunits
	 */
	@ModelAttribute("B2BUnits")
	public B2BUnitNodeData getB2BUnits()
	{
		if (currentUser.isAnonymousUser())
		{
			return new B2BUnitNodeData();
		}
		return b2bCommerceUnitFacade.getParentUnitNode();
	}

	/**
	 * Default page Display default multicarts page Cart list
	 * 
	 * @param model
	 *           the jsp model
	 * @return the jsp page
	 * @throws CMSItemNotFoundException
	 *            CMSItemNotFoundException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String homePage(final Model model) throws CMSItemNotFoundException
	{
		prepareDataForModel(model);
		final Configuration config = configurationService.getConfiguration();
		model.addAttribute("deleteAction", config.getBoolean(MulticartsControllerConstants.Options.delete));
		model.addAttribute("shareAction", config.getBoolean(MulticartsControllerConstants.Options.share));
		model.addAttribute("historyAction", config.getBoolean(MulticartsControllerConstants.Options.history));
		model.addAttribute("notificationAction", config.getBoolean(MulticartsControllerConstants.Options.notification));
		return MulticartsControllerConstants.Views.Pages.CartsListPage;
	}

	/**
	 * Create a cart for the current user
	 * 
	 * @param model
	 *           The jsp model
	 * @param cartName
	 *           The cart name
	 * @return Redirection to the default multicarts page
	 * @throws CMSItemNotFoundException
	 *            CMSItemNotFoundException
	 */
	@RequestMapping(value = MulticartsControllerConstants.Urls.CreateCart, method = RequestMethod.POST)
	public String createCart(final Model model, @RequestParam(value = "cartName") String cartName) throws CMSItemNotFoundException
	{
		if (currentUser.isAnonymousUser())
		{
			return AbstractAddOnPageController.REDIRECT_PREFIX + "/login";
		}
		multiCartsFacade.createCart(cartName, getCmsSiteService().getCurrentSite());
		return AbstractAddOnPageController.REDIRECT_PREFIX + MulticartsControllerConstants.Urls.RootUrl;
	}

	/**
	 * Display the history page
	 * 
	 * @param model
	 *           The jsp model
	 * @param cartCode
	 *           The cart code
	 * @return Cart history
	 * @throws CMSItemNotFoundException
	 *            CMSItemNotFoundException
	 */
	@CanEditCart
	@OptionIsEnabled(name = MulticartsControllerConstants.Options.history)
	@RequestMapping(value = MulticartsControllerConstants.Urls.History, method = RequestMethod.GET)
	public String history(final Model model, @CartCode @PathVariable final String cartCode) throws CMSItemNotFoundException
	{
		final PageableData pageableData = new PageableData();
		pageableData.setPageSize(100);
		prepareDataForModel(model);
		model.addAttribute("code", cartCode);
		model.addAttribute("selectedCart", multiCartsFacade.getCart(cartCode));
		model.addAttribute("logs", multiCartsFacade.getLogs(cartCode, pageableData).getResults());
		return MulticartsControllerConstants.Views.Pages.CartsHistoryPage;
	}

	/**
	 * Ajax method Select the cart
	 * 
	 * @param cartCode
	 *           The cart code
	 * @return true if the cart is selected
	 */
	@CanEditCart
	@ResponseBody
	@RequestMapping(value = MulticartsControllerConstants.Urls.SelectCart, method = RequestMethod.GET)
	public boolean selectCart(@CartCode @PathVariable(value = "cartCode") final String cartCode)
	{
		multiCartsFacade.selectCart(cartCode);
		return true;
	}

	/**
	 * delete the selected cart
	 * 
	 * @param cartCode
	 *           The cart code
	 * @return True if the cart is deleted
	 */
	@CanEditCart
	@OptionIsEnabled(name = MulticartsControllerConstants.Options.delete)
	@ResponseBody
	@RequestMapping(value = MulticartsControllerConstants.Urls.DeleteCart, method = RequestMethod.POST)
	public boolean deleteCart(@CartCode @PathVariable(value = "cartCode") final String cartCode)
	{
		multiCartsFacade.removeCart(cartCode);
		return true;
	}

	/**
	 * Share the cart
	 * 
	 * @param cartCode
	 *           The cart code
	 * @param b2bunit
	 *           The B2Bunit
	 * @return true if the cart is shared
	 */
	@CanEditCart
	@OptionIsEnabled(name = MulticartsControllerConstants.Options.share)
	@ResponseBody
	@RequestMapping(value = MulticartsControllerConstants.Urls.Share, method = RequestMethod.POST)
	public boolean share(@CartCode @PathVariable(value = "cartCode") final String cartCode,
			@RequestParam(value = "b2bunit") final String b2bunit)
	{
		multiCartsFacade.share(cartCode, b2bunit);
		return true;
	}

	/**
	 * Unshare the cart
	 * 
	 * @param cartCode
	 *           The cart code
	 * @param b2bunit
	 *           The B2Bunit
	 * @return true
	 */
	@CanEditCart
	@OptionIsEnabled(name = MulticartsControllerConstants.Options.share)
	@ResponseBody
	@RequestMapping(value = MulticartsControllerConstants.Urls.UnShare, method = RequestMethod.POST)
	public boolean unshare(@CartCode @PathVariable(value = "cartCode") final String cartCode,
			@RequestParam(value = "b2bunit") final String b2bunit)
	{
		multiCartsFacade.unshare(cartCode, b2bunit);
		return true;
	}

	/**
	 * Set cart name
	 * 
	 * @param cartCode
	 *           The cart code
	 * @param name
	 *           The name
	 * @return true
	 */
	@CanEditCart
	@ResponseBody
	@RequestMapping(value = MulticartsControllerConstants.Urls.SetCartName, method = RequestMethod.POST)
	public boolean setCartName(@CartCode @PathVariable(value = "cartCode") final String cartCode,
			@RequestParam(value = "name") final String name)
	{
		multiCartsFacade.setName(cartCode, name);
		return true;
	}

	/**
	 * Follow cart
	 * 
	 * @param cartCode
	 *           The cart code
	 * @return the jsp page
	 */
	@CanEditCart
	@OptionIsEnabled(name = MulticartsControllerConstants.Options.notification)
	@RequestMapping(value = MulticartsControllerConstants.Urls.follow, method = RequestMethod.GET)
	public String followCart(@CartCode @PathVariable(value = "cartCode") final String cartCode)
	{
		multiCartsFacade.follow(cartCode);
		return AbstractAddOnPageController.REDIRECT_PREFIX + MulticartsControllerConstants.Urls.RootUrl;
	}

	/**
	 * Unfollow cart
	 * 
	 * @param cartCode
	 *           The cart code
	 * @return the jsp page
	 */
	@CanEditCart
	@OptionIsEnabled(name = MulticartsControllerConstants.Options.notification)
	@RequestMapping(value = MulticartsControllerConstants.Urls.unfollow, method = RequestMethod.GET)
	public String unfollowCart(@CartCode @PathVariable(value = "cartCode") final String cartCode)
	{
		multiCartsFacade.unfollow(cartCode);
		return AbstractAddOnPageController.REDIRECT_PREFIX + MulticartsControllerConstants.Urls.RootUrl;
	}

	/**
	 * Update Page Title
	 * 
	 * @param model
	 *           the jsp model
	 * @param cmsPage
	 *           the cmspage attribute
	 */
	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(""));
	}

	/**
	 * Prepare data for model
	 * 
	 * @param model
	 *           the jsp model
	 * @throws CMSItemNotFoundException
	 *            CMSItemNotFoundException
	 */
	protected void prepareDataForModel(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(null));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
		updatePageTitle(model, getContentPageForLabelOrId(null));
	}
}
