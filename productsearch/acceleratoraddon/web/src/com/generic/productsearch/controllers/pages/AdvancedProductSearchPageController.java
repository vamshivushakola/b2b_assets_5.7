package com.generic.productsearch.controllers.pages;

//import com.capgemini.b2bassets.storefront.controllers.pages.AbstractSearchPageController;
//import com.capgemini.b2bassets.storefront.controllers.pages.AbstractSearchPageController.ShowMode;

//newly added

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController.ShowMode;

//end



import com.generic.productsearch.service.AdvancedProductSearchFacade;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/*
 * Unused imports 
 * import de.hybris.platform.acceleratorservices.storefront.util.PageTitleResolver;
 * import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
 * import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
 */

/**
 * Controller for Product Advanced Search
 * 
 * @author Capgemini
 */
@Controller
@RequestMapping("/advancedProductSearch")
public class AdvancedProductSearchPageController extends AbstractSearchPageController
{

	private static final String SEARCH_CMS_PAGE_ID = "advancedProductSearch";
	//MAX_PAGE_LIMIT should be configured
	public static final int MAX_PAGE_LIMIT = 100;
	private static final String INFINITE_SCROLL = "infiniteScroll";
	private String searchURL;
	private String sortParams;
	
	
	
	//newly added
	
	protected static final String PAGINATION_TYPE = "pagination.type";
	protected static final String PAGINATION_NUMBER_OF_RESULTS_COUNT = "pagination.number.results.count";
	protected static final String PAGINATION = "pagination";
	protected static final String IS_SHOW_ALLOWED = "isShowAllAllowed";
	
	//end
	

	@Resource
	private AdvancedProductSearchFacade advancedProductSearchFacade;

	/**
	 * For dvance product search page jsp
	 * 
	 * @param page
	 *           the page
	 * @param sort
	 *           the sort
	 * @param fieldsValues
	 *           the field values
	 * @param model
	 *           the model
	 * @return the advance product search page jsp
	 * @throws CMSItemNotFoundException
	 *            the CMSItemNotFoundException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "sort", defaultValue = "code") final String sort,
			@ModelAttribute(value = "fields") Fields fieldsValues, final Model model) throws CMSItemNotFoundException
	{
		if (fieldsValues.getFields() == null)
		{
			fieldsValues.setFields(populateEmptyFields(advancedProductSearchFacade.getFields()));
		}

		final PageableData pageableData = createPageableData(page,
				getSiteConfigService().getInt("product.advanced.search.pageSize", 0), null, ShowMode.Page);
		HashMap<String, Set<String>> searchData = getSearchData(fieldsValues.getFields());
		model.addAttribute("fieldsValues", fieldsValues.getFields());
		model.addAttribute("fields", advancedProductSearchFacade.getFields());
		model.addAttribute("searchNext", getSearchUrl());
		model.addAttribute("sortParams", getSortParams());
		final String paginationType = getSiteConfigService().getString(PAGINATION_TYPE, "pagination");
		model.addAttribute("paginationType", paginationType);
		ProductSearchPageData<SearchStateData, ProductData> products = advancedProductSearchFacade.textSearch(searchData,
				pageableData, sort);
		super.populateModel(model, products, ShowMode.Page);

		if (StringUtils.equalsIgnoreCase(getSiteConfigService().getString(PAGINATION_TYPE, PAGINATION), INFINITE_SCROLL))
		{
			model.addAttribute(IS_SHOW_ALLOWED, false);
		}
		storeCmsPageInModel(model, getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID));
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(""));
		return getViewForPage(model);
	}

	/**
	 * Search products by field values
	 * 
	 * @param fieldsValues
	 *           the fieldsValues
	 * @param model
	 *           the model
	 * @param page
	 *           the page
	 * @param request
	 *           the request
	 * @return the search result
	 * @throws CMSItemNotFoundException
	 *            the CMSItemNotFoundException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String search(@ModelAttribute(value = "fields") Fields fieldsValues, final Model model,
			@RequestParam(value = "page", defaultValue = "0") final int page, final HttpServletRequest request)
			throws CMSItemNotFoundException
	{
		if (fieldsValues.getFields() == null)
		{
			fieldsValues.setFields(populateEmptyFields(advancedProductSearchFacade.getFields()));
		}

		final PageableData pageableData = createPageableData(page, getSearchPageSize(), null, ShowMode.Page);
		HashMap<String, Set<String>> searchData = getSearchData(fieldsValues.getFields());
		if (!searchData.isEmpty())
		{
			ProductSearchPageData<SearchStateData, ProductData> products = advancedProductSearchFacade.textSearch(searchData,
					pageableData, null);
			model.addAttribute("fieldsValues", fieldsValues.getFields());
			model.addAttribute("fields", advancedProductSearchFacade.getFields());
			super.populateModel(model, products, ShowMode.Page);
			storeCmsPageInModel(model, getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID));
			if (StringUtils.equalsIgnoreCase(getSiteConfigService().getString(PAGINATION_TYPE, PAGINATION), INFINITE_SCROLL))
			{
				model.addAttribute(IS_SHOW_ALLOWED, false);
			}
			return getViewForPage(model);
		}
		if (StringUtils.equalsIgnoreCase(getSiteConfigService().getString(PAGINATION_TYPE, PAGINATION), INFINITE_SCROLL))
		{
			model.addAttribute(IS_SHOW_ALLOWED, true);
		}
		model.addAttribute("fieldsValues", fieldsValues.getFields());
		model.addAttribute("fields", advancedProductSearchFacade.getFields());
		return getViewForPage(model);
	}

	/**
	 * 
	 * Fields class for set and get fields
	 * 
	 */
	private static class Fields
	{
		private HashMap<String, String> fields;

