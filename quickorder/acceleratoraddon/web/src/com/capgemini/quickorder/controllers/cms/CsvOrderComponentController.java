/**
 * 
 */
package com.capgemini.quickorder.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.quickorder.cms.model.CsvOrderComponentModel;
import com.capgemini.quickorder.constants.QuickorderWebConstants;


/**
 * Controller for CMS CsvOrderComponent
 * 
 * @author svc-in-ecommusr
 * 
 */

@Controller("CsvOrderComponentController")
@RequestMapping("/view/CsvOrderComponentController")
public class CsvOrderComponentController extends AbstractCMSAddOnComponentController<CsvOrderComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final CsvOrderComponentModel component)
	{
		model.addAttribute("uploadUrl", QuickorderWebConstants.URI.UPLOAD);
		model.addAttribute("CsvUrl", QuickorderWebConstants.URI.GetCSV);
	}

}
