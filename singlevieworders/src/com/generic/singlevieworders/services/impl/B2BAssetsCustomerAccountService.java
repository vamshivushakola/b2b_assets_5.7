/**
 *
 */
package com.generic.singlevieworders.services.impl;


import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.generic.singlevieworders.daos.impl.B2BAssetsCustomerAccountDao;


/**
 * B2BAssetsCustomerAccountService for customer account details
 *
 * @author ammandal
 *
 */
public class B2BAssetsCustomerAccountService extends DefaultCustomerAccountService
{
	private B2BUnitService<B2BUnitModel, B2BCustomerModel> b2BUnitService;
	private B2BAssetsCustomerAccountDao customerAccountDao;

	/**
	 * Set b2BUnitService
	 *
	 * @param b2BUnitService
	 *           set b2BUnitService
	 */
	@Required
	public void setB2BUnitService(final B2BUnitService<B2BUnitModel, B2BCustomerModel> b2BUnitService)
	{
		this.b2BUnitService = b2BUnitService;
	}


	@Override
	public SearchPageData<OrderModel> getOrderList(final CustomerModel customerModel, final BaseStoreModel store,
			final OrderStatus[] status, final PageableData pageableData)
	{
		final Logger log = Logger.getLogger(this.getClass().getName());

		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null");
		ServicesUtil.validateParameterNotNull(store, "Store must not be null");
		ServicesUtil.validateParameterNotNull(pageableData, "PageableData must not be null");

		final UserModel user = getUserService().getCurrentUser();
		if (user instanceof B2BCustomerModel)
		{
			final B2BUnitModel parentUnit = b2BUnitService.getParent((B2BCustomerModel) user);
			final B2BUnitModel rootUnit = b2BUnitService.getRootUnit(parentUnit);

			log.info("=============== Parent Unit for User " + user.getUid() + " is " + parentUnit.getName());
			log.info("=============== Root Unit for User " + user.getUid() + " is " + rootUnit.getName());

			final List<B2BCustomerModel> b2bUnitUsers = new ArrayList<B2BCustomerModel>(b2BUnitService.getUsersOfUserGroup(
					parentUnit, parentUnit.getUid(), true));


			log.info("=============== Total number of user in B2BUnit - " + parentUnit + " are " + b2bUnitUsers.size());

			for (final B2BCustomerModel b2bCustomer : b2bUnitUsers)
			{
				log.info("============== B2BCustomer userid : " + b2bCustomer.getUid());
			}

			return getCustomerAccountDao().findOrdersByB2BUnitAndStore(b2bUnitUsers, store, status, pageableData);
		}
		else
		{
			return getCustomerAccountDao().findOrdersByCustomerAndStore(customerModel, store, status, pageableData);
		}
		//

	}

	/**
	 * Set customerAccountDao
	 *
	 * @param customerAccountDao
	 *           set customerAccountDao
	 */
	@Required
	public void setCustomerAccountDao(final B2BAssetsCustomerAccountDao customerAccountDao)
	{
		this.customerAccountDao = customerAccountDao;
	}

	@Override
	protected B2BAssetsCustomerAccountDao getCustomerAccountDao()
	{
		return this.customerAccountDao;
	}
}
