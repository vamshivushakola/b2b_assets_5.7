/**
 * 
 */
package com.generic.solrsearchrestriction.postprocessor;

import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SolrQueryPostProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;


/**
 * Add a filterQuery to solr query to retrieve only products that current user can see depending on its B2Bunit. If user
 * is anonymous or is not in any B2BUnit, then the filterQuery restricts in a way no product can be seen (using
 * "unknown" as grantedB2BUnits search).
 * 
 * Precondition: no B2BUnit should be called "unknown".
 * 
 * @author Capgemini
 */
public class GrantedB2BUnitSolrQueryPostProcessor implements SolrQueryPostProcessor
{
	private static final Logger LOG = Logger.getLogger(GrantedB2BUnitSolrQueryPostProcessor.class);
	private FieldNameProvider fieldNameProvider;

	@Autowired
	private UserService userService;

	/**
	 * Set fieldNameProvider
	 * 
	 * @param fieldNameProvider
	 *           set fieldNameProvider
	 */
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	@Override
	public SolrQuery process(final SolrQuery query, final SearchQuery solrSearchQuery)
	{
		final IndexedProperty property = solrSearchQuery.getIndexedType().getIndexedProperties().get("grantedB2BUnits");
		if (null != property)
		{
			final String b2bFieldName = fieldNameProvider.getFieldName(property, null, FieldNameProvider.FieldType.INDEX);
			final List<String> filterQueries = getB2BGroupsFilterQueriesForCurrentUser(b2bFieldName);
			String queryfq;
			if (!filterQueries.isEmpty())
			{
				queryfq = StringUtils.join(filterQueries, " OR ");
				final String[] grantedB2BFilterFields =
						{ "(" + queryfq + ")" };
				query.addFilterQuery(grantedB2BFilterFields);
			}
		}
		return query;
	}

	/**
	 * Get B2BGroups filter queries for current user
	 * 
	 * @param field
	 *           the field
	 * @return the list of B2BGroups filter queries for current user
	 */
	private List<String> getB2BGroupsFilterQueriesForCurrentUser(final String field)
	{
		final Collection<B2BUnitModel> b2bGroups;
		final List<String> b2bGroupNames = new ArrayList<String>();
		if (!userService.getCurrentUser().getUid().equals("anonymous"))
		{
			b2bGroups = new ArrayList<>();
			//We get only direct group to avoid getting parent B2BUnit
			final Collection<PrincipalGroupModel> groups = userService.getCurrentUser().getGroups();
			for (final PrincipalGroupModel group : groups)
			{
				if (group instanceof B2BUnitModel)
				{
					b2bGroups.add((B2BUnitModel) group);
				}
			}

			for (final B2BUnitModel b2bUnit : b2bGroups)
			{
				//Group uid is escaped to handle spaces
				b2bGroupNames.add(field + ":\"" + b2bUnit.getUid() + "\"");
			}
			b2bGroupNames.add(field + ":\":visible-for-all\"");
		}
		return b2bGroupNames;
	}

}
