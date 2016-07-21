package com.generic.multicarts.facades.impl;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.b2bacceleratorfacades.order.impl.DefaultB2BCartFacade;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import com.generic.multicarts.data.CartLogData;
import com.generic.multicarts.data.MultiCartsData;
import com.generic.multicarts.facades.MultiCartsFacade;
import com.generic.multicarts.factories.MultiCartsFactory;
import com.generic.multicarts.model.CartLogModel;
import com.generic.multicarts.services.MultiCartsService;


/**
 * MultiCartsFacade implmentation
 * 
 * @author Capgemini
 * @see com.generic.multicarts.facades.MultiCartsFacade
 */
public class MultiCartsFacadeImpl extends DefaultB2BCartFacade implements MultiCartsFacade
{

	@Resource
	private Converter<CartLogModel, CartLogData> cartLogConverter;

	@Resource
	private Converter<CartModel, MultiCartsData> multiCartsConverter;

	@Resource
	private B2BUnitService<B2BUnitModel, B2BCustomerModel> b2bUnitService;

	@Resource
	private MultiCartsService multiCartsService;

	@Resource
	private MultiCartsFactory multiCartsFactory;

	@Resource
	private ModelService modelService;

	@Resource
	private ProductService productService;

	@Override
	public SearchPageData<CartLogData> getLogs(final String cartCode, final PageableData pageableData)
	{
		final SearchPageData<CartLogModel> logs = multiCartsService.getLogs(cartCode, pageableData);
		final SearchPageData<CartLogData> ret = new SearchPageData<>();
		ret.setResults(Converters.convertAll(logs.getResults(), cartLogConverter));
		ret.setPagination(logs.getPagination());
		return ret;
	}

	@Override
	public List<MultiCartsData> getCarts()
	{
		final Collection<CartModel> carts = multiCartsService.getCarts(getUserService().getCurrentUser());
		return Converters.convertAll(carts, multiCartsConverter);
	}

	@Override
	public MultiCartsData getCart(final String cartCode)
	{
		final CartModel cart = multiCartsService.getCartByCode(cartCode);
		if (cart != null)
		{
			return multiCartsConverter.convert(cart);
		}
		else
		{
			return null;
		}
	}

	@Override
	public MultiCartsData getCurrentCart()
	{
		return multiCartsConverter.convert(multiCartsService.getSessionCart());
	}

	@Override
	public void log(final String orderCode, final String productCode, final String message, final String oldValue,
			final String newValue)
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		multiCartsService.log(orderCode, getUserService().getCurrentUser(), productModel, message, oldValue, newValue);
	}

	@Override
	public MultiCartsData createCart()
	{
		return this.createCart(null);
	}

	@Override
	public MultiCartsData createCart(final String name)
	{
		final CartModel cart = multiCartsFactory.createCart(name);
		modelService.save(cart);
		return multiCartsConverter.convert(cart);
	}

	@Override
	public void selectCart(final String cartCode)
	{
		final CartModel cart = multiCartsService.getCartByCode(cartCode);
		if (cart != null)
		{
			multiCartsService.setSessionCart(cart);
			multiCartsService.changeCurrentCartUser(getUserService().getCurrentUser());
		}
	}

	@Override
	public void removeCart(final String cartCode)
	{
		multiCartsService.removeCart(cartCode);
	}

	@Override
	public void share(final String cartCode, final String b2bUnit)
	{
		final CartModel cartByCode = multiCartsService.getCartByCode(cartCode);
		final B2BUnitModel unitForUid = b2bUnitService.getUnitForUid(b2bUnit);
		if (cartByCode != null && unitForUid != null)
		{
			multiCartsService.share(cartByCode, unitForUid);
		}
	}

	@Override
	public void unshare(final String cartCode, final String b2bUnit)
	{
		final CartModel cartByCode = multiCartsService.getCartByCode(cartCode);
		final B2BUnitModel unitForUid = b2bUnitService.getUnitForUid(b2bUnit);
		if (cartByCode != null && unitForUid != null)
		{
			multiCartsService.unshare(cartByCode, unitForUid);
		}
	}

	@Override
	public void setName(final String cartCode, final String name)
	{
		multiCartsService.setName(cartCode, name);
	}

	@Override
	public void follow(final String cartCode)
	{
		final CartModel cartByCode = multiCartsService.getCartByCode(cartCode);
		multiCartsService.follow(getUserService().getCurrentUser(), cartByCode);
	}

	@Override
	public void unfollow(final String cartCode)
	{
		final CartModel cartByCode = multiCartsService.getCartByCode(cartCode);
		multiCartsService.unfollow(getUserService().getCurrentUser(), cartByCode);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.generic.multicarts.facades.MultiCartsFacade#createCart(java.lang.String, java.lang.String)
	 */
	@Override
	public MultiCartsData createCart(final String name, final CMSSiteModel site)
	{
		final CartModel cart = multiCartsFactory.createCart(name);
		cart.setSite(site);
		modelService.save(cart);
		return multiCartsConverter.convert(cart);
	}

}