		/**
		 * Get fields
		 * 
		 * @return the fields
		 */
		public HashMap<String, String> getFields()
		{
			return fields;
		}

		/**
		 * Set fields
		 * 
		 * @param fields
		 *           the fields
		 */
		public void setFields(HashMap<String, String> fields)
		{
			this.fields = fields;
		}
	}

	/**
	 * Get Search data
	 * 
	 * @param searchFields
	 *           the search fields
	 * @return the search data
	 */
	protected HashMap<String, Set<String>> getSearchData(HashMap<String, String> searchFields)
	{
		searchURL = "advancedProductSearch?";
		sortParams = "";
		HashMap<String, Set<String>> searchData = new HashMap<String, Set<String>>();
		for (String key : searchFields.keySet())
		{

			if (key.equalsIgnoreCase("summary"))
			{

				String value = searchFields.get(key);
				searchURL = searchURL.concat("&fields%5B" + key + "%5D=" + value);
				sortParams = sortParams.concat("&fields[" + key + "]=" + value);
				if (StringUtils.isNotBlank(value))
				{
					Set<String> searchValues = new HashSet();
					searchValues.add("\"" + value + "\"");
					searchData.put(key, searchValues);
				}
			}
			else if (key.equalsIgnoreCase("code"))
			{
				String value = searchFields.get(key);
				searchURL = searchURL.concat("&fields%5B" + key + "%5D=" + value);
				sortParams = sortParams.concat("&fields[" + key + "]=" + value);
				if (StringUtils.isNotBlank(value))
				{
					Set<String> searchValues = new HashSet();
					String escaped = ClientUtils.escapeQueryChars(value);
					searchValues.add(escaped);
					searchData.put(key, searchValues);
				}
			}
			else if (key.equalsIgnoreCase("name"))
			{
				String value = searchFields.get(key);
				searchURL = searchURL.concat("&fields%5B" + key + "%5D=" + value);
				sortParams = sortParams.concat("&fields[" + key + "]=" + value);
				if (StringUtils.isNotBlank(value))
				{
					Set<String> searchValues = new HashSet();
					searchValues.add("\"" + value + "\"");

					searchData.put(key, searchValues);
				}
			}
			else if (key.equalsIgnoreCase("description"))
			{
				String value = searchFields.get(key);
				searchURL = searchURL.concat("&fields%5B" + key + "%5D=" + value);
				sortParams = sortParams.concat("&fields[" + key + "]=" + value);
				if (StringUtils.isNotBlank(value))
				{
					Set<String> searchValues = new HashSet();
					searchValues.add("\"" + value + "\"");
					searchData.put(key, searchValues);
				}
			}

		}
		return searchData;
	}

	/**
	 * populateEmptyFields
	 * 
	 * @param facadeFields
	 *           collection of fields
	 * @return the empty fields
	 */
	private HashMap<String, String> populateEmptyFields(Collection<String> facadeFields)
	{
		Iterator<String> keys = facadeFields.iterator();
		HashMap<String, String> fields = new HashMap<String, String>();
		while (keys.hasNext())
		{
			String key = keys.next();
			fields.put(key, "");
		}
		return fields;
	}

	/**
	 * storeCmsPageInModel
	 * 
	 * @param model
	 *           the model
	 * @param cmsPage
	 *           the cmsPage
	 */
	protected void storeCmsPageInModel(final Model model, final AbstractPageModel cmsPage)
	{
		if (model != null && cmsPage != null)
		{
			model.addAttribute(CMS_PAGE_MODEL, cmsPage);
			if (cmsPage instanceof ContentPageModel)
			{
				storeContentPageTitleInModel(model, getPageTitleResolver().resolveContentPageTitle(cmsPage.getTitle()));
			}
		}
	}

	/**
	 * Get jsp page
	 * 
	 * @param model
	 *           the model
	 * @return the jsp page
	 */
	protected String getViewForPage(final Model model)
	{
		if (model.containsAttribute(CMS_PAGE_MODEL))
		{
			final AbstractPageModel page = (AbstractPageModel) model.asMap().get(CMS_PAGE_MODEL);
			if (page != null)
			{
				return getViewForPage(page);
			}
		}
		return null;
	}

	/**
	 * Get search url
	 * 
	 * @return the search url
	 */
	protected String getSearchUrl()
	{
		return searchURL;
	}

	/**
	 * Get sort parameters
	 * 
	 * @return the sort parameters
	 */
	protected String getSortParams()
	{
		return sortParams;
	}
}
