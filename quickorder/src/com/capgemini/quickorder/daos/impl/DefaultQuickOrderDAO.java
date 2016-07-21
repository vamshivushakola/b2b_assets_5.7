package com.capgemini.quickorder.daos.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.quickorder.daos.QuickOrderDAO;


/**
 * Class is used to retrieve data from a database
 * 
 * @author Thomas Brison <thomas.brison@capgemini.com>
 */
public class DefaultQuickOrderDAO implements QuickOrderDAO
{

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	/*
	 * @Override public List<ProductModel> findProductsByCodeStart(final String code, final int maxNumberResults) { final
	 * String queryString = "SELECT {p:" + ProductModel.PK + "}, {p:" + ProductModel.CODE + "}, {p:" +
	 * ProductModel.CONDITIONING + "}, {p:" + ProductModel.NUMBERCONTENTUNITS + "} " + "FROM {" + ProductModel._TYPECODE
	 * + " AS p} " + "WHERE " + "{p:" + ProductModel.CODE + "} LIKE ?code"; final FlexibleSearchQuery query = new
	 * FlexibleSearchQuery(queryString); query.addQueryParameter("code", code + "%"); query.setCount(maxNumberResults);
	 * return flexibleSearchService.<ProductModel>search(query).getResult(); }
	 */



	@Override
	public List<ProductModel> findProductsByCodeStart(final String code, final int maxNumberResults)
	{

		final String queryString = "SELECT {p:" + ProductModel.PK + "}, {p:" + ProductModel.CODE + "},{p:" + ProductModel.NAME
				+ "}, {p:" + ProductModel.NUMBERCONTENTUNITS + "} " + "FROM {" + ProductModel._TYPECODE + " AS p} " + "WHERE "
				+ "{p:" + ProductModel.CODE + "} LIKE ?code";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("code", code + "%");
		query.setCount(maxNumberResults);
		return flexibleSearchService.<ProductModel> search(query).getResult();
	}
}
