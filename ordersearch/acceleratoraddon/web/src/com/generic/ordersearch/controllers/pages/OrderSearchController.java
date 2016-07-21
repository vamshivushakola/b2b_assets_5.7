package com.generic.ordersearch.controllers.pages;

import com.generic.ordersearch.facade.OrderSearchFacade;

import de.hybris.platform.b2b.services.B2BUnitService;

import com.generic.ordersearch.constants.OrdersearchConstants;
import com.generic.ordersearch.controllers.OrdersearchControllerConstants;
import com.generic.ordersearch.converters.populator.OrderSearchResultsPopulator;
import com.generic.ordersearch.field.OrderSearchField;
//import com.capgemini.b2bassets.storefront.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
//import com.capgemini.b2bassets.storefront.controllers.pages.AbstractSearchPageController.ShowMode;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController.ShowMode;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.crypto.Mac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Capgemini
 */
@Controller
@RequestMapping("/orderSearch")
public class OrderSearchController extends AbstractSearchPageController
{


	protected static final String PAGINATION_TYPE = "pagination.type";
	protected static final String PAGINATION = "pagination";
	protected static final String IS_SHOW_ALLOWED = "isShowAllAllowed";

	@Resource
	private OrderSearchFacade orderSearchFacade;
	
	@Resource
	private B2BUnitService<B2BUnitModel, B2BCustomerModel> b2bUnitService;
	
	@Resource
	private UserService userService;
	
	private String searchURL;
	private String sortParams;
	
	
	private static final String INFINITE_SCROLL = "infiniteScroll";
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = "page", defaultValue = "0") final int page,@RequestParam(value = "sort", defaultValue = "code") final String sort,
			@ModelAttribute(value = "fields") Fields fieldsValues, final Model model) throws CMSItemNotFoundException
	{

		if(fieldsValues.getFields()==null){
			fieldsValues.setFields(populateEmptyFields(orderSearchFacade.getFields()));
		}
		final PageableData pageableData = createPageableData(page, getSiteConfigService().getInt("order.search.pageSize", 0), null, ShowMode.Page);
		Boolean showCarts = getSiteConfigService().getBoolean("order.search.showcarts", true);
      
      super.populateModel(model, orderSearchFacade.textSearch(prepareSearchData(fieldsValues.getFields(),showCarts), pageableData, sort), ShowMode.Page);
		if (StringUtils.equalsIgnoreCase(getSiteConfigService().getString(PAGINATION_TYPE, PAGINATION), INFINITE_SCROLL))
		{
			model.addAttribute(IS_SHOW_ALLOWED, false);
		}
		model.addAttribute("fields",  orderSearchFacade.getFields());
		model.addAttribute("fieldsValues",  fieldsValues.getFields());
		model.addAttribute("searchNext", getSearchUrl());
		model.addAttribute("sortParams", getSortParams());
		 model.addAttribute("searchPageData", orderSearchFacade.textSearch(prepareSearchData(fieldsValues.getFields(),showCarts), pageableData, sort));
      prepareDataForModel(model);
      return OrdersearchControllerConstants.Views.Pages.OrderSearchPage;
	}
	
 /*  @RequestMapping(value = OrdersearchControllerConstants.Urls.SearchOrder, method = RequestMethod.POST)
   public String search(@ModelAttribute(value = "fields") Fields fieldsValues, final Model model) throws CMSItemNotFoundException {
   	final PageableData pageableData = createPageableData(0, getSearchPageSize(), null, ShowMode.Page);
		prepareDataForModel(model);
       model.addAttribute("fields",  orderSearchFacade.getFields());
       model.addAttribute("fieldsValues",  fieldsValues.getFields());
       
       model.addAttribute("searchPageData", orderSearchFacade.textSearch(prepareSearchData(fieldsValues.getFields()), pageableData, "code"));
       model.addAttribute("searchNext", getSearchUrl());
       return OrdersearchControllerConstants.Views.Pages.OrderSearchPage;
   }
*/
	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(""));
	}

	protected void prepareDataForModel(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(null));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
		updatePageTitle(model, getContentPageForLabelOrId(null));
	}
	
	private static class Fields {
      private HashMap<String, String> fields;

      public HashMap<String, String> getFields() {
          return fields;
      }

      public void setFields(HashMap<String, String> fields) {
          this.fields = fields;
      }
  }
	
	protected Collection<OrderSearchField> prepareSearchData(HashMap<String, String> fields, Boolean showCarts){
		
		searchURL = "orderSearch?";
		sortParams = "";
		Collection<OrderSearchField> values = new HashSet<OrderSearchField>();
		Iterator<String> keys = fields.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			OrderSearchField field = new OrderSearchField(key);
			String value = fields.get(key);
			searchURL = searchURL.concat("&fields%5B"+key+"%5D="+value);
			sortParams = sortParams.concat("&fields["+key+"]="+value);
			if(value!=null && value.contains(" ")){
				value = "\""+value+"\"";
				
			}
			
			field.addValue(value);
			values.add(field);
		}
		values.add(addB2BUnit());
		if(!showCarts){
			OrderSearchField field = new OrderSearchField("order_type");
			field.addValue(OrdersearchConstants.OrderType.ORDER.toString());
			values.add(field);
		}
		return values;
	}
	
	
	private HashMap<String,String> populateEmptyFields(Collection<String> facadeFields){
		Iterator<String> keys = facadeFields.iterator();
		HashMap<String,String> fields = new HashMap<String,String>();
		while(keys.hasNext()){
			String key = keys.next();
			fields.put(key, "");
		}
		return fields;
	}
	
	private OrderSearchField addB2BUnit(){
		String unitName;
		OrderSearchField field = new OrderSearchField("unit");
		if(userService.isAnonymousUser(getUserService().getCurrentUser())){
			unitName= "default";
			field.addValue(unitName);
		}else{
			Set<B2BUnitModel> b2bUnits = b2bUnitService.getAllUnitsOfOrganization(getCurrentUser());
			Iterator<B2BUnitModel> it = b2bUnits.iterator();
			while(it.hasNext()){
				String value = it.next().getUid();
				if(value!=null && value.contains(" ")){
					value = "\""+value+"\"";
				}
				field.addValue(value);
			}
		}
		/* Commenting to get the order result set with operator AND*/
	//	field.setRawString("-version_string:[* TO *]");
		
		return field;
	}
	
	private <T extends B2BCustomerModel> T getCurrentUser()
	{
		return (T) this.getUserService().getCurrentUser();
	}
	
	private UserService getUserService(){
		return userService;
	}
	protected String getSearchUrl()
	{
		return searchURL;
	}
	
	protected String getSortParams(){
		return sortParams;
	}
}
