package com.capgemini.quickorder.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.quickorder.cms.model.QuickOrderComponentModel;
import com.capgemini.quickorder.constants.QuickorderWebConstants;
import com.capgemini.quickorder.facades.QuickOrderFacade;


/**
 * Controller for CMS QuickOrderComponent
 * 
 * @author Thomas Brison <thomas.brison@capgemini.com>
 */
@Controller("QuickOrderComponentController")
@RequestMapping("/view/QuickOrderComponentController")
public class QuickOrderComponentController extends AbstractCMSAddOnComponentController<QuickOrderComponentModel>
{
	@Autowired
	private QuickOrderFacade quickOrderFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final QuickOrderComponentModel component)
	{
		model.addAttribute("nbRows", component.getNumberRows());
		model.addAttribute("nbChars", component.getMinimumCharactersSearch());
		model.addAttribute("nbResults", component.getMaximumResultsSearch());
		model.addAttribute("delaySearch", component.getDelaySearch());
		model.addAttribute("className", component.getStyleClass());
		model.addAttribute("formUrl", QuickorderWebConstants.URI.ADD_TO_CART);
		model.addAttribute("autocompleteUrl", QuickorderWebConstants.URI.SEARCH);
		model.addAttribute("autocompleteSolrUrl", QuickorderWebConstants.URI.SOLRSEARCH);
		model.addAttribute("useSolr", true);
		model.addAttribute("isCartEmpty", quickOrderFacade.isCartEmpty());
	}

}
