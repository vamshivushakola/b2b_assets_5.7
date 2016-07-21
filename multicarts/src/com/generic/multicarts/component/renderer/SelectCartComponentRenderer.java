package com.generic.multicarts.component.renderer;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.jsp.PageContext;

import com.generic.multicarts.facades.MultiCartsFacade;
import com.generic.multicarts.model.SelectCartComponentModel;


/**
 * Default SelectCartComponent renderer
 * 
 * @author Capgemini
 * 
 * @param <C>
 */
public class SelectCartComponentRenderer<C extends SelectCartComponentModel> extends DefaultAddOnCMSComponentRenderer<C>
{
	@Resource
	private MultiCartsFacade multiCartsFacade;

	/**
	 * GetVariablesToExpose
	 * 
	 * @param pageContext
	 *           the page context
	 * @param component
	 *           the component
	 * @return null because carts and currentCart must not be removed
	 */
	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		pageContext.setAttribute("carts", multiCartsFacade.getCarts(), PageContext.REQUEST_SCOPE);
		pageContext.setAttribute("currentCart", multiCartsFacade.getCurrentCart(), PageContext.REQUEST_SCOPE);
		return null;
	}
}
